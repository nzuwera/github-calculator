package com.nzuwera;

import com.nzuwera.service.ICalculator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CalculatorApplicationTest {

    private int a = 0;
    private int b = 0;
    private String wrongOperation = null;

    @RegisterExtension
    RestDocumentationExtension restDocumentation = new RestDocumentationExtension("target/generated-snippets");

    @Autowired
    ICalculator calculator;

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup(RestDocumentationContextProvider restDocumentation) {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(documentationConfiguration(restDocumentation))
                .build();

        // Initialize test data
        a = 6;
        b = 3;
        wrongOperation = "ADDED";
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
        Assertions.assertEquals(String.format("%d + %d = %s", a, b, a + b), response);
    }

    @Test
    public void testSuccessCalculatorSubstractionService() {
        String response = calculator.run("S", a, b);
        Assertions.assertEquals(String.format("%d - %d = %s", a, b, a - b), response);
    }

    @Test
    public void testSuccessCalculatorDivisionService() {
        String response = calculator.run("D", a, b);
        Assertions.assertEquals(String.format("%d / %d = %s", a, b, a / b), response);
    }

    @Test
    public void testSuccessCalculatorMultiplicationService() {
        String response = calculator.run("M", a, b);
        Assertions.assertEquals(String.format("%d * %d = %s", a, b, a * b), response);
    }

    @Test
    public void testSuccessCalculatorUnknownOperator() {
        String response = calculator.run(wrongOperation, a, b);
        Assertions.assertEquals("Unknown Operation", response);
    }

    @Test
    public void validateOperations() {
        Assertions.assertEquals("A", ICalculator.operations.A.name());
        Assertions.assertEquals("S", ICalculator.operations.S.name());
        Assertions.assertEquals("M", ICalculator.operations.M.name());
        Assertions.assertEquals("D", ICalculator.operations.D.name());
        Assertions.assertEquals("U", ICalculator.operations.U.name());
    }

    @Test
    public void testHomePage() throws Exception {
        this.mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/"))
                .andExpect(status().isOk());
    }

}
