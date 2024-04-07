package com.dragan.springtraining.teams.controller;

import com.dragan.springtraining.teams.dto.TeamResponseDto;
import com.dragan.springtraining.teams.dto.TeamSaveDto;
import com.dragan.springtraining.teams.service.TeamService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/teams")
public class TeamAPIController {

    private final TeamService teamService;

    public TeamAPIController(TeamService teamService) {
        this.teamService = teamService;
    }

    @PostMapping
    public ResponseEntity<TeamResponseDto> save(@Valid @RequestBody TeamSaveDto team) {
        final var savedTeam = teamService.save(team);
        return ResponseEntity.ok(savedTeam);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeamResponseDto> getById(@PathVariable Integer id) {
        final var team = teamService.getById(id);
        return ResponseEntity.ok(team);
    }

    @GetMapping
    public ResponseEntity<Page<TeamResponseDto>> get(Pageable pageable) {
        final var teams = teamService.get(pageable);
        return ResponseEntity.ok(teams);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<TeamResponseDto> update(@PathVariable Integer id, @Valid @RequestBody TeamSaveDto team) {
        final var updated = teamService.update(id, team);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        teamService.delete(id);
    }
}
