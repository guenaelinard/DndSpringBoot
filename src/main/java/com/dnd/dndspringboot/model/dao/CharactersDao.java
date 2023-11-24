package com.dnd.dndspringboot.model.dao;

import com.dnd.dndspringboot.model.Characters;

import java.util.List;

public interface CharactersDao {
    List<Characters> findAll();

    Characters findById(int id);

    Characters save(Characters characters);

    void modify(Characters characters, int id);
}
