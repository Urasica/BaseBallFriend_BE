package com.demo.project.crawling.repository;

import com.demo.project.crawling.model.BaseBallPlayer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BaseBallPlayerRepo extends JpaRepository<BaseBallPlayer,Integer> {
    List<BaseBallPlayer> findByTeam(String team);
}
