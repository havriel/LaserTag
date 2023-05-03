package com.example.demo;

import java.util.ArrayList;

public class PlayerProvider {
    public ArrayList<Player> players = new ArrayList<>();

    public PlayerProvider() {
    }

    public void addPlayer(Player player){
        players.add(player);
    }

    public ArrayList<Player> getList(){
        return players;
    }

    public void clearList(){
        players.clear();
    }
}
