package com.dnd.dndspringboot.model.web.controller;

import java.net.URI;
import java.util.List;
import java.util.Objects;

import com.dnd.dndspringboot.model.dao.PlayerDao;
import com.dnd.dndspringboot.model.Player;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class PlayerController {

    private final PlayerDao playerDao;

    public PlayerController(PlayerDao playerDao) {
        this.playerDao = playerDao;
    }

    @Operation(summary = "Gets the whole list of players")
    @GetMapping("/player")
    public List<Player> playerList() {
        return playerDao.findAll();
    }

    @Operation(summary = "Gets a player by its id")
    @GetMapping("/player/{id}")
    public Player showPlayer(@PathVariable @Schema(description = "ID of the given player",
            name = "id",
            type = "String",
            example = "1, 2, 3, etc.") int id) {
        return playerDao.findById(id);
    }

    @Operation(summary = "Adds a player to the list of players")
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

    @Operation(summary = "Updates a player by its id")
    @PutMapping("/player/{id}")
    public void modifyCharacter(@RequestBody Player player, @PathVariable @Schema(description = "ID of the given player",
            name = "id",
            type = "String",
            example = "1, 2, 3") int id) {
        playerDao.modify(player, id);
    }

    @Operation(summary = "Deletes a player by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deleted a player"),
            @ApiResponse(responseCode = "404", description = "Player not found", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid player id", content = @Content)})
    @DeleteMapping("/player/{id}")
    public void deleteCharacter(@PathVariable @Schema(description = "ID of the given player",
            name = "id",
            type = "String",
            example = "1, 2, 3, etc.") int id) {
        playerDao.delete(id);
    }
}
