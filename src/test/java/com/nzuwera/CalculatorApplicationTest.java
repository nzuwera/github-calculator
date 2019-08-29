package com.nzuwera;

import com.nzuwera.service.ICalculator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.fail;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CalculatorApplicationTest {

    private int a = 0;
    private int b = 0;
    private String wrongOperation = null;

    @Rule
    public JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation("target/generated-snippets");

    @Autowired
    ICalculator calculator;

    @Rule
    public ExpectedException thrown = ExpectedException.none();


    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(documentationConfiguration(this.restDocumentation))
                .build();
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
                .andDo(document("{ClassName}/{methodName}"));
    }

    @Test
    public void testFailCalculator() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/calculator/" + wrongOperation + "/1/2"))
                .andExpect(status().isOk())
                .andExpect(content().string("No enum constant com.nzuwera.service.ICalculator.operations." + wrongOperation.toUpperCase()))
                .andExpect(content().contentType("text/plain;charset=UTF-8"));
    }

    @Test
    public void testSuccessCalculatorAdditionService() {
        String response = calculator.run(ICalculator.operations.ADD, a, b);
        Assert.assertEquals(String.format("%d + %d = %s", a, b, a + b), response);
    }

    @Test
    public void testSuccessCalculatorSubstractionService() {
        String response = calculator.run(ICalculator.operations.SUBSTRACT, a, b);
        Assert.assertEquals(String.format("%d - %d = %s", a, b, a - b), response);
    }

    @Test
    public void testSuccessCalculatorDivisionService() {
        String response = calculator.run(ICalculator.operations.DIVIDE, a, b);
        Assert.assertEquals(String.format("%d / %d = %s", a, b, a / b), response);
    }

    @Test
    public void testSuccessCalculatorMultiplicationService() {
        String response = calculator.run(ICalculator.operations.MULTIPLY, a, b);
        Assert.assertEquals(String.format("%d * %d = %s", a, b, a * b), response);
    }

    @Test(expected = Exception.class)
    public void testSuccessCalculatorUnknownOperator() {
        String response = calculator.run(ICalculator.operations.valueOf(wrongOperation.toUpperCase()), a, b);
        Assert.assertEquals("No enum constant com.nzuwera.service.ICalculator.operations." + wrongOperation.toUpperCase(), response);
    }

    @Test
    public void validateOperations() {
        Assert.assertEquals("ADD", ICalculator.operations.ADD.name());
        Assert.assertEquals("SUBSTRACT", ICalculator.operations.SUBSTRACT.name());
        Assert.assertEquals("MULTIPLY", ICalculator.operations.MULTIPLY.name());
        Assert.assertEquals("DIVIDE", ICalculator.operations.DIVIDE.name());
    }

    @Before
    public void init(){
        a = 6;
        b = 3;
        wrongOperation = "ADDED";
    }

}