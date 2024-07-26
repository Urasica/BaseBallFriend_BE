package com.demo.project.board.controller;

import com.demo.project.board.dao.Board;
import com.demo.project.board.dto.BoardDTO;
import com.demo.project.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/boards")
public class BoardController {
    private final BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/getAllBoard")
    public List<Board> getAllBoards() {
        return boardService.getAllBoards();
    }

    @GetMapping("/getBoardById/{id}")
    public Board getBoardById(@PathVariable Long id) {
        return boardService.getBoardById(id);
    }

    @PostMapping("/create")
    public Board createBoard(@RequestBody BoardDTO dto) {
        Board board = new Board();
        board.setTitle(dto.getTitle());
        board.setContent(dto.getContent());
        board.setAuthorId(dto.getAuthorId());
        board.setCreatedAt(dto.getCreatedAt());
        board.setUpdatedAt(dto.getUpdatedAt());
        board.setUpVote(0);
        return boardService.createBoard(board);
    }

    @PutMapping("/update/{id}")
    public Board updateBoard(@PathVariable Long id, @RequestBody BoardDTO dto) {
        Board board = boardService.getBoardById(id);
        board.setTitle(dto.getTitle());
        board.setContent(dto.getContent());
        board.setUpdatedAt(dto.getUpdatedAt());
        return boardService.updateBoard(id, board);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteBoard(@PathVariable Long id) {
        boardService.deleteBoard(id);
    }

    @PostMapping("/{id}/upvote")
    public Board upvoteBoard(@PathVariable Long id) {
        return boardService.upvoteBoard(id);
    }
}

