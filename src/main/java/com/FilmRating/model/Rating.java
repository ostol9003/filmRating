package com.FilmRating.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.service.annotation.GetExchange;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "ratings")
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull(message = "Film´s rating must not be null.")
    @Min(value = 1, message = "Rating to low -> scale (1-6)")
    @Max(value = 6, message = "Rating to high -> scale (1-6)")
    private Short rating;


    private LocalDateTime date;
    @NotBlank(message = "Rating´s user must not be null")
    @Column(name = "user_name")
    private String userName;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    @Getter(AccessLevel.PRIVATE)
    @Setter(AccessLevel.PUBLIC)
    private Movie movie;


    public void updateFrom(Rating ratingToUpdate) {
        this.rating = ratingToUpdate.getRating();
        this.date = ratingToUpdate.getDate();
        this.userName = ratingToUpdate.getUserName();
        this.movie = ratingToUpdate.getMovie();
    }

}
