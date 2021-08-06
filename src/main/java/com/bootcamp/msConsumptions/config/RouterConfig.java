package com.bootcamp.msConsumptions.config;

import com.bootcamp.msConsumptions.entities.CreditCardDTO;
import com.bootcamp.msConsumptions.handler.ConsumptionCreditCardHandler;
import com.bootcamp.msConsumptions.handler.ConsumptionHandler;
import com.bootcamp.msConsumptions.handler.CreditCardDTOHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RouterConfig {

    @Bean
    public RouterFunction<ServerResponse> routes(ConsumptionHandler consumptionHandler,
                                                 CreditCardDTOHandler creditCardDTOHandler,
                                                 ConsumptionCreditCardHandler conssumptionCreditCardHandler){

        return route(RequestPredicates.GET("/api/consumption"), consumptionHandler::findAll)
                .andRoute(RequestPredicates.GET("/api/consumption/{id}"), consumptionHandler::findConsumption)
                .andRoute(RequestPredicates.POST("/product"), consumptionHandler::newConsumption);
    }
}
