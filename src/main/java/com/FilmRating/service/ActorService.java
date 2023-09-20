package com.FilmRating.service;

import com.FilmRating.model.Actor;
import com.FilmRating.model.ActorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ActorService {
    private final ActorRepository repository;

    public ActorService(ActorRepository repository) {
        this.repository = repository;
    }

    public ResponseEntity<List<Actor>> readAllActors() {
        log.warn("Exposing all the Actors!");
        return ResponseEntity.ok(repository.findAll());
    }

    public ResponseEntity<Optional<Actor>> readById(int id) {
        log.info("Read Actor by id: {}", id);
        return ResponseEntity.ok(repository.findById(id));
    }

    public ResponseEntity<Actor> createMovie(Actor toCreate) {
        Actor result = repository.save(toCreate);
        log.info("Actor created");
        return ResponseEntity.created(URI.create("/" + result.getId())).body(result);
    }

    public ResponseEntity<?> updateActor(int id, Actor ActorToUpdate) {
        return repository.findById(id)
                .map(Actor -> {
                    Actor.updateFrom(ActorToUpdate);
                    log.info("Actor with id:{} -> updated", id);
                    return ResponseEntity.noContent().build();
                })
                .orElseGet(() -> {
                    log.warn("Actor with id:{} -> not found", id);
                    return ResponseEntity.notFound().build();
                });
    }

    public ResponseEntity<?> delete(int id) {
        return repository.findById(id)
                .map(movie -> {
                    log.info("Actor with id:{} -> deleted", id);
                    repository.deleteById(id);
                    return ResponseEntity.noContent().build();
                })
                .orElseGet(
                        () -> {
                            log.info("Actor with id:{} -> not found", id);
                            return ResponseEntity.notFound().build();
                        });

    }
}
