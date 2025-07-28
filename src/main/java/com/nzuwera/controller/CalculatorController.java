package com.nzuwera.controller;

import com.nzuwera.service.ICalculator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/calculator")
public class CalculatorController {
    private final ICalculator calculator;

    public CalculatorController(ICalculator calculator) {
        this.calculator = calculator;
    }

    @GetMapping(value = "/{operation}/{a}/{b}")
    public String calculate(@PathVariable String operation, @PathVariable int a, @PathVariable int b) {
        return calculator.run(operation, a, b);
    }
}
