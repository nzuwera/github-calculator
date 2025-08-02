package com.nzuwera.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class Calculator implements ICalculator {

    /**
     * Run an arithmetic operation on a list of numbers
     *
     * @param ops     operand string
     * @param numbers list of numeric inputs
     * @return response string
     */
    @Override
    public String run(String ops, List<Double> numbers) {
        if (numbers == null || numbers.isEmpty()) {
            return "No numbers provided";
        }

        String numbersStr = numbers.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(" "));

        String response;
        operations operations = getOperation(ops);

        switch (operations) {
            case A:
                double sum = numbers.stream().mapToDouble(Double::doubleValue).sum();
                response = numbersStr + " = " + sum;
                break;
            case S:
                double difference = numbers.getFirst();
                for (int i = 1; i < numbers.size(); i++) {
                    difference -= numbers.get(i);
                }
                response = numbersStr + " = " + difference;
                break;
            case M:
                double product = 1.0;
                for (Double number : numbers) {
                    product *= number;
                }
                response = numbersStr + " = " + product;
                break;
            case D:
                if (numbers.size() < 2) {
                    return "At least two numbers are required for division";
                }
                double quotient = numbers.getFirst();
                for (int i = 1; i < numbers.size(); i++) {
                    if (numbers.get(i) == 0) {
                        return "Cannot divide by zero";
                    }
                    quotient /= numbers.get(i);
                }
                response = numbersStr + " = " + quotient;
                break;
            default:
                response = "Unknown Operation";
        }
        return response;
    }

    /**
     * Test if Operation string is a valid operand
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
     * Get Correct operand from a given string and set U for invalid operand
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
