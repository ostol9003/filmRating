package com.FilmRating.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Data
@Entity
@Table(name = "actors")
public class Actor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Actor´s name must not be null")
    private String name;
    @NotBlank(message = "Actor´s last name must not be null")
    private String lastName;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;

    @ManyToMany(mappedBy = "actors")
    private Set<Movie> movies = new HashSet<>();

    @Embedded
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private Audit audit = new Audit();

    public void updateFrom(Actor toUpdate) {
        this.name = toUpdate.getName();
        this.lastName = toUpdate.getLastName();
        this.birthDate = toUpdate.getBirthDate();
        this.movies = toUpdate.getMovies();
    }

}
