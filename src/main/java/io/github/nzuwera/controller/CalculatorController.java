package io.github.nzuwera.controller;

import io.github.nzuwera.service.ICalculator;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/calculator")
public class CalculatorController {
    private final ICalculator calculator;

    public CalculatorController(ICalculator calculator) {
        this.calculator = calculator;
    }

    /**
     * Perform an arithmetic operation on a list of numbers
     * 
     * @param operation the operation to perform (A: addition, S: subtraction, M: multiplication, D: division)
     * @param numbers the list of numbers to operate on
     * @return the result of the operation as a formatted string
     */
    @PostMapping(value = "/{operation}")
    public String calculate(@PathVariable String operation, @RequestBody List<Double> numbers) {
        return calculator.run(operation, numbers);
    }

    /**
     * Legacy endpoint for backward compatibility
     * Converts the two integers to a list and calls the new service
     */
    @GetMapping(value = "/{operation}/{a}/{b}")
    public String calculateLegacy(@PathVariable String operation, @PathVariable double a, @PathVariable double b) {
        return calculator.run(operation, List.of(a, b));
    }
}
