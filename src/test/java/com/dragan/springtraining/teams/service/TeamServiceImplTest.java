package com.dragan.springtraining.teams.service;

import com.dragan.springtraining.teams.dto.TeamResponseDto;
import com.dragan.springtraining.teams.dto.TeamSaveDto;
import com.dragan.springtraining.teams.entity.Team;
import com.dragan.springtraining.teams.exceptions.TeamNotFoundException;
import com.dragan.springtraining.teams.mappers.TeamMapper;
import com.dragan.springtraining.teams.repository.TeamRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TeamServiceImplTest {
    @Mock
    private TeamRepository teamRepository;

    @Mock
    private TeamMapper teamMapper;

    @InjectMocks
    private TeamServiceImpl teamService;

    @Test
    @Transactional
    void givenValidTeam_whenSave_OK() {
        //given
        TeamSaveDto teamSaveDto = new TeamSaveDto("city", "name", null);
        Team team = new Team();
        Team savedTeam = new Team();
        TeamResponseDto responseDto = new TeamResponseDto(null, "city", "name");
        when(teamMapper.teamSaveToTeam(teamSaveDto)).thenReturn(team);
        when(teamRepository.save(team)).thenReturn(savedTeam);
        when(teamMapper.teamToTeamResponseDto(savedTeam)).thenReturn(responseDto);

        //when
        TeamResponseDto result = teamService.save(teamSaveDto);

        //then
        assertEquals(teamSaveDto.city(), result.city());
        assertEquals(teamSaveDto.name(), result.name());
    }

    @Test
    void givenNotExistingTeamId_whenGetById_throwsException() {
        //given
        Integer id = 1;
        //when(teamRepository.findById(id)).thenReturn(Optional.empty());

        //when-then
        assertThrows(TeamNotFoundException.class, () -> teamService.getById(id));
    }

    @Test
    void givenExistingTeamId_whenDelete_OK() {
        //given
        Integer id = 1;
        when(teamRepository.existsById(id)).thenReturn(true);

        //when
        teamService.delete(id);

        //then
        verify(teamRepository, times(1)).deleteById(id);
    }
}