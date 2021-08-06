package com.bootcamp.msConsumptions.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "consumptionCreditCard")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ConsumptionCreditCard {

    @Id
    private String id;

    private Consumption consumption;

    private CreditCardDTO creditCardDTO;
}
