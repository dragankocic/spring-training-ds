package com.dragan.springtraining.teams.dto;

import com.dragan.springtraining.player.entity.Player;
import org.hibernate.validator.constraints.Length;

import java.util.List;

public record TeamSaveDto(
        @Length(min = 3, max = 30, message = "City name must be between 3 and 30 characters")
        String city,
        String name,
        List<Player> players
) {
}
