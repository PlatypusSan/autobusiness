package com.test.autobusiness;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.ClassRule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@AutoConfigureMockMvc
@SpringBootTest
@TestPropertySource("/application-test.yaml")
public class CarControllerIntegrationTests {

    private static MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @ClassRule
    private static String token;

    @Value("${auth-json}")
    private String authJson;

    @Value("${login}")
    private String loginUrl;

    @Value("${get-car}")
    private String getCarUrl;

    @Value("${car-id}")
    private long carId;

    @Autowired
    public CarControllerIntegrationTests(MockMvc mockMvc) {
        CarControllerIntegrationTests.mockMvc = mockMvc;
    }

    @BeforeEach
    private void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
        String tokenJson = mockMvc.perform(post(loginUrl)
                .contentType(MediaType.APPLICATION_JSON)
                .content(authJson))
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
        String carJson = mockMvc.perform(get(getCarUrl + carId)
                .header(HttpHeaders.AUTHORIZATION, token))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        //when
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode carNode = mapper.readValue(carJson, ObjectNode.class);

        //then
        assertEquals(carId, Integer.parseInt(carNode.get("id").asText()));
    }
}
