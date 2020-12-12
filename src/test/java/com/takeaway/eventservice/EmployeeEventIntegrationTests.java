package com.takeaway.eventservice;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Naveen Kumashi
 */

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class EmployeeEventIntegrationTests {
	@Autowired
	private MockMvc mockMvc;
		
	@BeforeEach
    public void setup() {
        JacksonTester.initFields(this, new ObjectMapper());
    }
	
	@Test
	void should_fetch_events() throws Exception {			 	
		mockMvc.perform(get("/api/events")
	            .contentType("application/json"))
	            .andExpect(status().isOk());   
	}
}
