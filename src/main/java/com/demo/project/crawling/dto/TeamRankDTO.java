package com.demo.project.crawling.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Setter
@Getter
public class TeamRankDTO {
    @NotNull
    @Min(0)
    private Integer rank;

    @NotEmpty
    private String teamName;

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
    private Integer draws;

    @NotNull
    @DecimalMin(value = "0.0")
    private Double winRate;

    @NotNull
    @DecimalMin(value = "0.0")
    private Double winningMargin;

    @NotEmpty
    private String continuity;
}
