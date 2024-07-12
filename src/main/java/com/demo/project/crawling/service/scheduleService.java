package com.demo.project.crawling.service;

import com.demo.project.crawling.model.schedule;
import com.demo.project.crawling.repository.scheduleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class scheduleService {
    @Autowired
    private scheduleRepo scheduleRepo;

    public void updateSchedule(List<schedule> scheduleList) {
        scheduleRepo.deleteAll();
        scheduleRepo.saveAll(scheduleList);
    }

    public List<schedule> getAllSchedule() {
        return scheduleRepo.findAll();
    }
}
