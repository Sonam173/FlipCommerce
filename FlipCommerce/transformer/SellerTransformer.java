package com.example.FlipCommerce.transformer;

import com.example.FlipCommerce.dto.requestDto.SellerRequestDto;
import com.example.FlipCommerce.dto.responseDto.SellerResponseDto;
import com.example.FlipCommerce.model.Seller;

import static ch.qos.logback.classic.spi.ThrowableProxyVO.build;

public class    SellerTransformer {
    public static Seller SellerRequestDtoToSeller(SellerRequestDto sellerRequestDto)
    {
        return Seller.builder()
                .name(sellerRequestDto.getName())
                .emailId(sellerRequestDto.getEmailId())
                .mobNo(sellerRequestDto.getMobNo())
                .build();

    }
    public static SellerResponseDto SellerToSellerResponseDto(Seller seller)
    {
        return SellerResponseDto.builder()
                .name(seller.getName())
                .mobNo(seller.getMobNo())
                .build();
    }
}
