package com.example.FlipCommerce.transformer;

import com.example.FlipCommerce.dto.requestDto.CardRequestDto;
import com.example.FlipCommerce.dto.responseDto.CardResponseDto;
import com.example.FlipCommerce.model.Card;

public class CardTransformer {
    public static Card CardRequestDtoToCard(CardRequestDto cardRequestDto){

        return Card.builder()
                .cardNo(cardRequestDto.getCardNo())
                .validTill(cardRequestDto.getValidTill())
                .cvv(cardRequestDto.getCvv())
                .cardType(cardRequestDto.getCardType())
                .build();
    }

    public static CardResponseDto CardToCardResponseDto(Card card) {

        return CardResponseDto.builder()
                .cardNo(card.getCardNo())
                .cardType(card.getCardType())
                .customerName(card.getCustomer().getName())
                .build();
    }
}
