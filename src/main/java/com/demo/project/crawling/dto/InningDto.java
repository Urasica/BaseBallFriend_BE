package com.demo.project.crawling.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class InningDto {
    private int inningNumber;
    private List<String> details;
}
