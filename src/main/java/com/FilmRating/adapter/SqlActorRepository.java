package com.FilmRating.adapter;

import com.FilmRating.model.Actor;
import com.FilmRating.model.ActorRepository;
import com.FilmRating.model.Movie;
import com.FilmRating.model.MovieRepository;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
interface SqlActorRepository extends ActorRepository, JpaRepository<Actor, Integer> {
   @Override
    @Query("SELECT DISTINCT a FROM Actor a left JOIN FETCH a.movies")
    List<Actor> findAll();

    @Override
    @Query(nativeQuery = true, value = "select count(*) > 0 from actors where id=:id")
    boolean existById(@Param("id") @NonNull Integer id);



}
