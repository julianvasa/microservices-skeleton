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
public class TestJewelryController {

    private static final String URL = "/jewelry";
    @Autowired
    private MockMvc mockMvc;


    @Test
    public void getListOfJewelry() throws Exception {
        this.mockMvc.perform(get(URL)).andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }


    @Test
    public void insertJewelryCoverageWithinRange() throws Exception {
        Jewelry jewelry = new Jewelry();
        jewelry.setCoverage(2000.0);
        Gson gson = new Gson();
        String json = gson.toJson(jewelry);

        MvcResult result = this.mockMvc.perform(
                        post(URL)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(json))
                .andExpect(status().is2xxSuccessful())
                .andReturn();
        Jewelry savedJewelry = gson.fromJson(result.getResponse().getContentAsString(), Jewelry.class);
        assertEquals(100.0, savedJewelry.getPrice(), 0);
    }

    @Test
    public void whenSavedJewelry_CheckPrice() throws Exception {
        Jewelry jewelry = new Jewelry();
        jewelry.setCoverage(2000.0);
        Gson gson = new Gson();
        String json = gson.toJson(jewelry);

        MvcResult result = this.mockMvc.perform(
                        post(URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().is2xxSuccessful())
                .andReturn();
        Jewelry savedJewelry = gson.fromJson(result.getResponse().getContentAsString(), Jewelry.class);
        assertEquals(100.0, savedJewelry.getPrice(), 0);
    }


    @Test
    public void insertJewelryCoverageOutOfRange() throws Exception {
        Jewelry jewelry = new Jewelry();
        jewelry.setCoverage(200000.0);
        Gson gson = new Gson();
        String json = gson.toJson(jewelry);

        MvcResult result = this.mockMvc.perform(
                        post(URL)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(json))
                .andExpect(status().is4xxClientError())
                .andReturn();
        Jewelry savedJewelry = gson.fromJson(result.getResponse().getContentAsString(), Jewelry.class);
        assertNull(savedJewelry);
    }

}
