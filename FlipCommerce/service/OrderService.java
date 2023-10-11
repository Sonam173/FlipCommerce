package com.example.FlipCommerce.service;

import com.example.FlipCommerce.Enum.ProductStatus;
import com.example.FlipCommerce.dto.requestDto.OrderRequestDto;
import com.example.FlipCommerce.dto.responseDto.OrderResponseDto;
import com.example.FlipCommerce.exception.CustomerNotFoundException;
import com.example.FlipCommerce.exception.InsufficientQuantityException;
import com.example.FlipCommerce.exception.InvalidCardException;
import com.example.FlipCommerce.exception.ProductNotFoundException;
import com.example.FlipCommerce.model.*;
import com.example.FlipCommerce.repository.CardRepository;
import com.example.FlipCommerce.repository.CustomerRepository;
import com.example.FlipCommerce.repository.OrderRepository;
import com.example.FlipCommerce.repository.ProductRepository;
import com.example.FlipCommerce.transformer.ItemTransformer;
import com.example.FlipCommerce.transformer.OrderTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderService {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    CardRepository cardRepository;
    @Autowired
    ProductRepository productRepository;

    public OrderResponseDto placeOrder(OrderRequestDto orderRequestDto) throws  ProductNotFoundException, InvalidCardException, InsufficientQuantityException, CustomerNotFoundException
    {
        Customer customer = customerRepository.findByEmailId(orderRequestDto.getEmailId());
        if (customer == null) {
            throw new CustomerNotFoundException("customer does not exist");
        }
        Optional<Product> optionalProduct = productRepository.findById(orderRequestDto.getProductId());
        if (optionalProduct.isEmpty()) {
            throw new ProductNotFoundException("Sorry! Product does not exist");
        }
        Product product = optionalProduct.get();

        // check quantity
        if (product.getQuantity() < orderRequestDto.getRequiredQuantity()) {
            throw new InsufficientQuantityException("Required quantity not available");
        }
        // check card
        Card card = cardRepository.findByCardNo(orderRequestDto.getCardNo());
        Date date = new Date();
        if (card == null || card.getCvv() != orderRequestDto.getCvv() || date.after(card.getValidTill())) {
            throw new InvalidCardException("Sorry! you cant not use this card");

        }
        // decrease the quantity of product
        int newQuantity = product.getQuantity() - orderRequestDto.getRequiredQuantity();
        product.setQuantity(newQuantity);
        if (newQuantity == 0) {
            product.setProductStatus(ProductStatus.OUT_OF_STOCK);
        }
        // for place the order
        //Create item
        Item item = ItemTransformer.ItemRequestDtoToItem(orderRequestDto.getRequiredQuantity());
        item.setProduct(product);

// create order
        OrderEntity orderEntity = OrderTransformer.OrderRequestDtoToOrder(item,customer);
        String maskedCard= generateMaskedCardNo(card);
        orderEntity.setCardUsed(maskedCard);

        orderEntity.getItems().add(item);
        item.setOrderEntity(orderEntity);

        OrderEntity savedOrder = orderRepository.save(orderEntity);  // saves both order and item
        customer.getOrders().add(savedOrder);
        product.getItems().add(savedOrder.getItems().get(0));

        // prepare response dto
        return OrderTransformer.OrderToOrderResponseDto(savedOrder);

    }
    public OrderEntity placeOrder(Cart cart, Card card) throws InsufficientQuantityException
    {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setOrderNo(String.valueOf(UUID.randomUUID()));
        orderEntity.setCardUsed(generateMaskedCardNo(card));
     // requried quantity not is not present
        int totalValue=0;
        for(Item item: cart.getItems()){
            Product product = item.getProduct();
            if(item.getRequiredQuantity()>product.getQuantity()){
                throw new InsufficientQuantityException("Required Quantity not present!!");
            }

            totalValue += item.getRequiredQuantity()*product.getPrice();
            int newQuantity = product.getQuantity()-item.getRequiredQuantity();
            product.setQuantity(newQuantity);
            if(newQuantity==0){
                product.setProductStatus(ProductStatus.OUT_OF_STOCK);
            }

            item.setOrderEntity(orderEntity);
        }

        orderEntity.setTotalValue(totalValue);
        orderEntity.setItems(cart.getItems());
        orderEntity.setCustomer(cart.getCustomer());

        return orderEntity;
    }

    private String generateMaskedCardNo(Card card){
        String cardNo = "";
        String originalCardNo = card.getCardNo();

        for(int i=0;i<originalCardNo.length()-4;i++){
            cardNo += "X";
        }
        cardNo += originalCardNo.substring(originalCardNo.length()-4);
        return cardNo;
    }


}
