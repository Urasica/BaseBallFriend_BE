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
    @Min(0)
    private Integer games;

    @NotNull
    @Min(0)
    private Integer plateAppearance;

    @NotNull
    @Min(0)
    private Integer atBat;

    @NotNull
    @Min(0)
    private Integer hits;

    @NotNull
    @Min(0)
    private Integer doubles;

    @NotNull
    @Min(0)
    private Integer triples;

    @NotNull
    @Min(0)
    private Integer homeRuns;

    @NotNull
    @Min(0)
    private Integer runBattedIn;

    @NotNull
    @Min(0)
    private Integer runsScored;

    @NotNull
    @Min(0)
    private Integer stolenBases;

    @NotNull
    @Min(0)
    private Integer baseOnBall;

    @NotNull
    @Min(0)
    private Integer strikeOuts;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = true)
    @DecimalMax(value = "1.0", inclusive = true)
    private Double battingAVG;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = true)
    @DecimalMax(value = "1.0", inclusive = true)
    private Double onBaseAVG;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = true)
    @DecimalMax(value = "1.0", inclusive = true)
    private Double sluggingAVG;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = true)
    @DecimalMax(value = "2.0", inclusive = true)
    private Double ops;
}

