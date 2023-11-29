package com.dnd.dndspringboot.model.dao;

import com.dnd.dndspringboot.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerDao extends JpaRepository<Player, Integer> {
//    List<Player> findAll();

    Player findById(int id);

    List<Player> findByHealthPointsGreaterThan(int hpLimit);

//    Player save(Player player);

//    void modify(Player player, int id);

//    void delete(int id);
}
