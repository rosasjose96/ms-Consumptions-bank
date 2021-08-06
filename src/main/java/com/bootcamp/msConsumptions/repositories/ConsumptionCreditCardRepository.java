package com.bootcamp.msConsumptions.repositories;

import com.bootcamp.msConsumptions.entities.ConsumptionCreditCard;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ConsumptionCreditCardRepository extends ReactiveMongoRepository<ConsumptionCreditCard,String> {
}
