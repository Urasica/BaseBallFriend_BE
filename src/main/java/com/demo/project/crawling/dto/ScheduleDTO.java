package com.demo.project.crawling.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class ScheduleDTO {
    @NotEmpty private String date;
    @NotEmpty private String time;
    @NotEmpty private String team1;
    @NotEmpty private String team2;
    @NotEmpty private String team1Score;
    @NotEmpty private String team2Score;
    @NotEmpty private String place;
    @NotEmpty private String note;
}
