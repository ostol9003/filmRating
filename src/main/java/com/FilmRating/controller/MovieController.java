package com.FilmRating.controller;

import com.FilmRating.model.Movie;
import com.FilmRating.model.MovieRepository;
import com.FilmRating.service.MovieService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/movies")
public class MovieController {
    private final MovieRepository repository;
    private final MovieService service;

    public MovieController(MovieRepository repository, MovieService service) {
        this.repository = repository;
        this.service = service;
    }

    @GetMapping(params = {"!sort", "!page", "!size"})
    ResponseEntity<List<Movie>> readAllMovies() {
        log.warn("Exposing all the tasks!");
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping
    ResponseEntity<List<Movie>> readAllMovies(Pageable page) {
        log.info("Custom pageable");
        return ResponseEntity.ok(repository.findAll(page).getContent());
    }

    @PostMapping
    ResponseEntity<Movie> createMovie(@RequestBody @Valid Movie toCreate) {
        Movie result = repository.save(toCreate);
        log.info("Movie created");
        return ResponseEntity.created(URI.create("/" + result.getId())).body(result);
    }

    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity<?> updateMovie(@PathVariable int id,@RequestBody @Valid Movie movieToUpdate){
        if(!repository.existById(id)){
            log.warn("Movie with id:{} -> not found", id);
            return ResponseEntity.notFound().build();
        }
        repository.findById(id)
                .ifPresent(movie ->{
                    movie.updateFrom(movieToUpdate);
                } );
        log.info("Movie with id:{} -> updated", id);
        return ResponseEntity.noContent().build();

    }
}
