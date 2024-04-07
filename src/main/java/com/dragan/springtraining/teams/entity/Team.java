package com.dragan.springtraining.teams.entity;

import com.dragan.springtraining.player.entity.Player;
import jakarta.persistence.*;

import java.util.Objects;
import java.util.Set;

@Entity
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String city;
    private String name;

    @OneToMany(mappedBy = "team",
            cascade = CascadeType.ALL)
    private Set<Player> players;

    public Team() {
    }

    public Team(Integer id, String city, String name) {
        this.id = id;
        this.city = city;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Set<Player> getPlayers() {
        return players;
    }

    public void setPlayers(Set<Player> players) {
        this.players = players;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Team team = (Team) o;

        if (!Objects.equals(id, team.id)) return false;
        if (!Objects.equals(city, team.city)) return false;
        return Objects.equals(name, team.name);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
