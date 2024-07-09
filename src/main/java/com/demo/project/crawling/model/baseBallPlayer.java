package com.demo.project.crawling.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class baseBallPlayer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String team;
    private int number;
    private String name;
    private String position;
    private String height;
    private String weight;
    private String birthday;
    private String handedInfo;
}
