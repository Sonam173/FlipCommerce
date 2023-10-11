package com.example.FlipCommerce.dto.requestDto;

import jakarta.persistence.Column;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE)
@Builder
public class SellerRequestDto {
    String name;

    String emailId;

    String mobNo;
}
