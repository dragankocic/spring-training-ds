package com.dragan.springtraining.teams.mappers;

import com.dragan.springtraining.teams.dto.TeamResponseDto;
import com.dragan.springtraining.teams.dto.TeamSaveDto;
import com.dragan.springtraining.teams.entity.Team;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TeamMapper {

    Team teamSaveToTeam(TeamSaveDto teamSaveDto);

    TeamResponseDto teamToTeamResponseDto(Team team);

    @BeanMapping(nullValuePropertyMappingStrategy = org.mapstruct.NullValuePropertyMappingStrategy.IGNORE)
    void update(TeamSaveDto teamSaveDto, @MappingTarget Team team);
}
