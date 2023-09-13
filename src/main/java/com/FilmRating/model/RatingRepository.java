package com.FilmRating.model;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RatingRepository {
    List<Rating> findAll();
    Optional<Rating> findById(Integer id);
    boolean existById(Integer id);
    Rating save(Rating entity);
    Page<Rating> findAll(Pageable page);
    List<Rating> findByUserName(@Param("user_name") String user_name);
    List<Rating> findAllByMovieId(Integer id);
    void deleteById(int id);
}
