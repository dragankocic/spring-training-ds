package com.dragan.springtraining.player.repository;

import com.dragan.springtraining.player.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Integer> {
}
