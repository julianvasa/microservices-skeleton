package com.microservices.skeleton;

import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.nio.charset.Charset;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TestJewelryController {
    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getJewelrys() throws Exception {
        this.mockMvc.perform(get("/jewelry")).andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    public void insertJewelryCoverageWithinRange() throws Exception {
        String url = "/jewelry";

        Jewelry jewelry = new Jewelry();
        jewelry.setCoverage(2000.0);

        Gson gson = new Gson();
        String json = gson.toJson(jewelry);

        MvcResult result = this.mockMvc.perform(
                post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().is2xxSuccessful())
                .andReturn();
    }

    @Test
    public void whenSavedJewelry_CheckPrice() throws Exception {
        String url = "/jewelry";

        Jewelry jewelry = new Jewelry();
        jewelry.setCoverage(2000.0);

        Gson gson = new Gson();
        String json = gson.toJson(jewelry);

        MvcResult result = this.mockMvc.perform(
                post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().is2xxSuccessful())
                .andReturn();
        Jewelry savedJewelry = gson.fromJson(result.getResponse().getContentAsString(), Jewelry.class);
        assertEquals(100.0, savedJewelry.getPrice(), 0);
    }

    @Test
    public void insertJewelryCoverageOutOfRange() throws Exception {
        String url = "/jewelry";

        Jewelry jewelry = new Jewelry();
        jewelry.setCoverage(200000.0);

        Gson gson = new Gson();
        String json = gson.toJson(jewelry);

        MvcResult result = this.mockMvc.perform(
                post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @SpringBootApplication
    static class Configuration {
    }
}
