package com.test.autobusiness;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.ClassRule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@AutoConfigureMockMvc
@SpringBootTest
public class CarControllerIntegrationTests {

    private static MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @ClassRule
    private static String token;

    private final String AUTH_JSON = "{\"password\": \"test\", \"username\": \"goose\"}";

    @Autowired
    public CarControllerIntegrationTests(MockMvc mockMvc) {
        CarControllerIntegrationTests.mockMvc = mockMvc;
    }

    @BeforeEach
    private void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
        String tokenJson = mockMvc.perform(post("/api/v1/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(AUTH_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        ObjectNode node = new ObjectMapper().readValue(tokenJson, ObjectNode.class);
        token = "Bearer_" + node.get("token").asText();
    }

    @Test
    void givenMockMvc_whenGetValue_thenValueIsNotNull() {

        assertNotNull(mockMvc);
        assertNotNull(token);
    }

    @Test
    void givenMockMvc_whenGetCarsFromController_thenCarsAreValid() throws Exception {

        //given
        String carJson = mockMvc.perform(get("/api/v1/car/4")
                .header(HttpHeaders.AUTHORIZATION, token))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        //when
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode carNode = mapper.readValue(carJson, ObjectNode.class);

        //then
        assertEquals(4, Integer.parseInt(carNode.get("id").asText()));
        assertNotNull(carNode.get("brand"));
        assertNotEquals("", carNode.get("id"));
    }
}
