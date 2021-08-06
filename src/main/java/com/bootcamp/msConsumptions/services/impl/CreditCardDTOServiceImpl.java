package com.bootcamp.msConsumptions.services.impl;

import com.bootcamp.msConsumptions.entities.CreditCardDTO;
import com.bootcamp.msConsumptions.repositories.CreditCardDTORepository;
import com.bootcamp.msConsumptions.services.ICreditCardDTOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CreditCardDTOServiceImpl implements ICreditCardDTOService {

    @Autowired
    private CreditCardDTORepository repository;

    @Override
    public Mono<CreditCardDTO> create(CreditCardDTO o) {
        return repository.save(o);
    }

    @Override
    public Flux<CreditCardDTO> findAll() {
        return repository.findAll();
    }

    @Override
    public Mono<CreditCardDTO> findById(String s) {
        return repository.findById(s);
    }

    @Override
    public Mono<CreditCardDTO> update(String s, CreditCardDTO o) {
        return repository.findById(s).flatMap( c -> {
            if (c == null){
                return null;
            }
            c.setCreditLimit(o.getTotalConsumption());
            c.setTotalConsumption(o.getTotalConsumption());

            return Mono.just(c);
        });
    }

    @Override
    public Mono<Void> delete(CreditCardDTO o) {
        return repository.delete(o);
    }
}
