package com.demo.project.crawling.controller;

import com.demo.project.crawling.dto.ScheduleDTO;
import com.demo.project.crawling.model.schedule;
import com.demo.project.crawling.service.ScheduleService;
import com.demo.project.crawling.util.ScheduleConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/schedule")
public class ScheduleController {
    @Autowired
    ScheduleService service;

    @PostMapping
    public ResponseEntity<String> updateSchedule(@RequestBody List<ScheduleDTO> list){
        List<schedule> schedules = list.stream().map(ScheduleConverter::convertToEntity).collect(Collectors.toList());
        service.updateSchedule(schedules);
        return ResponseEntity.ok("Schedule updated");
    }

    @GetMapping
    public ResponseEntity<List<schedule>> getSchedule(){
        return ResponseEntity.ok(service.getAllSchedule());
    }

    @GetMapping("/match")
    public ResponseEntity<List<schedule>> getScheduleByTeamsAndDate(
            @RequestParam String teamName1,
            @RequestParam String teamName2,
            @RequestParam String matchDate) {
        List<schedule> schedules = service.getScheduleByTeamsAndDate(teamName1, teamName2, matchDate);
        return ResponseEntity.ok(schedules);
    }
}
