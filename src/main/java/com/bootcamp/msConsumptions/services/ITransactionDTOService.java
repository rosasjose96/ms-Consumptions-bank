package com.bootcamp.msConsumptions.services;

import com.bootcamp.msConsumptions.models.dto.TransactionActiveDTO;
import reactor.core.publisher.Mono;

public interface ITransactionDTOService {
    public Mono<TransactionActiveDTO> saveTransaction(TransactionActiveDTO transaction);
}
