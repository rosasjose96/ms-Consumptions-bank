package com.bootcamp.msConsumptions.handler;

import com.bootcamp.msConsumptions.entities.Consumption;
import com.bootcamp.msConsumptions.entities.CreditCardDTO;
import com.bootcamp.msConsumptions.services.IConsumptionService;
import com.bootcamp.msConsumptions.services.ICreditCardDTOService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Slf4j(topic = "creditcard_handler")
@Component
public class CreditCardDTOHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(CreditCardDTOHandler.class);

    @Autowired
    private ICreditCardDTOService service;

    public Mono<ServerResponse> findAll(ServerRequest request){
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(service.findAll(), Consumption.class);
    }

    public Mono<ServerResponse> findCreditCardDTO(ServerRequest request) {
        String id = request.pathVariable("id");
        return service.findById(id).flatMap((c -> ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(c))
                .switchIfEmpty(ServerResponse.notFound().build()))
        );
    }

    public Mono<ServerResponse> newCreditCardDTO(ServerRequest request){

        Mono<CreditCardDTO> consumptionMono = request.bodyToMono(CreditCardDTO.class);

        return consumptionMono.flatMap( c -> {
               return service.create(c);
        }).flatMap( c -> ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(c)));
    }

    public Mono<ServerResponse> deleteCreditCardDTO(ServerRequest request){

        String id = request.pathVariable("id");

        Mono<CreditCardDTO> creditCardDTOMono = service.findById(id);

        return creditCardDTOMono
                .doOnNext(c -> LOGGER.info("deleteConsumption: consumptionId={}", c.getId()))
                .flatMap(c -> service.delete(c).then(ServerResponse.noContent().build()))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> updateCreditCardDTO(ServerRequest request){
        Mono<CreditCardDTO> creditCardDTOMono = request.bodyToMono(CreditCardDTO.class);
        String id = request.pathVariable("id");

        return service.findById(id).zipWith(creditCardDTOMono, (db,req) -> {
                    db.setCreditLimit(req.getCreditLimit());
                    db.setTotalConsumption(req.getTotalConsumption());
                    return db;
                }).flatMap( c -> ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(service.create(c),Consumption.class))
                .switchIfEmpty(ServerResponse.notFound().build());
    }
}
