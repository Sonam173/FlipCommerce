package com.example.FlipCommerce.transformer;

import com.example.FlipCommerce.dto.responseDto.CartResponseDto;
import com.example.FlipCommerce.dto.responseDto.ItemResponseDto;
import com.example.FlipCommerce.model.Cart;
import com.example.FlipCommerce.model.Item;
import com.example.FlipCommerce.repository.CartRepository;

import java.util.ArrayList;
import java.util.List;

public class CartTransformer {
    public  static CartResponseDto CartToCartResponseDto(Cart cart)
    {  List<ItemResponseDto> itemResponseDtos= new ArrayList<>();
            for(Item item: cart.getItems())
            {
                itemResponseDtos.add(ItemTransformer.ItemToItemResponseDto(item));
            }

         return CartResponseDto.builder()
                 .cartTotal(cart.getCartTotal())
                 .customerName(cart.getCustomer().getName())
                 .items(itemResponseDtos)
                 .build();
    }
}
