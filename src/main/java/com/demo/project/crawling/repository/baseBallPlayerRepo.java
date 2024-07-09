package com.demo.project.crawling.repository;

import com.demo.project.crawling.model.baseBallPlayer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface baseBallPlayerRepo extends JpaRepository<baseBallPlayer,Integer> {
    Optional<baseBallPlayer> findByNameAndBirthday(String name, String birthday);
}
