package com.bootcamp.msConsumptions.handler;

import com.bootcamp.msConsumptions.entities.Consumption;
import com.bootcamp.msConsumptions.services.IConsumptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Date;

@Component
public class ConsumptionHandler {

    @Autowired
    private IConsumptionService service;

    public Mono<ServerResponse> findAll(ServerRequest request){
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(service.findAll(), Consumption.class);
    }

    public Mono<ServerResponse> findConsumption(ServerRequest request) {
        String id = request.pathVariable("id");
        return service.findById(id).flatMap((c -> ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(c))
                .switchIfEmpty(ServerResponse.notFound().build()))
        );
    }

    public Mono<ServerResponse> newConsumption(ServerRequest request){

            Mono<Consumption> consumptionMono = request.bodyToMono(Consumption.class);

            return consumptionMono.flatMap( c -> {
                if(c.getDate() == null){
                    c.setDate(new Date());
                }
                return service.create(c);
            }).flatMap( c -> ServerResponse
                    .ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(BodyInserters.fromValue(c)));
          }
}
