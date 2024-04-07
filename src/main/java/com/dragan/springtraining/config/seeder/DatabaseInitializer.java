package com.dragan.springtraining.config.seeder;

import com.dragan.springtraining.player.entity.Player;
import com.dragan.springtraining.player.repository.PlayerRepository;
import com.dragan.springtraining.teams.entity.Team;
import com.dragan.springtraining.teams.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.ArrayList;
import java.util.List;

@Component
public class DatabaseInitializer implements CommandLineRunner {

    private final RestClient restClient;
    private final TeamRepository teamRepository;
    private final PlayerRepository playersRepository;

    public DatabaseInitializer(
            RestClient.Builder builder,
            TeamRepository teamRepository,
            PlayerRepository playersRepository,
            @Value("${API_URL}") String baseUrl,
            @Value("${API_KEY}") String apiKey) {
        this.restClient = builder
                .baseUrl(baseUrl)
                .defaultHeader("Authorization", apiKey)
                .build();
        this.teamRepository = teamRepository;
        this.playersRepository = playersRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        var bdlTeams = restClient
                .get()
                .uri("/teams")
                .retrieve()
                .body(BallDontLieTeams.class);


        var teams = new ArrayList<Team>();
        bdlTeams.data.forEach(bdlTeam -> {
            var team = new Team();
            team.setCity(bdlTeam.getCity());
            team.setName(bdlTeam.getName());
            teams.add(team);
        });

        var savedTeams = teamRepository.saveAll(teams);


        var bldPlayers = restClient
                .get()
                .uri("/players?per_page=100")
                .retrieve()
                .body(BallDontLiePlayers.class);

        var players = new ArrayList<Player>();
        for (BallDontLiePlayer bldPlayer : bldPlayers.data) {
            var team = savedTeams.stream()
                    .filter(t -> t.getName().equals(bldPlayer.team().getName()))
                    .findFirst()
                    .orElse(null);

            if (team != null) {
                var player = new Player();
                player.setFirstName(bldPlayer.first_name());
                player.setLastName(bldPlayer.last_name());
                player.setTeam(team);
                players.add(player);
            }
        }

        playersRepository.saveAll(players);
    }

    private record BallDontLieTeams(List<Team> data) {
    }

    private record BallDontLiePlayer(String first_name, String last_name, Team team) {
    }

    private record BallDontLiePlayers(List<BallDontLiePlayer> data) {
    }
}
