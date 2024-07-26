package com.demo.project.board.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
public class BoardDTO {
    @NotEmpty
    private String title;
    @NotEmpty
    private String content;
    @NotEmpty
    private String authorId;
    @NotEmpty
    private LocalDateTime createdAt;
    @NotEmpty
    private LocalDateTime updatedAt;
}
