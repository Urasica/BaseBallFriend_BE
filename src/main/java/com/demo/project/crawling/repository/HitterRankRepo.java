package com.demo.project.crawling.repository;

import com.demo.project.crawling.model.HitterRank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HitterRankRepo extends JpaRepository<HitterRank, Long> {
    List<HitterRank> findAllByOrderByRankAsc();
}
