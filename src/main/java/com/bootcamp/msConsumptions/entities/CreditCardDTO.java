package com.bootcamp.msConsumptions.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "creditCardDTO")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreditCardDTO {

    @Id
    private String id;

    private String pan;

    private String cardType;

    private String cardBrand;

    private double creditLimit;

    private double totalConsumption;
}
