package com.bootcamp.msConsumptions.services.impl;

import com.bootcamp.msConsumptions.entities.Consumption;
import com.bootcamp.msConsumptions.repositories.ConsumptionRepository;
import com.bootcamp.msConsumptions.services.IConsumptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ConsumptionServiceImpl implements IConsumptionService {

    @Autowired
    private ConsumptionRepository repository;

    @Override
    public Mono<Consumption> create(Consumption o) {
        return repository.save(o);
    }

    @Override
    public Flux<Consumption> findAll() {
        return repository.findAll();
    }

    @Override
    public Mono<Consumption> findById(String s) {
        return repository.findById(s);
    }

    @Override
    public Mono<Consumption> update(String s, Consumption o) {
        return repository.findById(s).flatMap( c -> {
            if (c == null){
                return null;
            }
            c.setDescription(o.getDescription());
            c.setAmount(o.getAmount());

            return Mono.just(c);
        });
    }

    @Override
    public Mono<Void> delete(Consumption o) {
        return repository.delete(o);
    }
}
