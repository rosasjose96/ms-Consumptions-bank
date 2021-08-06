package com.bootcamp.msConsumptions.handler;

import com.bootcamp.msConsumptions.entities.Consumption;
import com.bootcamp.msConsumptions.entities.ConsumptionCreditCard;
import com.bootcamp.msConsumptions.services.IConsumptionCreditCardService;
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

@Slf4j(topic = "consumtioncreditcard_handler")
@Component
public class ConsumptionCreditCardHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(CreditCardDTOHandler.class);

    @Autowired
    private IConsumptionCreditCardService service;

    public Mono<ServerResponse> findAll(ServerRequest request){
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(service.findAll(), Consumption.class);
    }

    public Mono<ServerResponse> findConsumptionCreditCard(ServerRequest request) {
        String id = request.pathVariable("id");
        return service.findById(id).flatMap((c -> ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(c))
                .switchIfEmpty(ServerResponse.notFound().build()))
        );
    }

    public Mono<ServerResponse> newConsumptionCreditCard(ServerRequest request){

        Mono<ConsumptionCreditCard> consumptionCreditCardMono = request.bodyToMono(ConsumptionCreditCard.class);

        return consumptionCreditCardMono.flatMap( c -> {
            return service.create(c);
        }).flatMap( c -> ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(c)));
    }

    public Mono<ServerResponse> deleteConsumptionCreditCard(ServerRequest request){

        String id = request.pathVariable("id");

        Mono<ConsumptionCreditCard> consumptionCreditCardMono = service.findById(id);

        return consumptionCreditCardMono
                .doOnNext(c -> LOGGER.info("deleteConsumption: consumptionId={}", c.getId()))
                .flatMap(c -> service.delete(c).then(ServerResponse.noContent().build()))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> updateConsumptionCreditCard(ServerRequest request){
        Mono<ConsumptionCreditCard> consumptionCreditCardMono = request.bodyToMono(ConsumptionCreditCard.class);
        String id = request.pathVariable("id");

        return service.findById(id).zipWith(consumptionCreditCardMono, (db,req) -> {
                    db.setConsumption(req.getConsumption());
                    db.setCreditCardDTO(req.getCreditCardDTO());
                    return db;
                }).flatMap( c -> ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(service.create(c),Consumption.class))
                .switchIfEmpty(ServerResponse.notFound().build());
    }
}
