package com.demo.project.crawling.util;

import com.demo.project.crawling.dto.ScheduleDTO;
import com.demo.project.crawling.model.schedule;
import org.springframework.stereotype.Component;

@Component
public class ScheduleConverter {
    static public schedule convertToEntity(ScheduleDTO data){
        schedule schedule = new schedule();
        schedule.setDate(data.getDate());
        schedule.setTime(data.getTime());
        schedule.setTeam1(data.getTeam1());
        schedule.setTeam2(data.getTeam2());
        schedule.setTeam1Score(data.getTeam1Score());
        schedule.setTeam2Score(data.getTeam2Score());
        schedule.setPlace(data.getPlace());
        schedule.setNote(data.getNote());
        return schedule;
    }
}
