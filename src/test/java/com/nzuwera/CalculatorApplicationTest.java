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
                        .get("/calculator/A/1/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/plain;charset=UTF-8"))
                .andDo(document("{ClassName}/{methodName}"));
    }

    @Test
    public void testFailCalculator() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/calculator/" + wrongOperation + "/1/2"))
                .andExpect(status().isOk())
                .andExpect(content().string("Unknown Operation"))
                .andExpect(content().contentType("text/plain;charset=UTF-8"));
    }

    @Test
    public void testSuccessCalculatorAdditionService() {
        String response = calculator.run("A", a, b);
        Assert.assertEquals(String.format("%d + %d = %s", a, b, a + b), response);
    }

    @Test
    public void testSuccessCalculatorSubstractionService() {
        String response = calculator.run("S", a, b);
        Assert.assertEquals(String.format("%d - %d = %s", a, b, a - b), response);
    }

    @Test
    public void testSuccessCalculatorDivisionService() {
        String response = calculator.run("D", a, b);
        Assert.assertEquals(String.format("%d / %d = %s", a, b, a / b), response);
    }

    @Test
    public void testSuccessCalculatorMultiplicationService() {
        String response = calculator.run("M", a, b);
        Assert.assertEquals(String.format("%d * %d = %s", a, b, a * b), response);
    }

    @Test
    public void testSuccessCalculatorUnknownOperator() {
        String response = calculator.run(wrongOperation, a, b);
        Assert.assertEquals("Unknown Operation", response);
    }

    @Test
    public void validateOperations() {
        Assert.assertEquals("A", ICalculator.operations.A.name());
        Assert.assertEquals("S", ICalculator.operations.S.name());
        Assert.assertEquals("M", ICalculator.operations.M.name());
        Assert.assertEquals("D", ICalculator.operations.D.name());
        Assert.assertEquals("U", ICalculator.operations.U.name());
    }

    @Before
    public void init() {
        a = 6;
        b = 3;
        wrongOperation = "ADDED";
    }

}