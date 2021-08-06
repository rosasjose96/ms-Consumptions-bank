package com.bootcamp.msConsumptions.services.impl;

import com.bootcamp.msConsumptions.entities.ConsumptionCreditCard;
import com.bootcamp.msConsumptions.repositories.ConsumptionCreditCardRepository;
import com.bootcamp.msConsumptions.services.IConsumptionCreditCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ConsumptionCreditCardServiceImpl implements IConsumptionCreditCardService {

    @Autowired
    private ConsumptionCreditCardRepository repository;

    @Override
    public Mono<ConsumptionCreditCard> create(ConsumptionCreditCard o) {
        return repository.save(o);
    }

    @Override
    public Flux<ConsumptionCreditCard> findAll() {
        return repository.findAll();
    }

    @Override
    public Mono<ConsumptionCreditCard> findById(String s) {
        return repository.findById(s);
    }

    @Override
    public Mono<Void> delete(ConsumptionCreditCard o) {
        return repository.delete(o);
    }
}
