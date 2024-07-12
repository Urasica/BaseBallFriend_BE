package com.demo.project.crawling.repository;

import com.demo.project.crawling.model.schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface scheduleRepo extends JpaRepository<schedule, Long> {
}
