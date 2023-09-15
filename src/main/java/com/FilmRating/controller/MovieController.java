package com.FilmRating.controller;

import com.FilmRating.model.Movie;
import com.FilmRating.model.Rating;
import com.FilmRating.service.MovieService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/movies")
public class MovieController {
    private final MovieService service;

    public MovieController(MovieService service) {
        this.service = service;

    }

    @GetMapping(params = {"!sort", "!page", "!size"})
    ResponseEntity<List<Movie>> readAllMovies() {
        return service.readAllMovies();
    }

    @GetMapping
    ResponseEntity<List<Movie>> readAllMovies(Pageable page) {
        return service.readAllMovies(page);
    }

    @PostMapping
    ResponseEntity<Movie> createMovie(@RequestBody @Valid Movie toCreate) {
        return service.createMovie(toCreate);
    }

    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity<?> updateMovie(@PathVariable int id, @RequestBody @Valid Movie movieToUpdate) {
        return service.updateMovie(id, movieToUpdate);
    }


    @DeleteMapping("/{id}")
    ResponseEntity<?> delete(@PathVariable int id) {
     return service.delete(id);
    }

    @ResponseBody
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<Rating>> readAllRatingsFromMovie(@PathVariable int id) {
        return service.readAllRatingsFromMovie(id);
    }
}
