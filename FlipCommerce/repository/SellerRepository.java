package com.example.FlipCommerce.repository;

import com.example.FlipCommerce.dto.responseDto.SellerResponseDto;
import com.example.FlipCommerce.model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SellerRepository extends JpaRepository<Seller,Integer> {
     Seller findByEmailId(String emailId);



}
