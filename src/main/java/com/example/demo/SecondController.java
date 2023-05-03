package com.example.demo;

import java.io.IOException;

import com.example.demo.Constants.Constants;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class SecondController {
    DataBaseHandler db = new DataBaseHandler();

    PlayerProvider playerProvider = new PlayerProvider();
    @FXML
    private Button addBtn;

    @FXML
    private TextField second_name;

    @FXML
    private ChoiceBox<Integer> second_weapon;

    @FXML
    private ChoiceBox<Integer> second_vest;

    @FXML
    private ChoiceBox<String> second_command;

    @FXML
    private Button startBtn;

    @FXML
    private VBox redLayout;

    @FXML
    private VBox blueLayout;

    @FXML
    void initialize() {
        second_weapon.getItems().addAll(Constants.WEAPON);
        second_vest.getItems().addAll(Constants.VEST);
        second_command.getItems().addAll(Constants.COMMANDS);
        addBtn.setOnAction(event -> {
            Player player = new Player();
            newPlayer(player);
            db.addPlayer(player);
            playerProvider.clearList();
            playerProvider.addPlayer(player);
            for(Player players:playerProvider.getList()) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("player_item.fxml"));
                try {
                    HBox hBox = fxmlLoader.load();
                    PlayerItemController pic = fxmlLoader.getController();
                    pic.setData(players);
                    if (players.getCommand().equals("Красная")) {
                        redLayout.getChildren().add(hBox);
                    }
                    if (players.getCommand().equals("Синяя")) {
                        blueLayout.getChildren().add(hBox);
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            second_name.clear();
            second_vest.setValue(null);
            second_weapon.setValue(null);
        });

        startBtn.setOnAction(event -> {

        });

    }
    public void newPlayer(Player player) {
        player.setName(second_name.getText());
        player.setCommand(second_command.getValue());
        player.setWeaponNum(second_weapon.getValue());
        player.setVestNum(second_vest.getValue());
        player.setKills(0);
        player.setDeaths(0);
        player.setPlace(0);
    }
}