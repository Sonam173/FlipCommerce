package com.example.FlipCommerce.model;

import com.example.FlipCommerce.Enum.CardType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Table(name="card")
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(unique = true,nullable = false)
    String cardNo;
    @Column(name="cvv")
    int cvv;
    @Column(name="card_type")
    @Enumerated(EnumType.STRING)
    CardType cardType;
    @Column(name="valid_till")
    Date validTill;
    @ManyToOne
    @JoinColumn
    Customer customer;


}
