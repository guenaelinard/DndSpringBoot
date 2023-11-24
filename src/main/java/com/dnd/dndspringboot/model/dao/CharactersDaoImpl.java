package com.dnd.dndspringboot.model.dao;

import com.dnd.dndspringboot.model.Characters;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CharactersDaoImpl implements CharactersDao {

    public static List<Characters> characters = new ArrayList<>();

    static {
        characters.add(new Characters(1, "Guenael le gentil", "Warrior", 5));
        characters.add(new Characters(1, "Marjolin le Nécromant", "Wizard", 3));
        characters.add(new Characters(1, "Carole la Violente", "Warrior", 5));
    }


    @Override
    public List<Characters> findAll() {
        return characters;
    }

    @Override
    public Characters findById(int id) {
        for (Characters character : characters) {
            if (character.getId() == id) {
                return character;
            }
        }
        return null;
    }

    @Override
    public Characters save(Characters character) {
        characters.add(character);
        return character;
    }
}
