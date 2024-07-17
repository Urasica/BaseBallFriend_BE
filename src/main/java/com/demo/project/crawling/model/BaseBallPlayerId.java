package com.demo.project.crawling.model;

import jakarta.persistence.Embeddable;

import java.util.Objects;

@Embeddable
public class BaseBallPlayerId {
    private String name;
    private String birthday;

    // Default constructor
    public BaseBallPlayerId() {}

    // Parameterized constructor
    public BaseBallPlayerId(String name, String birthday) {
        this.name = name;
        this.birthday = birthday;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseBallPlayerId that = (BaseBallPlayerId) o;
        return Objects.equals(name, that.name) && Objects.equals(birthday, that.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, birthday);
    }
}
