package com.example.demo;

import java.util.concurrent.atomic.AtomicInteger;

public class Player {
    private String name;
    private int weaponNum;
    private int vestNum;
    private String command;
    private int kills;
    private int deaths;
    private int place;

    public Player(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWeaponNum() {
        return weaponNum;
    }

    public void setWeaponNum(int weaponNum) {
        this.weaponNum = weaponNum;
    }

    public int getVestNum() {
        return vestNum;
    }

    public void setVestNum(int vestNum) {
        this.vestNum = vestNum;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public int getKills() {
        return kills;
    }

    public void setKills(int kills) {
        this.kills = kills;
    }

    public int getDeaths() {
        return deaths;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public int getPlace() {
        return place;
    }

    public void setPlace(int place) {
        this.place = place;
    }
}
