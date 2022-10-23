package com.microservices.skeleton;

import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TestBikeController {

    private static final String URL = "/bikes";
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getBikes() throws Exception {
        mockMvc.perform(get(URL)).andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }


    @Test
    public void insertBikeCoverageWithinRange() throws Exception {
        Bike bike = new Bike();
        bike.setCoverage(2000.0);
        Gson gson = new Gson();
        String json = gson.toJson(bike);

        MvcResult result = this.mockMvc.perform(
                        post(URL)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(json))
                .andExpect(status().is2xxSuccessful())
                .andReturn();
        Bike savedBike = null;
        savedBike = gson.fromJson(result.getResponse().getContentAsString(), Bike.class);
        assertEquals(600.0, savedBike.getPrice(), 0);
    }

    @Test
    public void whenSavedBike_CheckPrice() throws Exception {
        Bike bike = new Bike();
        bike.setCoverage(2000.0);
        Gson gson = new Gson();
        String json = gson.toJson(bike);

        MvcResult result = this.mockMvc.perform(
                        post(URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().is2xxSuccessful())
                .andReturn();
        Bike savedBike = gson.fromJson(result.getResponse().getContentAsString(), Bike.class);
        assertEquals(600.0, savedBike.getPrice(), 0);
    }


    @Test
    public void insertBikeCoverageOutOfRange() throws Exception {
        Bike bike = new Bike();
        bike.setCoverage(200000.0);
        Gson gson = new Gson();
        String json = gson.toJson(bike);

        MvcResult result = this.mockMvc.perform(
                        post(URL)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(json))
                .andExpect(status().is4xxClientError())
                .andReturn();
        Bike savedBike = gson.fromJson(result.getResponse().getContentAsString(), Bike.class);
        assertNull(savedBike);
    }

}
