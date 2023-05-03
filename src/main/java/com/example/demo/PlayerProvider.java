package com.example.demo;

import java.util.ArrayList;

public class PlayerProvider {
    private ArrayList<Player> players;

    public PlayerProvider() {
        players = new ArrayList<>();
    }

    public void addPlayer(Player player){
        this.players.add(player);
    }

    public ArrayList<Player> getList(){
        return players;
    }

    public void clearList(){
        players.clear();
    }
}
