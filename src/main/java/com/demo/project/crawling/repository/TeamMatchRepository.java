package com.demo.project.crawling.repository;

import com.demo.project.crawling.model.TeamMatch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeamMatchRepository extends JpaRepository<TeamMatch, Long> {
    TeamMatch findByTeamNameAndMatchDate(String teamName, String matchDate);
}

