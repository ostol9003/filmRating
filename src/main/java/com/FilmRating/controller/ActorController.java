package com.FilmRating.controller;

import com.FilmRating.model.Actor;
import com.FilmRating.model.Movie;
import com.FilmRating.service.ActorService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/actors")
public class ActorController {

    private final ActorService service;

    public ActorController (ActorService service){
        this.service = service;
    }


// TODO:


    @GetMapping(params = {"!sort", "!page", "!size"})
    ResponseEntity<List<Actor>> readAllActors(){
        return service.readAllActors();
    }

    @GetMapping("/{id}")
    ResponseEntity<Optional<Actor>> readById(@PathVariable int id) {
        return service.readById(id);
    }

    @PostMapping
    ResponseEntity<Actor> createMovie(@RequestBody @Valid Actor toCreate) {
        return service.createMovie(toCreate);
    }

    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity<?> updateActor(@PathVariable int id, @RequestBody @Valid Actor actorToUpdate) {
        return service.updateActor(id, actorToUpdate);
    }


    @DeleteMapping("/{id}")
    ResponseEntity<?> delete(@PathVariable int id) {
        return service.delete(id);
    }
}
