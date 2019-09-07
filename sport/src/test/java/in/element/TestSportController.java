package in.element;

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
public class TestSportController {
    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getElectronics() throws Exception {
        this.mockMvc.perform(get("/sports")).andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    public void insertElectronicCoverageWithinRange() throws Exception {
        String url = "/sports";

        Sport sport = new Sport();
        sport.setCoverage(2000.0);

        Gson gson = new Gson();
        String json = gson.toJson(sport);

        MvcResult result = this.mockMvc.perform(
                post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().is2xxSuccessful())
                .andReturn();
    }

    @Test
    public void whenSavedElectronic_CheckPrice() throws Exception {
        String url = "/sports";

        Sport sport = new Sport();
        sport.setCoverage(2000.0);

        Gson gson = new Gson();
        String json = gson.toJson(sport);

        MvcResult result = this.mockMvc.perform(
                post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().is2xxSuccessful())
                .andReturn();
        Sport savedElectronic = gson.fromJson(result.getResponse().getContentAsString(), Sport.class);
        assertEquals(600.0, savedElectronic.getPrice(), 0);
    }

    @Test
    public void insertElectronicCoverageOutOfRange() throws Exception {
        String url = "/sports";

        Sport sport = new Sport();
        sport.setCoverage(200000.0);

        Gson gson = new Gson();
        String json = gson.toJson(sport);

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
