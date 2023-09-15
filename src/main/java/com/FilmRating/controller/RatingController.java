package com.FilmRating.controller;

import com.FilmRating.model.Rating;
import com.FilmRating.service.RatingService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/ratings")
public class RatingController {
    private final RatingService service;

    public RatingController(RatingService service) {
        this.service = service;
    }

    @GetMapping(params = {"!sort", "!page", "!size"})
    ResponseEntity<List<Rating>> readAllRatings() {
        return ResponseEntity.ok(service.readAllRating());
    }

    @GetMapping
    ResponseEntity<List<Rating>> readAllRatings(Pageable page) {
        return ResponseEntity.ok(service.readAllRating(page));
    }

    @PostMapping
    ResponseEntity<Rating> createRating(@RequestBody @Valid Rating toCreate) {
        Rating result = service.save(toCreate);
        return ResponseEntity.created(URI.create("/" + result.getId())).body(result);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        service.deleteById(id);
    }

    @GetMapping("/{user_name}")
    ResponseEntity<Optional<List<Rating>>> readAllByUserName(@PathVariable String user_name) {
        return ResponseEntity.ok(service.readAllByUserName(user_name));
    }

    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity<?> updateRating(@PathVariable int id, @RequestBody @Valid Rating ratingToUpdate) {
        return service.updateRating(id, ratingToUpdate);
    }


}
