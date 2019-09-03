package com.nzuwera.service;

import org.springframework.stereotype.Service;

@Service
public class Calculator implements ICalculator {

    /**
     * Run arithmetic operation
     *
     * @param ops operand string
     * @param a   numeric input 1
     * @param b   numeric input 2
     * @return response string
     */
    @Override
    public String run(String ops, int a, int b) {
        String response;
        operations operations = getOperation(ops);
        switch (operations) {
            case A:
                response = a + " + " + b + " = " + (a + b);
                break;
            case S:
                response = a + " - " + b + " = " + (a - b);
                break;
            case M:
                response = a + " * " + b + " = " + (a * b);
                break;
            case D:
                response = a + " / " + b + " = " + (a / b);
                break;
            default:
                response = "Unknown Operation";
        }
        return response;
    }

    /**
     * Test if Operation string is valid operand
     *
     * @param ops operand string
     * @return true | false
     */
    @Override
    public boolean isOperation(String ops) {
        String operand = ops.toUpperCase();
        for (operations operation : ICalculator.operations.values()) {
            if (operation.name().equals(operand)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Get Correct operand from given string and set U for invalid operand
     *
     * @param operation operand string
     * @return operations enum
     */
    @Override
    public operations getOperation(String operation) {
        if (isOperation(operation)) {
            return operations.valueOf(operation);
        } else {
            return operations.U;
        }
    }
}
