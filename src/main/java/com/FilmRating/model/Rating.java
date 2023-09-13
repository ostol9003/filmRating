package com.FilmRating.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "ratings")
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;



    @NotNull(message = "Film´s rating must not be null.")
    @Min(value = 1,message = "Rating to low -> scale (1-6)")
    @Max(value = 6,message = "Rating to high -> scale (1-6)")
    private Short rating;


    private LocalDateTime date;
    @NotBlank(message = "Rating´s user must not be null")
    @Column(name = "user_name")
    private String userName;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;

}
