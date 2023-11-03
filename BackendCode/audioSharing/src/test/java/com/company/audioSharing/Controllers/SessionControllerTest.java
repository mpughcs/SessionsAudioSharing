package com.company.audioSharing.Controllers;

import ch.qos.logback.core.encoder.EchoEncoder;
import com.company.audioSharing.Models.Session;
import com.company.audioSharing.Repositories.SessionRepository;
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
class SessionControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper= new ObjectMapper();

    @Autowired
    private SessionRepository repo;

    Session s = new Session("text",14,"url.com","desc");
    String inputJson;
    @BeforeEach
    public void setup() throws Exception {
        repo.deleteAll();
        inputJson = mapper.writeValueAsString(s);

    }


    @Test
    void addSession() throws Exception {
        s = repo.save(s);

        mockMvc.perform(
                post("/sessions/create-session")
                        .content(inputJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());

    }

    @Test
    void getSessionById() throws Exception {
        mockMvc.perform(
                get("/sessions/{id}", s.getId())
        ).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void getSessions() throws Exception{
        mockMvc.perform(
                get("/sessions/")
        ).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void updateSession() throws Exception{
        mockMvc.perform(
                put("/sessions/")
                        .content(inputJson)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        ).andDo(print()).andExpect(status().isNoContent());

    }

    @Test
    void deleteSession() throws Exception{
        s = repo.save(s);
        mockMvc.perform(
                delete("/sessions/{id}", s.getId())
        ).andExpect(status().isNoContent());

    }
}