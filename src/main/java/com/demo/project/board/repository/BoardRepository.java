package com.demo.project.board.repository;

import com.demo.project.board.dao.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long>, PagingAndSortingRepository<Board, Long> {
    List<Board> findByType(String type);
    Page<Board> findByType(String type, Pageable pageable);
    Page<Board> findByTitleContainingOrContentContaining(String title, String content, Pageable pageable);
    Page<Board> findByTitleContainingOrContentContainingAndType(String title, String content, String type, Pageable pageable);
}