package com.example.simpleapp.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("test")
@ContextConfiguration
@AutoConfigureMockMvc
class ControllerTest {
    @Autowired 
    private MockMvc mvc;


    @Test
    void testHello() throws Exception {
        this.mvc.perform(get("/test")).andExpect(status().isOk());
    } 
    
    @Test
    void testHelloCiOps() throws Exception {
        this.mvc.perform(get("/hello")).andExpect(status().isOk());
    } 
}
