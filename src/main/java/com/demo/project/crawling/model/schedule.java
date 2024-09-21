package com.demo.project.crawling.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Entity
@Setter
@Getter
@IdClass(scheduleId.class)
public class schedule {
    @Id
    private String date;
    private String time;
    @Id
    private String team1;
    private String team2;
    private String team1Score;
    private String team2Score;
    private String place;
    private String note;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        schedule schedule = (schedule) o;
        return Objects.equals(date, schedule.date) && Objects.equals(team1, schedule.team1);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, team1);
    }
}
