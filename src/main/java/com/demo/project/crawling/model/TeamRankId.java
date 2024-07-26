package com.demo.project.crawling.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class TeamRankId {
    private int rank;
    private String teamName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeamRankId teamRankId = (TeamRankId) o;
        return Objects.equals(rank, teamRankId.rank) && Objects.equals(teamName,teamRankId.teamName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rank, teamName);
    }
}
