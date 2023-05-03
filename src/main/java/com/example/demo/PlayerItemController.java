package com.example.demo;

import com.example.demo.Player;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class PlayerItemController implements Initializable {

    @FXML
    private Label item_name;

    @FXML
    private Label item_weapon;

    @FXML
    private Label item_vest;

    @FXML
    private Label item_kills;

    @FXML
    private Label item_deaths;

    @FXML
    private Label item_place;
    public void setData(Player player){
        item_name.setText(player.getName());
        item_weapon.setText(String.valueOf(player.getWeaponNum()));
        item_vest.setText(String.valueOf(player.getVestNum()));
        item_kills.setText(String.valueOf(player.getKills()));
        item_deaths.setText(String.valueOf(player.getDeaths()));
        item_place.setText("#"+player.getPlace());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
