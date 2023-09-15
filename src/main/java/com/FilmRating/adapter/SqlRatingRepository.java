package com.FilmRating.adapter;

import com.FilmRating.model.Rating;
import com.FilmRating.model.RatingRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
interface SqlRatingRepository extends RatingRepository, JpaRepository<Rating, Integer> {

    @Override
    @Query(nativeQuery = true, value = "select count(*) > 0 from ratings where id=:id")
    boolean existById(@Param("id") @NonNull Integer id);

}
