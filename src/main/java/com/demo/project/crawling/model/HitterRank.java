package com.demo.project.crawling.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Entity
@IdClass(HitterRankId.class) // 복합 키 클래스 지정
@Getter
@Setter
public class HitterRank {
    @Id
    private int rank;            // 순위
    @Id
    private String name;         // 선수 이름
    private String team;         // 팀 이름
    private int games;           // 경기
    private int plateAppearance; // 타석수
    private int atBat;           // 타수
    private int hits;            // 안타
    private int doubles;         // 2루타
    private int triples;         // 3루타
    private int homeRuns;        // 홈런
    private int runBattedIn;     // 타점
    private int runsScored;      // 득점
    private int stolenBases;     // 도루
    private int baseOnBall;      // 사사구
    private float strikeOuts;    // 삼진
    private double battingAVG;   // 타율
    private double onBaseAVG;    // 출루율
    private double sluggingAVG;  // 장타율
    private double ops;          // OPS

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HitterRank that = (HitterRank) o;
        return rank == that.rank && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rank, name);
    }
}
