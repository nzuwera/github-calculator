package com.nzuwera;

import org.junit.Test;

import static org.junit.Assert.*;

public class CalculatorTest {

    Calculator calc = new Calculator();

    @Test
    public void add() {
        int sum = calc.add(4, 5);
        assertEquals("4 + 5 must be 9", 9, sum);
    }

    @Test
    public void multiply() {
        int product = calc.multiply(4, 5);
        assertEquals("4 x 5 must be 20", 20, product);
    }
}