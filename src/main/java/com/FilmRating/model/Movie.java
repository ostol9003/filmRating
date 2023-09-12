package com.FilmRating.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
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

    public void updateFrom(Movie movieToUpdate) {
    this.title = movieToUpdate.getTitle();
    this.description = movieToUpdate.getDescription();
    this.productionYear = movieToUpdate.getProductionYear();
    }

}
