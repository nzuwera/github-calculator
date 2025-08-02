package io.github.nzuwera;

import io.github.nzuwera.service.ICalculator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith({SpringExtension.class})
@SpringBootTest
public class CalculatorApplicationTest {

    private double a = 0;
    private double b = 0;
    private List<Double> numbers;
    private List<Double> singleNumber;
    private String wrongOperation = null;

    @Autowired
    ICalculator calculator;

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();

        // Initialize test data
        a = 6.0;
        b = 3.0;
        numbers = Arrays.asList(a, b);
        singleNumber = List.of(5.0);
        wrongOperation = "XXX";
    }

    @Test
    public void contextLoads() {
        CalculatorApplication.main(new String[]{});
    }

    @Test
    public void testSuccessCalculator() throws Exception {
        this.mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/calculator/ADD/1/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/plain;charset=UTF-8"))
                ;
    }

    @Test
    public void testSuccessCalculatorList() throws Exception {
        String jsonContent = "[1.5, 2.5, 3.5]";

        this.mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/calculator/ADD")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/plain;charset=UTF-8"));
    }

    @Test
    public void testEmptyListCalculator() throws Exception {
        String jsonContent = "[]";

        this.mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/calculator/ADD")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(content().string("No numbers provided"))
                .andExpect(content().contentType("text/plain;charset=UTF-8"));
    }

    @Test
    public void testFailCalculator() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/calculator/" + wrongOperation + "/1/2"))
                .andExpect(status().isOk())
                .andExpect(content().string("Unknown Operation"))
                .andExpect(content().contentType("text/plain;charset=UTF-8"));
    }

    @Test
    public void testFailCalculatorList() throws Exception {
        String jsonContent = "[1.5, 2.5, 3.5]";

        this.mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/calculator/" + wrongOperation)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(content().string("Unknown Operation"))
                .andExpect(content().contentType("text/plain;charset=UTF-8"));
    }

    @Test
    public void testSuccessCalculatorAdditionService() {
        String response = calculator.run("Add", numbers);
        Assertions.assertEquals(a + " " + b + " = " + (a + b), response);
    }

    @Test
    public void testSuccessCalculatorSubstractionService() {
        String response = calculator.run("Subtract", numbers);
        Assertions.assertEquals(a + " " + b + " = " + (a - b), response);
    }

    @Test
    public void testSuccessCalculatorDivisionService() {
        String response = calculator.run("Divide", numbers);
        Assertions.assertEquals(a + " " + b + " = " + (a / b), response);
    }

    @Test
    public void testSuccessCalculatorMultiplicationService() {
        String response = calculator.run("Multiply", numbers);
        Assertions.assertEquals(a + " " + b + " = " + (a * b), response);
    }

    @Test
    public void testSuccessCalculatorUnknownOperator() {
        String response = calculator.run(wrongOperation, numbers);
        Assertions.assertEquals("Unknown Operation", response);
    }

    @Test
    public void testEmptyList() {
        String response = calculator.run("Add", List.of());
        Assertions.assertEquals("No numbers provided", response);
    }

    @Test
    public void testDivisionByZero() {
        String response = calculator.run("Divide", Arrays.asList(10.0, 0.0));
        Assertions.assertEquals("Cannot divide by zero", response);
    }

    @Test
    public void testSingleNumberDivision() {
        String response = calculator.run("Divide", singleNumber);
        Assertions.assertEquals("At least two numbers are required for division", response);
    }

    @Test
    public void validateOperations() {
        Assertions.assertEquals("Add".toUpperCase(), ICalculator.operations.ADD.name());
        Assertions.assertEquals("Subtract".toUpperCase(), ICalculator.operations.SUBTRACT.name());
        Assertions.assertEquals("Multiply".toUpperCase(), ICalculator.operations.MULTIPLY.name());
        Assertions.assertEquals("Divide".toUpperCase(), ICalculator.operations.DIVIDE.name());
        Assertions.assertEquals("Unknown".toUpperCase(), ICalculator.operations.UNKNOWN.name());
    }

    @Test
    public void testHomePage() throws Exception {
        this.mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/"))
                .andExpect(status().isOk());
    }

}
