package com.demo.project.board.service;

import com.demo.project.board.dao.Board;
import com.demo.project.board.handler.ResourceNotFoundException;
import com.demo.project.board.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BoardService {
    private final BoardRepository boardRepository;

    @Autowired
    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public List<Board> getAllBoards() {
        return boardRepository.findAll();
    }

    public Board getBoardById(Long id) {
        return boardRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Board not found"));
    }

    public Board createBoard(Board board) {
        board.setCreatedAt(LocalDateTime.now());
        board.setUpdatedAt(LocalDateTime.now());
        return boardRepository.save(board);
    }

    public Board updateBoard(Long id, Board boardDetails) {
        Board board = boardRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Board not found"));

        board.setTitle(boardDetails.getTitle());
        board.setContent(boardDetails.getContent());
        board.setUpdatedAt(LocalDateTime.now());
        board.setUpVote(boardDetails.getUpVote());

        return boardRepository.save(board);
    }

    public void deleteBoard(Long id) {
        Board board = boardRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Board not found"));
        boardRepository.delete(board);
    }
}