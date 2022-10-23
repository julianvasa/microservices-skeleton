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
public class TestSportController {

    private static final String URL = "/sports";
    @Autowired
    private MockMvc mockMvc;


    @Test
    public void getElectronics() throws Exception {
        this.mockMvc.perform(get(URL)).andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }


    @Test
    public void insertElectronicCoverageWithinRange() throws Exception {
        Sport sport = new Sport();
        sport.setCoverage(2000.0);
        Gson gson = new Gson();
        String json = gson.toJson(sport);

        MvcResult result = this.mockMvc.perform(
                        post(URL)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(json))
                .andExpect(status().is2xxSuccessful())
                .andReturn();
        Sport savedElectronic = gson.fromJson(result.getResponse().getContentAsString(), Sport.class);
        assertEquals(600.0, savedElectronic.getPrice(), 0);

    }


    @Test
    public void whenSavedElectronic_CheckPrice() throws Exception {
        Sport sport = new Sport();
        sport.setCoverage(2000.0);
        Gson gson = new Gson();
        String json = gson.toJson(sport);

        MvcResult result = this.mockMvc.perform(
                        post(URL)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(json))
                .andExpect(status().is2xxSuccessful())
                .andReturn();
        Sport savedElectronic = gson.fromJson(result.getResponse().getContentAsString(), Sport.class);
        assertEquals(600.0, savedElectronic.getPrice(), 0);
    }


    @Test
    public void insertElectronicCoverageOutOfRange() throws Exception {
        Sport sport = new Sport();
        sport.setCoverage(200000.0);
        Gson gson = new Gson();
        String json = gson.toJson(sport);

        MvcResult result = this.mockMvc.perform(
                        post(URL)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(json))
                .andExpect(status().is4xxClientError())
                .andReturn();
        Sport savedElectronic = gson.fromJson(result.getResponse().getContentAsString(), Sport.class);
        assertNull(savedElectronic);
    }

}
