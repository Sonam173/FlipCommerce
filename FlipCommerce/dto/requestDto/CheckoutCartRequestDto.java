package com.example.FlipCommerce.dto.requestDto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class CheckoutCartRequestDto {
    String emailId;
    String cardId;
    int cvv;
    String cardNo;

}
