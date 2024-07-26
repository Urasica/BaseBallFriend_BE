package com.demo.project.crawling.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Entity
@Setter
@Getter
@IdClass(TeamRankId.class)
public class TeamRank {
    @Id
    private int rank;
    @Id
    private String teamName;
    private int games;
    private int wins;
    private int losses;
    private int draws;
    private double winRate;
    private double winningMargin;
    private String continuity;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeamRank teamRank = (TeamRank) o;
        return Objects.equals(rank ,teamRank.rank) && Objects.equals(teamName ,teamRank.teamName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rank, teamName);
    }
}