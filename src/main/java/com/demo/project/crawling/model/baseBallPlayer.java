package com.demo.project.crawling.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Entity
@Getter
@Setter
@IdClass(baseBallPlayerId.class)
public class baseBallPlayer {
    private String team;
    private int number;
    @Id
    private String name;
    private String position;
    private String height;
    private String weight;
    @Id
    private String birthday;
    private String handedInfo;

    // equals and hashCode methods
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        baseBallPlayer that = (baseBallPlayer) o;
        return Objects.equals(name, that.name) && Objects.equals(birthday, that.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, birthday);
    }
}
