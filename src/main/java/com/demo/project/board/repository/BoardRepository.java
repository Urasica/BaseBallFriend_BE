package com.demo.project.board.repository;

import com.demo.project.board.dao.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
}