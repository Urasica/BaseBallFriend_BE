package com.demo.project.crawling.service;

import com.demo.project.crawling.model.schedule;
import com.demo.project.crawling.repository.ScheduleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
public class ScheduleService {
    @Autowired
    private ScheduleRepo scheduleRepo;

    public void updateSchedule(List<schedule> scheduleList) {
        scheduleRepo.saveAll(scheduleList);
    }

    public List<schedule> getAllSchedule() {
        return scheduleRepo.findAll();
    }

    public List<schedule> getScheduleByTeamsAndDate(String teamName1, String teamName2, String matchDate) {
        return scheduleRepo.findByTeam1AndTeam2AndDate(teamName1, teamName2, matchDate);
    }
}
