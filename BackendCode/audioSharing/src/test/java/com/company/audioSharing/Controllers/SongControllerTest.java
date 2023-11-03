package com.company.audioSharing.Controllers;

import com.company.audioSharing.Models.Song;
import com.company.audioSharing.Repositories.SongRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class SongControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private SongRepository repo;

    @Autowired
    private ObjectMapper mapper = new ObjectMapper();

    private Song s = new Song("title",55,"url.com", 1);
    private String inputJson = "";
    @BeforeEach
    public void setup() throws Exception{
        repo.deleteAll();
        s = repo.save(s);
        inputJson = mapper.writeValueAsString(s);
    }

    @Test
    void addSong() throws Exception{
        mockMvc.perform(
                post("/songs")
                        .content(inputJson)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andDo(print()).andExpect(status().isCreated());

    }

    @Test
    void getSongById() throws Exception{
        mockMvc.perform(
                get("/songs/{id}", s.getId())
        ).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void getSongs() throws Exception{
        mockMvc.perform(
                get("/songs")
        ).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void updateSong() throws Exception{
        mockMvc.perform(
                put("/songs/")
                        .content(inputJson)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        ).andDo(print()).andExpect(status().isNoContent());
    }

    @Test
    void deleteSong() throws Exception{
        mockMvc.perform(
                delete("/songs/{id}", s.getId())
        ).andDo(print()).andExpect(status().isNoContent());
    }
}