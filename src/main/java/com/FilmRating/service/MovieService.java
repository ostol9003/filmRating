package com.FilmRating.service;

import com.FilmRating.model.Movie;
import com.FilmRating.model.MovieRepository;
import com.FilmRating.model.Rating;
import com.FilmRating.model.RatingRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class MovieService {
    private final MovieRepository repository;
    private final RatingRepository ratingRepository;


    public MovieService(MovieRepository repository, RatingRepository ratingRepository) {
        this.repository = repository;
        this.ratingRepository = ratingRepository;
    }

    public ResponseEntity<List<Movie>> readAllMovies() {
        log.warn("Exposing all the movies!");
        return ResponseEntity.ok(repository.findAll());
    }

    public ResponseEntity<List<Movie>> readAllMovies(Pageable page) {
        log.info("Custom pageable");
        return ResponseEntity.ok(repository.findAll(page).getContent());
    }

    public ResponseEntity<Movie> createMovie(Movie toCreate) {
        Movie result = repository.save(toCreate);
        log.info("Movie created");
        return ResponseEntity.created(URI.create("/" + result.getId())).body(result);
    }

    public ResponseEntity<?> updateMovie(int id, Movie movieToUpdate) {
        return repository.findById(id)
                .map(movie -> {
                    movie.updateFrom(movieToUpdate);
                    log.info("Movie with id:{} -> updated", id);
                    return ResponseEntity.noContent().build();
                })
                .orElseGet(() -> {
                    log.warn("Movie with id:{} -> not found", id);
                    return ResponseEntity.notFound().build();
                });
    }

    public Optional<List<Movie>> delete(int id) {
        repository.findById(id)
                .ifPresentOrElse(movie -> {
                            repository.deleteById(id);
                            log.info("Movie with id:{} -> deleted", id);
                        },
                        () -> log.info("Movie with id:{} -> not found", id));
        return Optional.ofNullable(repository.findAll());
    }

    public ResponseEntity<List<Rating>> readAllRatingsFromMovie(int id) {
        return ResponseEntity.ok(ratingRepository.findAllByMovieId(id));
    }
}
