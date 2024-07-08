package com.demo.project.crawling.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.demo.project.crawling.model.teamRank;

public interface teamRankRepo extends JpaRepository<teamRank, Long> {
}
