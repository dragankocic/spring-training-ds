package com.dragan.springtraining.teams.repository;

import com.dragan.springtraining.teams.dto.TeamResponseDto;
import com.dragan.springtraining.teams.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface TeamRepository extends JpaRepository<Team, Integer> {

    @Query("SELECT t FROM Team t WHERE t.name = ?1")
    Team tryQuery(String fullName);

    @Query(value = "SELECT * FROM team WHERE fullName = ?1", nativeQuery = true)
    Team getTryNativeQuery(String fullName);

    Team findByNameIgnoreCase(String name);

    Optional<TeamResponseDto> findTeamResponseDtoById(Integer id);
}
