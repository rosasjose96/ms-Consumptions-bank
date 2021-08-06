package com.bootcamp.msConsumptions.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Document(collection = "consumption")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Consumption {

    @Id
    private String id;

    private double amount;

    private String description;

    private CreditCardDTO creditCard;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date date;
}
