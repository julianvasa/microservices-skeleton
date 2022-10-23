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
public class TestElectronicController {

    private static final String URL = "/electronics";
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getElectronics() throws Exception {
        this.mockMvc.perform(get(URL)).andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }


    @Test
    public void insertElectronicCoverageWithinRange() throws Exception {
        Electronic Electronic = new Electronic();
        Electronic.setCoverage(2000.0);
        Gson gson = new Gson();
        String json = gson.toJson(Electronic);

        MvcResult result = this.mockMvc.perform(
                        post(URL)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(json))
                .andExpect(status().is2xxSuccessful())
                .andReturn();
        com.microservices.skeleton.Electronic savedElectronic =
                gson.fromJson(result.getResponse().getContentAsString(), com.microservices.skeleton.Electronic.class);
        assertEquals(700.0, savedElectronic.getPrice(), 0);
    }

    @Test
    public void whenSavedElectronic_CheckPrice() throws Exception {
        Electronic Electronic = new Electronic();
        Electronic.setCoverage(2000.0);
        Gson gson = new Gson();
        String json = gson.toJson(Electronic);

        MvcResult result = this.mockMvc.perform(
                        post(URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().is2xxSuccessful())
                .andReturn();
        com.microservices.skeleton.Electronic savedElectronic = gson.fromJson(result.getResponse().getContentAsString(), com.microservices.skeleton.Electronic.class);
        assertEquals(700.0, savedElectronic.getPrice(), 0);
    }


    @Test
    public void insertElectronicCoverageOutOfRange() throws Exception {
        Electronic Electronic = new Electronic();
        Electronic.setCoverage(200000.0);
        Gson gson = new Gson();
        String json = gson.toJson(Electronic);

        MvcResult result = this.mockMvc.perform(
                        post(URL)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(json))
                .andExpect(status().is4xxClientError())
                .andReturn();
        com.microservices.skeleton.Electronic savedElectronic =
                gson.fromJson(result.getResponse().getContentAsString(), com.microservices.skeleton.Electronic.class);
        assertNull(savedElectronic);
    }

}
