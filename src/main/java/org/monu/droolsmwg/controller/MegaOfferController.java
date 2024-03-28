package org.monu.droolsmwg.controller;


import org.kie.api.runtime.KieSession;
import org.monu.droolsmwg.DroolsMwgApplication;
import org.monu.droolsmwg.domain.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MegaOfferController {

    Logger logger = LoggerFactory.getLogger(DroolsMwgApplication.class);

    @Autowired
    private KieSession kieSession;

    @PostMapping("/order")
    public Order orderNow(@RequestBody Order order){
        logger.info(".. rules trigger ...");
        logger.debug("Order details: " + order);
        kieSession.insert(order);

        logger.debug("Order details after insert " + order);
        kieSession.fireAllRules();

        return order;
    }
}
