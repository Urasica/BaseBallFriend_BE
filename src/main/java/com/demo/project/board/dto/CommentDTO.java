package com.demo.project.board.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDTO {
    private String content;
    private String authorId;
}