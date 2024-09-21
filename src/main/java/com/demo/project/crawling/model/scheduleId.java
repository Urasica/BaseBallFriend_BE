package com.demo.project.crawling.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class scheduleId {
    private String date;
    private String team1;
    private String time;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        scheduleId scheduleId = (scheduleId) o;
        return Objects.equals(date, scheduleId.date) && Objects.equals(team1, scheduleId.team1)
                && Objects.equals(time, scheduleId.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, team1, time);
    }
}
