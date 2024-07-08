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
public class teamRank {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int rank;
    private String teamName;
    private int games;
    private int wins;
    private int losses;
    private int draws;
    private double winRate;
    private double winningMargin;
    private String continuity;
    private double onBasePercentage;
    private double sluggingPercentage;
    private String last10GameRecord;
}