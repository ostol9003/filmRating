package com.FilmRating.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.Set;


@Data
@Entity
@Table(name = "movies")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Film´s title must not be empty")
    private String title;

    private String description;
    private int productionYear;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "movie")
    @Fetch(FetchMode.JOIN)
    @Getter(AccessLevel.PUBLIC)
    private Set<Rating> ratings;

    public void updateFrom(Movie movieToUpdate) {
        this.title = movieToUpdate.getTitle();
        this.description = movieToUpdate.getDescription();
        this.productionYear = movieToUpdate.getProductionYear();
    }
}
