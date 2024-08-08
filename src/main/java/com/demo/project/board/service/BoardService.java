package com.demo.project.board.service;

import com.demo.project.board.dao.Board;
import com.demo.project.board.dto.BoardDTO;
import com.demo.project.board.handler.ResourceNotFoundException;
import com.demo.project.board.repository.BoardRepository;
import com.demo.project.board.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BoardService {
    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;

    @Autowired
    public BoardService(BoardRepository boardRepository, CommentRepository commentRepository) {
        this.boardRepository = boardRepository;
        this.commentRepository = commentRepository;
    }

    public List<Board> getAllBoards() {
        return boardRepository.findAll();
    }

    public List<Board> getBoardsByType(String type) {
        return boardRepository.findByType(type);
    }

    public Board getBoardById(Long id) {
        return boardRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Board not found"));
    }

    public Board createBoard(Board board) {
        board.setCreatedAt(LocalDateTime.now());
        board.setUpdatedAt(LocalDateTime.now());
        return boardRepository.save(board);
    }

    public Board updateBoard(Long id, Board updatedBoard) {
        Board board = getBoardById(id);
        board.setTitle(updatedBoard.getTitle());
        board.setContent(updatedBoard.getContent());
        board.setUpdatedAt(LocalDateTime.now());
        board.setType(updatedBoard.getType());
        return boardRepository.save(board);
    }

    public void deleteBoard(Long id) {
        Board board = getBoardById(id);
        boardRepository.delete(board);
    }

    public Board upvoteBoard(Long id) {
        Board board = getBoardById(id);
        board.setUpVote(board.getUpVote() + 1);
        return boardRepository.save(board);
    }

    public Page<BoardDTO> searchBoards(String keyword, String type, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"));
        Page<Board> boardPage;
        if (type == null) {
            boardPage = boardRepository.findByTitleContainingOrContentContaining(keyword, keyword, pageable);
        } else {
            boardPage = boardRepository.findByTitleContainingOrContentContainingAndType(keyword, keyword, type, pageable);
        }
        return boardPage.map(this::convertToDTO);
    }

    public Page<BoardDTO> getBoardsByPageAsDTO(String type, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"));
        Page<Board> boardPage;
        if (type == null) {
            boardPage = boardRepository.findAll(pageable);
        } else {
            boardPage = boardRepository.findByType(type, pageable);
        }
        return boardPage.map(this::convertToDTO);
    }

    private BoardDTO convertToDTO(Board board) {
        BoardDTO dto = new BoardDTO();
        dto.setId(board.getId());
        dto.setTitle(board.getTitle());
        dto.setContent(board.getContent());
        dto.setAuthorId(board.getAuthorId());
        dto.setCreatedAt(board.getCreatedAt());
        dto.setUpdatedAt(board.getUpdatedAt());
        dto.setUpVote(board.getUpVote());
        dto.setType(board.getType());
        dto.setCommentCount(commentRepository.countByBoardId(board.getId())); // 댓글 수 설정
        return dto;
    }
}
