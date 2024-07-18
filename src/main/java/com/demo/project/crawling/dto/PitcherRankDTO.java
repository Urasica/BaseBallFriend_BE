package com.demo.project.crawling.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonProperty;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PitcherRankDTO {
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
    private Integer wins;

    @NotNull
    @Min(0)
    private Integer losses;

    @NotNull
    @Min(0)
    private Integer save;

    @NotNull
    @Min(0)
    private Integer hold;

    @NotEmpty
    private String innings;

    @NotNull
    @Min(0)
    private Integer pitchCount;

    @NotNull
    @Min(0)
    private Integer hits;

    @NotNull
    @Min(0)
    private Integer homeRuns;

    @NotNull
    @Min(0)
    private Integer strikeout;

    @NotNull
    @Min(0)
    private Integer baseOnBall;

    @JsonProperty("Runs")
    @NotNull
    @Min(0)
    private Integer runs;

    @JsonProperty("EarnedRuns")
    @NotNull
    @Min(0)
    private Integer earnedRuns;

    @JsonProperty("EarnedRunsAVG")
    @NotNull
    @DecimalMin(value = "0.0")
    @DecimalMax(value = "10.0")
    private Double earnedRunsAVG;

    @JsonProperty("WHIP")
    @NotNull
    @DecimalMin(value = "0.0")
    @DecimalMax(value = "10.0")
    private Double whip;

    @NotNull
    @Min(0)
    @JsonProperty("QS")
    private Integer qs;
}
