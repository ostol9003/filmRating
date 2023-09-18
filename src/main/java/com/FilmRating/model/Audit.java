package com.FilmRating.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.AccessLevel;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Embeddable
class Audit {
    @DateTimeFormat(pattern = "yy-MM-dd'T'HH:mm")
    private LocalDateTime created;

    @DateTimeFormat(pattern = "yy-MM-dd'T'HH:mm")
    private LocalDateTime updated;

    @PrePersist
    void prePresist(){
        created = LocalDateTime.now();
    }
    @PreUpdate
    void preUpdate(){
        updated = LocalDateTime.now();
    }
}
