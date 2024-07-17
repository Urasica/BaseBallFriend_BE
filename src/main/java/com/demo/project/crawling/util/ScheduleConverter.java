package com.demo.project.crawling.util;

import com.demo.project.crawling.model.schedule;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ScheduleConverter {
    static public schedule convertToEntity(List<String> data){
        schedule schedule = new schedule();
        schedule.setDate(data.get(0));
        schedule.setTime(data.get(1));
        schedule.setTeam1(data.get(2));
        schedule.setTeam2(data.get(3));
        schedule.setTeam1Score(data.get(4));
        schedule.setTeam2Score(data.get(5));
        schedule.setPlace(data.get(6));
        schedule.setNote(data.get(7));
        return schedule;
    }
}
