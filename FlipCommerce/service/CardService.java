package com.example.FlipCommerce.service;

import com.example.FlipCommerce.dto.requestDto.CardRequestDto;
import com.example.FlipCommerce.dto.responseDto.CardResponseDto;
import com.example.FlipCommerce.exception.CustomerNotFoundException;
import com.example.FlipCommerce.model.Card;
import com.example.FlipCommerce.model.Customer;
import com.example.FlipCommerce.repository.CustomerRepository;
import com.example.FlipCommerce.transformer.CardTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardService {
    @Autowired
    CustomerRepository customerRepository;
    public CardResponseDto addCard(CardRequestDto cardRequestDto) throws CustomerNotFoundException
    {// check customer exist or not
        Customer customer = customerRepository.findByEmailId(cardRequestDto.getEmailId());
        if(customer==null){
            throw new CustomerNotFoundException("Invalid email id!!!");// customer is not exist
        }

        // dto -> entity
        Card card = CardTransformer.CardRequestDtoToCard(cardRequestDto);///set card
        card.setCustomer(customer);//set customer
        customer.getCards().add(card);// add the card


        // save customer and card
        Customer savedCustomer = customerRepository.save(customer);

        // preapre response Dto
        return CardTransformer.CardToCardResponseDto(card);
    }

    }


