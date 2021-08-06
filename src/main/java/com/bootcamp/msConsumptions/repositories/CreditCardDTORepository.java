package com.bootcamp.msConsumptions.repositories;

import com.bootcamp.msConsumptions.entities.CreditCardDTO;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface CreditCardDTORepository extends ReactiveMongoRepository<CreditCardDTO,String> {
}
