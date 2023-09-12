package com.FilmRating.service;

import com.FilmRating.model.Movie;
import com.FilmRating.model.MovieRepository;
import org.springframework.stereotype.Service;

@Service
public class MovieService {
private final MovieRepository repository;

    public MovieService(MovieRepository repository) {
        this.repository = repository;
    }

    public Movie save(Movie toSave){
        return repository.save(toSave);
    }
    public void deleteById(Integer id){
        repository.deleteById(id);
    }
}
