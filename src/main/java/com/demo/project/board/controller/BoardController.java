package com.demo.project.board.controller;

import com.demo.project.board.dao.Board;
import com.demo.project.board.dto.BoardDTO;
import com.demo.project.board.service.BoardService;
import com.demo.project.board.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/boards")
public class BoardController {
    private final BoardService boardService;
    private final CommentService commentService;

    @Autowired
    public BoardController(BoardService boardService, CommentService commentService) {
        this.boardService = boardService;
        this.commentService = commentService;
    }

    @GetMapping("/getAllBoard")
    public List<Board> getAllBoards(@RequestParam(required = false) String type) {
        if (type == null) {
            return boardService.getAllBoards();
        } else {
            return boardService.getBoardsByType(type);
        }
    }

    @GetMapping("/getBoardById/{id}")
    public Board getBoardById(@PathVariable Long id) {
        Board board = boardService.getBoardById(id);
        board.setComments(commentService.getCommentsByBoardId(id));
        return board;
    }

    @Transactional
    @PostMapping("/create")
    public Board createBoard(@RequestBody BoardDTO dto) {
        Board board = new Board();
        board.setTitle(dto.getTitle());
        board.setContent(dto.getContent());
        board.setAuthorId(dto.getAuthorId());
        board.setCreatedAt(dto.getCreatedAt());
        board.setUpdatedAt(dto.getUpdatedAt());
        board.setUpVote(0);
        board.setType(dto.getType());
        return boardService.createBoard(board);
    }

    @Transactional
    @PutMapping("/update/{id}")
    public Board updateBoard(@PathVariable Long id, @RequestBody BoardDTO dto) {
        Board board = boardService.getBoardById(id);
        board.setTitle(dto.getTitle());
        board.setContent(dto.getContent());
        board.setUpdatedAt(dto.getUpdatedAt());
        board.setType(dto.getType());
        return boardService.updateBoard(id, board);
    }

    @Transactional
    @DeleteMapping("/delete/{id}")
    public void deleteBoard(@PathVariable Long id) {
        boardService.deleteBoard(id);
    }

    @Transactional
    @PostMapping("/{id}/upvote")
    public ResponseEntity<BoardDTO> upvoteBoard(@PathVariable Long id, @RequestParam String userNickname) {
        Board board = boardService.upvoteBoard(id, userNickname);
        BoardDTO boardDTO = boardService.convertToDTO(board);
        return ResponseEntity.ok(boardDTO);
    }

    @GetMapping("/search")
    public Page<BoardDTO> searchBoards(@RequestParam String keyword, @RequestParam(required = false) String type, @RequestParam int page) {
        int size = 10;
        return boardService.searchBoards(keyword, type, page, size);
    }

    @GetMapping("/getBoardsByPage")
    public Page<BoardDTO> getBoardsByPage(@RequestParam(required = false) String type, @RequestParam int page) {
        int size = 10;
        return boardService.getBoardsByPageAsDTO(type, page, size);
    }
}