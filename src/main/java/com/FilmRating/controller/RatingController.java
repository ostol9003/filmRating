package com.FilmRating.controller;

import com.FilmRating.model.Rating;
import com.FilmRating.model.RatingRepository;
import com.FilmRating.service.RatingService;
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
@RequestMapping("/ratings")
public class RatingController {
    private final RatingRepository repository;
    private final RatingService service;

    public RatingController(RatingRepository repository, RatingService service) {
        this.repository = repository;
        this.service = service;
    }

    @GetMapping(params = {"!sort", "!page", "!size"})
    ResponseEntity<List<Rating>> readAllRatings() {
        log.warn("Exposing all the ratings!");
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping
    ResponseEntity<List<Rating>> readAllRatings(Pageable page) {
        log.info("Custom pageable");
        return ResponseEntity.ok(repository.findAll(page).getContent());
    }

    @PostMapping
    ResponseEntity<Rating> createRating(@RequestBody @Valid Rating toCreate) {
        Rating result = repository.save(toCreate);
        log.info("Rating saved");
        return ResponseEntity.created(URI.create("/" + result.getId())).body(result);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        if (!repository.existById(id)) {
            log.warn("Movie with id:{} -> not found", id);
            return;
        }
        service.deleteById(id);
        log.info("Movie with id:{} -> deleted", id);
    }

    @GetMapping("/{user_name}")
    ResponseEntity<List<Rating>> readAllByUserName(@PathVariable String user_name) {
        log.info("Exposing all ratings from user: {}", user_name);
        return ResponseEntity.ok(repository.findByUserName(user_name));
    }

    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity<?> updateRating(@PathVariable int id, @RequestBody @Valid Rating ratingToUpdate) {
        if (!repository.existById(id)) {
            log.warn("Rating with id:{} -> not found", id);
            return ResponseEntity.notFound().build();
        }
        repository.findById(id)
                .ifPresent(rating -> {
                    rating.updateFrom(ratingToUpdate);
                });
        log.info("Rating with id:{} -> updated", id);
        return ResponseEntity.noContent().build();
    }


}
