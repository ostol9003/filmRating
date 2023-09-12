package com.FilmRating.adapter;

import com.FilmRating.model.Movie;
import com.FilmRating.model.MovieRepository;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
interface SqlMovieRepository extends MovieRepository, JpaRepository<Movie, Integer> {

    @Override
    @Query(nativeQuery = true, value = "select count(*) > 0 from movies where id=:id")
    boolean existById(@Param("id") @NonNull Integer id);

}
