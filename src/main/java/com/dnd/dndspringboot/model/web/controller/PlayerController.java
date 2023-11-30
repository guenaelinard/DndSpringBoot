package com.dnd.dndspringboot.model.web.controller;

import com.dnd.dndspringboot.model.Player;
import com.dnd.dndspringboot.model.dao.PlayerDao;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class PlayerController {

    RestTemplate restTemplate;
    private final PlayerDao playerDao;

    public PlayerController(PlayerDao playerDao) {
        this.playerDao = playerDao;
        this.restTemplate = new RestTemplate();
    }

    @Operation(summary = "Gets the whole list of players")
    @RequestMapping(value = "/player", method = RequestMethod.GET)
    public List<Player> playerList() {
        return playerDao.findAll();

    }

    @Operation(summary = "Gets a player by its id")
    @GetMapping("/player/{id}")
    public Player showPlayer(@Valid @PathVariable int id) {
        return playerDao.findById(id);
    }

    @GetMapping(value = "player/hp/{hpLimit}")
    public List<Player> testRequest(@Valid @PathVariable int hpLimit) {
        return playerDao.findByHealthPointsGreaterThan(hpLimit);
    }

    @Operation(summary = "Adds a player to the list of players")
    @PostMapping("/player")
    public ResponseEntity<Player> addPlayer(@Valid @RequestBody Player player) {
        Player characterAdded = playerDao.save(player);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(characterAdded.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @Operation(summary = "Updates a player by its id")
    @PutMapping("/player/{id}")
    public Player modifyCharacter(@Valid @RequestBody Player player, @PathVariable int id) {
        return playerDao.save(player);
    }

    @Operation(summary = "Deletes a player by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deleted a player"),
            @ApiResponse(responseCode = "404", description = "Player not found", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid player id", content = @Content)})
    @DeleteMapping("/player/{id}")
    public void deleteCharacter(@Valid @PathVariable int id) {
        playerDao.deleteById(id);
    }


    @GetMapping(value = "/random-name")
    public String getRandomName() {
        String url = "https://random-word-api.herokuapp.com/word";
        System.out.println(url);
        return restTemplate.getForObject(url, String.class);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
