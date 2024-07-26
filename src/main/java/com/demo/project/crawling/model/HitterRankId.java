package com.demo.project.crawling.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import java.util.Objects;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class HitterRankId {
    private int rank;
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HitterRankId that = (HitterRankId) o;
        return rank == that.rank && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rank, name);
    }
}