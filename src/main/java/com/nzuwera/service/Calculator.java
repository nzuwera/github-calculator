package com.nzuwera.service;

import org.springframework.stereotype.Service;

@Service
public class Calculator implements ICalculator {

    private String response;

    @Override
    public String run(operations ops, int a, int b) {

        try {
            switch (ops) {
                case ADD:
                    response = a + " + " + b + " = " + (a + b);
                    break;
                case SUBSTRACT:
                    response = a + " - " + b + " = " + (a - b);
                    break;
                case MULTIPLY:
                    response = a + " * " + b + " = " + (a * b);
                    break;
                case DIVIDE:
                    response = a + " / " + b + " = " + (a / b);
                    break;
            }
        } catch (Exception ex) {
            response = ex.getMessage();
        }
        return response;
    }
}
