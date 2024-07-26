package com.demo.project.crawling.service;

import com.demo.project.crawling.model.schedule;
import com.demo.project.crawling.repository.ScheduleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
