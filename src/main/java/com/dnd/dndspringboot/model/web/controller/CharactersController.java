package com.dnd.dndspringboot.model.web.controller;

import java.net.URI;
import java.util.List;
import java.util.Objects;

import com.dnd.dndspringboot.model.dao.CharactersDao;
import com.dnd.dndspringboot.model.Characters;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class CharactersController {

    private final CharactersDao charactersDao;

    public CharactersController(CharactersDao charactersDao) {
        this.charactersDao = charactersDao;
    }

    @GetMapping("/Characters")
    public List<Characters> charactersList() {
        return charactersDao.findAll();
    }

    @GetMapping("/Characters/{id}")
    public Characters showCharacter(@PathVariable int id) {
        return charactersDao.findById(id);
    }

    @PostMapping("/Characters")
    public ResponseEntity<Characters> addCharacter(@RequestBody Characters character) {
        Characters characterAdded = charactersDao.save(character);
        if (Objects.isNull(characterAdded)) {
            return ResponseEntity.noContent().build();
        }
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(characterAdded.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }
}
