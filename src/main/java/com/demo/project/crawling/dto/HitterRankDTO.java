package com.demo.project.crawling.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter
@Setter
public class HitterRankDTO {
    @NotNull
    @Min(1)
    private Integer rank;

    @NotEmpty
    private String name;

    @NotEmpty
    private String team;

    @NotNull
    @PositiveOrZero
    private Integer games;

    @NotNull
    @PositiveOrZero
    private Integer plateAppearance;

    @NotNull
    @PositiveOrZero
    private Integer atBat;

    @NotNull
    @PositiveOrZero
    private Integer hits;

    @NotNull
    @PositiveOrZero
    private Integer doubles;

    @NotNull
    @PositiveOrZero
    private Integer triples;

    @NotNull
    @PositiveOrZero
    private Integer homeRuns;

    @NotNull
    @PositiveOrZero
    private Integer runBattedIn;

    @NotNull
    @PositiveOrZero
    private Integer runsScored;

    @NotNull
    @PositiveOrZero
    private Integer stolenBases;

    @NotNull
    @PositiveOrZero
    private Integer baseOnBall;

    @NotNull
    @PositiveOrZero
    private Integer strikeOuts;

    @NotNull
    @DecimalMin(value = "0.0")
    @DecimalMax(value = "1.0")
    private Double battingAVG;

    @NotNull
    @DecimalMin(value = "0.0")
    @DecimalMax(value = "1.0")
    private Double onBaseAVG;

    @NotNull
    @DecimalMin(value = "0.0")
    @DecimalMax(value = "1.0")
    private Double sluggingAVG;

    @NotNull
    @DecimalMin(value = "0.0")
    @DecimalMax(value = "2.0")
    private Double ops;
}

