package com.example.FlipCommerce.controller;

import com.example.FlipCommerce.dto.requestDto.SellerRequestDto;
import com.example.FlipCommerce.dto.responseDto.SellerResponseDto;
import com.example.FlipCommerce.exception.SellerNotFoundException;
import com.example.FlipCommerce.model.Seller;
import com.example.FlipCommerce.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/seller")
public class SellerController {
    @Autowired
    SellerService sellerService;
    @PostMapping("/add")
    public ResponseEntity addSeller(@RequestBody SellerRequestDto sellerRequestDto)

    {
        SellerResponseDto sellerResponseDto=sellerService.addSeller(sellerRequestDto);
        return new ResponseEntity<>(sellerResponseDto, HttpStatus.CREATED);

    }
   }
