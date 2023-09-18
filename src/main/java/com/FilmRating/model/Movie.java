package com.FilmRating.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;


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

    @Embedded
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private Audit audit = new Audit();


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "movie")
    @Fetch(FetchMode.JOIN)
    @Getter(AccessLevel.PUBLIC)
    private Set<Rating> ratings;

    public void updateFrom(Movie movieToUpdate) {
        this.title = movieToUpdate.getTitle();
        this.description = movieToUpdate.getDescription();
        this.productionYear = movieToUpdate.getProductionYear();
        this.ratings = movieToUpdate.getRatings();
    }
}
