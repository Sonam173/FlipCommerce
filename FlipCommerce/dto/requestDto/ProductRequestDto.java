package com.example.FlipCommerce.dto.requestDto;

import com.example.FlipCommerce.Enum.Category;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE)
@Builder
public class ProductRequestDto {
    String sellerEmailId;
    String name;
    Integer price;
    Category category;
    Integer quantity;
}
