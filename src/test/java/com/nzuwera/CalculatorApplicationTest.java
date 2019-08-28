package com.nzuwera;

import com.nzuwera.service.ICalculator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
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

    @Rule
    public JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation("target/generated-snippets");

    @Autowired
    ICalculator calculator;

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
        String ops = "asdfaasdfasd";
        this.mockMvc.perform(MockMvcRequestBuilders.get("/calculator/" + ops + "/1/2"))
                .andExpect(status().isOk())
                .andExpect(content().string("No enum constant com.nzuwera.service.ICalculator.operations." + ops.toUpperCase()))
                .andExpect(content().contentType("text/plain;charset=UTF-8"));
    }


    @Test
    public void validateOperations() {
        Assert.assertEquals("ADD", ICalculator.operations.ADD.name());
        Assert.assertEquals("SUBSTRACT", ICalculator.operations.SUBSTRACT.name());
        Assert.assertEquals("MULTIPLY", ICalculator.operations.MULTIPLY.name());
        Assert.assertEquals("DIVIDE", ICalculator.operations.DIVIDE.name());
    }

}