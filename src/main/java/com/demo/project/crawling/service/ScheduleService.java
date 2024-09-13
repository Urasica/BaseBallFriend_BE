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

    // 오늘 날짜의 일정을 필터링하여 반환하는 메서드 추가
    public List<schedule> getTodaySchedule() {
        // 서버의 현재 날짜를 "MM.dd(요일)" 형식으로 변환
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM.dd(E)", Locale.KOREA);  // E는 요일을 나타냄
        String todayFormatted = today.format(formatter);

        return scheduleRepo.findAll().stream()
                .filter(s -> s.getDate().equals(todayFormatted)) // 오늘 날짜와 일치하는 일정만 필터링
                .collect(Collectors.toList());
    }
}
