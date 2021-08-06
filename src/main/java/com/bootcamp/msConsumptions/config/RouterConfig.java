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
                .andRoute(RequestPredicates.POST("/api/consumption"), consumptionHandler::newConsumption)
                .andRoute(RequestPredicates.PUT("/api/consumption/{id}"), consumptionHandler::updateConsumption)
                .andRoute(RequestPredicates.DELETE("/api/consumption/{id}"), consumptionHandler::deleteConsumption)
                .andRoute(RequestPredicates.GET("/api/consumptioncreditcarddto"), creditCardDTOHandler::findAll)
                .andRoute(RequestPredicates.GET("/api/consumptioncreditcarddto/{id}"), creditCardDTOHandler::findCreditCardDTO)
                .andRoute(RequestPredicates.POST("/api/consumptioncreditcarddto"), creditCardDTOHandler::newCreditCardDTO)
                .andRoute(RequestPredicates.PUT("/api/consumptioncreditcarddto/{id}"), creditCardDTOHandler::updateCreditCardDTO)
                .andRoute(RequestPredicates.DELETE("/api/consumptioncreditcarddto/{id}"), creditCardDTOHandler::deleteCreditCardDTO)
                .andRoute(RequestPredicates.GET("/api/consumptioncreditcarddto"), conssumptionCreditCardHandler::findAll)
                .andRoute(RequestPredicates.GET("/api/consumptioncreditcarddto/{id}"), conssumptionCreditCardHandler::findConsumptionCreditCard)
                .andRoute(RequestPredicates.POST("/api/consumptioncreditcarddto"), conssumptionCreditCardHandler::newConsumptionCreditCard)
                .andRoute(RequestPredicates.PUT("/api/consumptioncreditcarddto/{id}"), conssumptionCreditCardHandler::updateConsumptionCreditCard)
                .andRoute(RequestPredicates.DELETE("/api/consumptioncreditcarddto/{id}"), conssumptionCreditCardHandler::deleteConsumptionCreditCard);
    }
}
