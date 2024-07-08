package com.demo.project.crawling.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class pitcherRank {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private int rank;       // 순위
    private String name;    // 선수 이름
    private String team;    // 팀 이름
    private int games;      // 경기
    private int wins;       // 승
    private int losses;     // 패
    private int save;       // 세이브
    private int hold;       // 홀드
    private String innings; // 이닝
    private int pitchCount; // 투구수
    private int hits;       // 피안타
    private int homeRuns;   // 피홈런
    private int strikeout;  // 탈삼진
    private int baseOnBall; // 사사구
    private int Runs;       // 실점
    private int EarnedRuns; // 자책점
    private double EarnedRunsAVG; // 평균 자책
    private double WHIP;
    private int QS;
}

