package com.demo.project.crawling.repository;

import com.demo.project.crawling.model.PitcherRank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PitcherRankRepo extends JpaRepository<PitcherRank, Long> {
    List<PitcherRank> findAllByOrderByRankAsc();
}
