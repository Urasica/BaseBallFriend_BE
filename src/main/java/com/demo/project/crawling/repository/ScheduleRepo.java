package com.demo.project.crawling.repository;

import com.demo.project.crawling.model.schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleRepo extends JpaRepository<schedule, Long> {
    List<schedule> findByTeam1AndTeam2AndDate(String teamName1, String teamName2, String matchDate);
}
