package com.nzuwera.service;

import org.springframework.stereotype.Service;

@Service
public class Calculator implements ICalculator {


    @Override
    public String run(operations ops, int a, int b) {
        String response = null;
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
        return response;
    }
}
