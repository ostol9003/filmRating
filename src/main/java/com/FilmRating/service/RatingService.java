package com.FilmRating.service;

import com.FilmRating.model.Rating;
import com.FilmRating.model.RatingRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class RatingService {
    private final RatingRepository repository;

    public RatingService(RatingRepository repository) {
        this.repository = repository;
    }

    public List<Rating> readAllRating() {
        log.warn("Exposing all the ratings!");
        return repository.findAll();
    }

    public List<Rating> readAllRating(Pageable page) {
        log.warn("Exposing all the ratings!");
        return repository.findAll(page).getContent();
    }


    public Rating save(Rating toSave) {
        log.info("Rating created");
        return repository.save(toSave);
    }

    public void deleteById(Integer id) {
        if (!repository.existById(id)) {
            log.warn("Movie with id:{} -> not found", id);
            return;
        }
        repository.deleteById(id);
        log.info("Movie with id:{} -> deleted", id);
    }

    public Optional<List<Rating>> readAllByUserName(String userName) {
        log.info("Exposing all ratings from user: {}", userName);
        return repository.findAllByUserName(userName);
    }

    public ResponseEntity<?> updateRating(int id, Rating toUpdate) {
        return repository.findById(id)
                .map(rating -> {
                    rating.updateFrom(toUpdate);
                    log.info("Rating with id:{} -> updated", id);
                    return ResponseEntity.noContent().build();
                })
                .orElseGet(() -> {
                    log.warn("Rating with id:{} -> not found", id);
                    return ResponseEntity.notFound().build();
                });
    }

}
