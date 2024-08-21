package com.demo.project.crawling.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TeamMatchDto {
    private String teamName;
    private String matchDate;
    private List<InningDto> innings;
}
