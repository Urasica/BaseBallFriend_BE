package com.demo.project.board.repository;

import com.demo.project.board.dao.Recommendation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RecommendationRepository extends JpaRepository<Recommendation, Long> {
    Optional<Recommendation> findByBoardIdAndUserNickname(Long boardId, String userNickname);
}
