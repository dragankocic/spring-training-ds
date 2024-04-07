package com.dragan.springtraining.teams.service;

import com.dragan.springtraining.teams.dto.TeamResponseDto;
import com.dragan.springtraining.teams.dto.TeamSaveDto;
import com.dragan.springtraining.teams.exceptions.TeamNotFoundException;
import com.dragan.springtraining.teams.mappers.TeamMapper;
import com.dragan.springtraining.teams.repository.TeamRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TeamServiceImpl implements TeamService {

    private final TeamRepository teamRepository;
    private final TeamMapper teamMapper;

    public TeamServiceImpl(TeamRepository teamRepository, TeamMapper teamMapper) {
        this.teamRepository = teamRepository;
        this.teamMapper = teamMapper;
    }

    @Override
    public TeamResponseDto save(TeamSaveDto teamSaveDto) {
        var team = teamMapper.teamSaveToTeam(teamSaveDto);

        var savedTeam = teamRepository.save(team);

        return teamMapper.teamToTeamResponseDto(savedTeam);
    }

    @Override
    public TeamResponseDto getById(Integer id) {
        return teamRepository.findTeamResponseDtoById(id)
                .orElseThrow(() -> new TeamNotFoundException(id));
    }

    @Override
    public Page<TeamResponseDto> get(Pageable pageable) {
        return teamRepository.findAll(pageable).map(teamMapper::teamToTeamResponseDto);
    }

    @Override
    public TeamResponseDto update(Integer id, TeamSaveDto teamDto) {
        var dbTeam = teamRepository.findById(id).orElseThrow(() -> new TeamNotFoundException(id));

        teamMapper.update(teamDto, dbTeam);

        var savedDbTeam = teamRepository.save(dbTeam);

        return teamMapper.teamToTeamResponseDto(savedDbTeam);
    }

    @Override
    public void delete(Integer id) {
        if (!teamRepository.existsById(id)) {
            throw new TeamNotFoundException(id);
        }

        teamRepository.deleteById(id);
    }
}
