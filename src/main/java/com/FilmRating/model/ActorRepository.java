package com.FilmRating.model;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ActorRepository {

    List<Actor> findAll();

    Optional<Actor> findById(Integer id);

    boolean existById(Integer id);

    Actor save(Actor entity);

    Page<Actor> findAll(Pageable page);

    void deleteById(int id);


}
