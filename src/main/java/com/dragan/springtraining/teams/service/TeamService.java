package com.dragan.springtraining.teams.service;

import com.dragan.springtraining.teams.dto.TeamResponseDto;
import com.dragan.springtraining.teams.dto.TeamSaveDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TeamService {

    TeamResponseDto save(TeamSaveDto team);

    TeamResponseDto getById(Integer id);

    Page<TeamResponseDto> get(Pageable pageable);

    TeamResponseDto update(Integer id, TeamSaveDto team);

    void delete(Integer id);
}
