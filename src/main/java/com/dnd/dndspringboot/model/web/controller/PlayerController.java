package com.dnd.dndspringboot.model.web.controller;

import java.net.URI;
import java.util.List;
import java.util.Objects;

import com.dnd.dndspringboot.model.dao.PlayerDao;
import com.dnd.dndspringboot.model.Player;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class PlayerController {

    private final PlayerDao playerDao;

    public PlayerController(PlayerDao playerDao) {
        this.playerDao = playerDao;
    }

    @GetMapping("/player")
    public List<Player> playerList() {
        return playerDao.findAll();
    }

    @GetMapping("/player/{id}")
    public Player showPlayer(@PathVariable int id) {
        return playerDao.findById(id);
    }

    @PostMapping("/player")
    public ResponseEntity<Player> addPlayer(@RequestBody Player character) {
        Player characterAdded = playerDao.save(character);
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

    @PutMapping("/player/{id}")
    public void modifyCharacter(@RequestBody Player player, @PathVariable int id){
        playerDao.modify(player, id);
    }

    @DeleteMapping("/player/{id}")
    public void deleteCharacter(@PathVariable int id){
        playerDao.delete(id);
    }
}
