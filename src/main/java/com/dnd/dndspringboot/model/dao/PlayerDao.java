package com.dnd.dndspringboot.model.dao;

import com.dnd.dndspringboot.model.Player;

import java.util.List;

public interface PlayerDao {
    List<Player> findAll();

    Player findById(int id);

    Player save(Player player);

    void modify(Player player, int id);

    void delete(int id);
}
