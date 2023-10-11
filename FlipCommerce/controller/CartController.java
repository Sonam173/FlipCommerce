package com.example.FlipCommerce.controller;

import com.example.FlipCommerce.dto.requestDto.CheckoutCartRequestDto;
import com.example.FlipCommerce.dto.requestDto.ItemRequestDto;
import com.example.FlipCommerce.dto.responseDto.CartResponseDto;
import com.example.FlipCommerce.dto.responseDto.OrderResponseDto;
import com.example.FlipCommerce.model.Item;
import com.example.FlipCommerce.service.CartService;
import com.example.FlipCommerce.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    ItemService itemService;

    @Autowired
    CartService cartService;

    @PostMapping("/add")
    public ResponseEntity addToCart(@RequestBody ItemRequestDto itemRequestDto){

        try{
            Item item = itemService.createItem(itemRequestDto);// create item
            CartResponseDto cartResponseDto = cartService.addToCart(item,itemRequestDto);//save item to cart
            return new ResponseEntity(cartResponseDto,HttpStatus.ACCEPTED);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
     @PostMapping("/checkout")
    public ResponseEntity checkoutCart(@RequestBody CheckoutCartRequestDto checkoutCartRequestDto)
    {
            try{
                OrderResponseDto orderResponseDto=cartService.checkOutCart(checkoutCartRequestDto);
                return new ResponseEntity(orderResponseDto,HttpStatus.BAD_REQUEST);
            }
            catch (Exception e)
            {
                return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
            }
    }


}
