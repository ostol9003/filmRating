package com.FilmRating.adapter;

import com.FilmRating.model.Movie;
import com.FilmRating.model.MovieRepository;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
interface SqlMovieRepository extends MovieRepository, JpaRepository<Movie, Integer> {
    @Override
    @Query("SELECT DISTINCT m FROM Movie m left JOIN FETCH m.ratings")
    List<Movie> findAll();

    @Override
    @Query(nativeQuery = true, value = "select count(*) > 0 from movies where id=:id")
    boolean existById(@Param("id") @NonNull Integer id);



}
