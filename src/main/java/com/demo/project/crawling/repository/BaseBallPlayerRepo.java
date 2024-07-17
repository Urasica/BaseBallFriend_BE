package com.demo.project.crawling.repository;

import com.demo.project.crawling.model.BaseBallPlayer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BaseBallPlayerRepo extends JpaRepository<BaseBallPlayer,Integer> {
    Optional<BaseBallPlayer> findByNameAndBirthday(String name, String birthday);
}
