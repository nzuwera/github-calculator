package com.nzuwera.controller;

import com.nzuwera.service.ICalculator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/calculator")
public class CalculatorController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CalculatorController.class);
    @Autowired
    ICalculator calculator;

    @GetMapping(value = "/{operation}/{a}/{b}")
    public String calculate(@PathVariable String operation, @PathVariable int a, @PathVariable int b) {
        try {
            String response = calculator.run(ICalculator.operations.valueOf(operation.toUpperCase()), a, b);
            LOGGER.info(getClass().getSimpleName() + "][" + response + "]");
            return response;
        } catch (Exception ex) {

            return ex.getMessage();
        }
    }
}
