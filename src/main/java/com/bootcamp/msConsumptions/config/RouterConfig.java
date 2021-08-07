package com.bootcamp.msConsumptions.config;

import com.bootcamp.msConsumptions.handler.ConsumptionCreditCardHandler;
import com.bootcamp.msConsumptions.handler.ConsumptionHandler;
import com.bootcamp.msConsumptions.handler.CreditCardDTOHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RouterConfig {

    @Bean
    public RouterFunction<ServerResponse> routes(ConsumptionHandler consumptionHandler,
                                                 CreditCardDTOHandler creditCardDTOHandler,
                                                 ConsumptionCreditCardHandler consumptionCreditCardHandler){

        return route(GET("/api/consumption"), consumptionHandler::findAll)
                .andRoute(GET("/api/consumption/{id}"), consumptionHandler::findConsumption)
                .andRoute(POST("/api/consumption"), consumptionHandler::newConsumption)
                .andRoute(PUT("/api/consumption/{id}"), consumptionHandler::updateConsumption)
                .andRoute(DELETE("/api/consumption/{id}"), consumptionHandler::deleteConsumption)
                .andRoute(GET("/api/creditcarddto"), creditCardDTOHandler::findAll)
                .andRoute(GET("/api/creditcarddto/{id}"), creditCardDTOHandler::findCreditCardDTO)
                .andRoute(POST("/api/creditcarddto"), creditCardDTOHandler::newCreditCardDTO)
                .andRoute(PUT("/api/creditcarddto/{id}"), creditCardDTOHandler::updateCreditCardDTO)
                .andRoute(DELETE("/api/creditcarddto/{id}"), creditCardDTOHandler::deleteCreditCardDTO)
                .andRoute(GET("/api/consumptioncreditcar"), consumptionCreditCardHandler::findAll)
                .andRoute(GET("/api/consumptioncreditcar/{id}"), consumptionCreditCardHandler::findConsumptionCreditCard)
                .andRoute(POST("/api/consumptioncreditcar"), consumptionCreditCardHandler::newConsumptionCreditCard)
                .andRoute(PUT("/api/consumptioncreditcar/{id}"), consumptionCreditCardHandler::updateConsumptionCreditCard)
                .andRoute(DELETE("/api/consumptioncreditcar/{id}"), consumptionCreditCardHandler::deleteConsumptionCreditCard);
    }
}
