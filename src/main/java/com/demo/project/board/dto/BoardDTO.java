package com.demo.project.board.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BoardDTO {
    @NotNull
    private Long id;
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
    @NotNull
    private int upVote;
    @NotEmpty
    private String type;
}
