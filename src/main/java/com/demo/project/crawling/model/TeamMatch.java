package com.demo.project.crawling.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
public class TeamMatch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String teamName;
    private String matchDate;

    // 양방향 관계 설정
    @OneToMany(mappedBy = "teamMatch", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Inning> innings = new ArrayList<>();

    // 중복 방지를 위해서 teamName과 matchDate를 기준으로 비교할 메서드
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeamMatch teamMatch = (TeamMatch) o;
        return Objects.equals(teamName, teamMatch.teamName) &&
                Objects.equals(matchDate, teamMatch.matchDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(teamName, matchDate);
    }

    // 연관된 Inning을 추가할 때 편의 메서드
    public void addInning(Inning inning) {
        innings.add(inning);
        inning.setTeamMatch(this);
    }

}
