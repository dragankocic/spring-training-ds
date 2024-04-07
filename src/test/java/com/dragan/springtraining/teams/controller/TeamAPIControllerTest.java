package com.dragan.springtraining.teams.controller;

import com.dragan.springtraining.teams.dto.TeamSaveDto;
import com.dragan.springtraining.teams.service.TeamService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TeamAPIController.class)
@AutoConfigureMockMvc
class TeamAPIControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TeamService teamService;

    @Test
    void saveShouldReturnSavedTeam() throws Exception {
        TeamSaveDto teamSaveDto = new TeamSaveDto("city", "name", null);

        mockMvc.perform(post("/api/v1/teams")
                        .contentType("application/json")
                        .content(new ObjectMapper().writeValueAsString(teamSaveDto)))
                .andExpect(status().isOk());
    }
}