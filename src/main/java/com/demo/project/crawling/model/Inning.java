package com.demo.project.crawling.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Inning {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int inningNumber;

    @ElementCollection
    private List<String> details = new ArrayList<>();

    // Many-to-One 관계 설정
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_match_id")
    private TeamMatch teamMatch;
}

