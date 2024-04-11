package com.dragan.springtraining.teams.controller;

import com.dragan.springtraining.teams.dto.TeamResponseDto;
import com.dragan.springtraining.teams.service.TeamService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/v1/teams")
public class TeamMVCController {

    private final TeamService teamService;

    public TeamMVCController(TeamService teamService) {
        this.teamService = teamService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public String getTeams(Model model) {
        Page<TeamResponseDto> teams = teamService.get(PageRequest.of(0, 10));
        model.addAttribute("teams", teams.getContent());
        return "list";
    }

}
