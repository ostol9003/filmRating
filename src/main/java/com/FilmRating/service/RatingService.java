package com.FilmRating.service;

import com.FilmRating.model.Rating;
import com.FilmRating.model.RatingRepository;
import org.springframework.stereotype.Service;

@Service
public class RatingService {
    private final RatingRepository repository;

    public RatingService(RatingRepository repository) {
        this.repository = repository;
    }

    public Rating save(Rating toSave) {
        return repository.save(toSave);
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }
}
