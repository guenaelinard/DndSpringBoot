package com.dnd.dndspringboot.model.dao;

import com.dnd.dndspringboot.model.Characters;
import org.springframework.stereotype.Repository;

import java.util.List;
public interface CharactersDao {
    List<Characters> findAll();
    Characters findById(int id);
    Characters save(Characters characters);
}
