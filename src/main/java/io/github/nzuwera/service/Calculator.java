package io.github.nzuwera.service;

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
        operations operations = getOperation(ops.toUpperCase());

        switch (operations) {
            case ADD:
                double sum = numbers.stream().mapToDouble(Double::doubleValue).sum();
                response = String.format("%s = %s", numbersStr, sum);
                break;
            case SUBTRACT:
                if (numbers.isEmpty()) {
                    return "At least one number is required for subtraction";
                }
                double difference = numbers.getFirst();
                difference -= numbers.stream().skip(1).mapToDouble(Double::doubleValue).sum();
                response = String.format("%s = %s", numbersStr, difference);
                break;
            case MULTIPLY:
                double product = numbers.stream().reduce(1.0, (a, b) -> a * b);
                response = String.format("%s = %s", numbersStr, product);
                break;
            case DIVIDE:
                if (numbers.size() < 2) {
                    return "At least two numbers are required for division";
                }
                if (numbers.stream().skip(1).anyMatch(n -> n == 0)) {
                    return "Cannot divide by zero";
                }
                double quotient = numbers.stream().skip(1)
                        .reduce(numbers.getFirst(), (a, b) -> a / b);
                response = String.format("%s = %s", numbersStr, quotient);
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
        try {
            ICalculator.operations.valueOf(ops);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
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
            return operations.UNKNOWN;
        }
    }
}
