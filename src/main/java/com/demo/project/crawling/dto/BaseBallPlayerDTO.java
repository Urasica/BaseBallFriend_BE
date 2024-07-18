package com.demo.project.crawling.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class BaseBallPlayerDTO {
    @NotEmpty
    private String team;
    @NotNull
    private Integer number;
    @NotEmpty
    private String name;
    @NotEmpty
    private String position;
    @NotEmpty
    private String height;
    @NotEmpty
    private String weight;
    @NotEmpty
    private String birthday;
    @NotEmpty
    private String handedInfo;
}
