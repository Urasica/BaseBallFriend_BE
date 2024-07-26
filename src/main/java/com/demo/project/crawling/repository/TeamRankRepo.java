package com.demo.project.crawling.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.demo.project.crawling.model.TeamRank;

import java.util.List;

public interface TeamRankRepo extends JpaRepository<TeamRank, Long> {
    List<TeamRank> findAllByOrderByRankAsc();
}
