package com.nzuwera.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class Calculator implements ICalculator {
    private static final Logger LOGGER = LoggerFactory.getLogger(Calculator.class);

    private String response;

    @Override
    public String run(operations ops, int a, int b) {

        try {
            switch (ops) {
                case ADD:
                    response = a + " + " + b + " = " + (a + b);
                    LOGGER.info(getClass().getSimpleName() + "][" + ops.name() + "][" + response + "]");
                    break;
                case SUBSTRACT:
                    response = a + " - " + b + " = " + (a - b);
                    LOGGER.info(getClass().getSimpleName() + "][" + ops.name() + "][" + response + "]");
                    break;
                case MULTIPLY:
                    response = a + " * " + b + " = " + (a * b);
                    LOGGER.info(getClass().getSimpleName() + "][" + ops.name() + "][" + response + "]");
                    break;
                case DIVIDE:
                    response = a + " / " + b + " = " + (a / b);
                    LOGGER.info(getClass().getSimpleName() + "][" + ops.name() + "][" + response + "]");
                    break;
            }
        } catch (Exception ex) {
            LOGGER.info(getClass().getSimpleName() + "][" + ops.name() + "][" + ex.getMessage() + "]");
            response = ex.getMessage();
        }
        return response;
    }
}
