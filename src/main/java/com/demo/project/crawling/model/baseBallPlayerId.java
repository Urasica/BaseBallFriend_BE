package com.demo.project.crawling.model;

import jakarta.persistence.Embeddable;

import java.util.Objects;

@Embeddable
public class baseBallPlayerId {
    private String name;
    private String birthday;

    // Default constructor
    public baseBallPlayerId() {}

    // Parameterized constructor
    public baseBallPlayerId(String name, String birthday) {
        this.name = name;
        this.birthday = birthday;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        baseBallPlayerId that = (baseBallPlayerId) o;
        return Objects.equals(name, that.name) && Objects.equals(birthday, that.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, birthday);
    }
}
