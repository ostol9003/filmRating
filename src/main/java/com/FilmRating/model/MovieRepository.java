package com.FilmRating.model;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface MovieRepository {

    List<Movie> findAll();

    Optional<Movie> findById(Integer id);

    boolean existById(Integer id);

    Movie save(Movie entity);

    Page<Movie> findAll(Pageable page);

    void deleteById(int id);


}
