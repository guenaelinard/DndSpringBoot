package com.dnd.dndspringboot.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;

@Entity
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Schema(description = "ID of the given player",
            name = "id",
            type = "String",
            example = "1, 2, 3, etc.")
    private int id;
    @NotBlank(message = "Name is mandatory")
    @Size(min = 3, max = 30, message = "Name must be between 3 and 30 characters")
    private String name;
    @NotBlank(message = "Type is mandatory")
    @Size(min = 3, max = 30, message = "Type must be between 3 and 30 characters")
    private String type;
    @Min(value = 0, message = "HPs can't be lower than 0")
    @Max(value = 999, message = "HPs can't be higher than 999")
    @PositiveOrZero (message = "HPs can't be negative")
    private int healthPoints;

    //--------------------------------------- CONSTRUCTORS ---------------------------------------
    public Player() {
    }

    //--------------------------------------- METHODS ---------------------------------------


    //--------------------------------------- GET/SET ---------------------------------------

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public void setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
    }



    //--------------------------------------- TO STRING ---------------------------------------

    @Override
    public String toString() {
        return "main.java.model.Characters{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", healthPoints=" + healthPoints +
                '}';
    }
}
