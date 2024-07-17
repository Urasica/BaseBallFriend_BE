package com.demo.project.crawling.repository;

import com.demo.project.crawling.model.HitterRank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HitterRankRepo extends JpaRepository<HitterRank, Long> {
}
