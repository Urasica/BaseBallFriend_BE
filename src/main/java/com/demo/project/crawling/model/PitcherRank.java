package com.demo.project.crawling.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Entity
@IdClass(PitcherRankId.class) // 복합 키 클래스 지정
@Getter
@Setter
public class PitcherRank {
    @Id
    private int rank;            // 순위
    @Id
    private String name;         // 선수 이름
    private String team;         // 팀 이름
    private int games;           // 경기
    private int wins;            // 승
    private int losses;          // 패
    private int save;            // 세이브
    private int hold;            // 홀드
    private String innings;      // 이닝
    private int pitchCount;      // 투구수
    private int hits;            // 피안타
    private int homeRuns;        // 피홈런
    private int strikeout;       // 탈삼진
    private int baseOnBall;      // 사사구
    private int Runs;            // 실점
    private int EarnedRuns;      // 자책점
    private double EarnedRunsAVG; // 평균 자책
    private double WHIP;
    private int QS;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PitcherRank that = (PitcherRank) o;
        return rank == that.rank && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rank, name);
    }
}
