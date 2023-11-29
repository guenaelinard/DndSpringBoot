//package com.dnd.dndspringboot.model.dao;
//
//import com.dnd.dndspringboot.model.Player;
//import org.springframework.stereotype.Repository;
////import org.springframework.context.annotation.Primary;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Repository//@Primary
//public class PlayerDaoImpl implements PlayerDao {
//
//    public static List<Player> playerList = new ArrayList<>();
//
//    static {
//        playerList.add(new Player(1, "Guenael le gentil", "Warrior", 5));
//        playerList.add(new Player(2, "Marjolin le Nécromant", "Wizard", 3));
//        playerList.add(new Player(3, "Carole la Violente", "Warrior", 5));
//    }
//
//
//    @Override
//    public List<Player> findAll() {
//        return playerList;
//    }
//
//    @Override
//    public Player findById(int id) {
//        for (Player player : playerList) {
//            if (player.getId() == id) {
//                return player;
//            }
//        }
//        return null;
//    }
//
//    @Override
//    public Player save(Player player) {
//        playerList.add(player);
//        return player;
//    }
//
//    @Override
//    public void modify(Player player, int id) {
//        for (Player newplayer : playerList) {
//            if (newplayer.getId() == id) {
//                newplayer.setName(player.getName());
//                newplayer.setType(player.getType());
//                newplayer.setHealthPoints(player.getHealthPoints());
//            }
//        }
//    }
//
//    @Override
//    public void delete(int id) {
//        playerList.removeIf(player -> player.getId() == id);
//    }
//
//    public void deleteV2(int id) {
//        playerList.forEach(player -> {
//            if (player.getId() == id) {
//                playerList.remove(player);
//            }
//        });
//    }
//
//
//}
//
//
//
