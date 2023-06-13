package com.example.demo;

import com.example.demo.Constants.Constants;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

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
        second_name.setStyle("-fx-prompt-text-fill: black");
        second_weapon.getItems().addAll(Constants.WEAPON);
        second_vest.getItems().addAll(Constants.VEST);
        second_command.getItems().addAll(Constants.COMMANDS);
        addBtn.setOnAction(event -> {
            Player player = new Player();
            newPlayer(player);
            db.addPlayer(player);
            playerProvider.clearList();
            playerProvider.addPlayer(player);
            for (Player players : playerProvider.getList()) {
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
            Parent root;
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("timer.fxml"));
                root = loader.load();
                Stage stage = new Stage();
                stage.setTitle(Constants.LABEL);
                stage.setScene(new Scene(root));
                stage.show();
                // Hide this current window (if this is what you want)
                ((Node) (event.getSource())).getScene().getWindow().hide();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }

    public void newPlayer(Player player) {
        if (second_name.getText().isEmpty() || second_command.getValue().isEmpty() || second_weapon.getValue() == null
                || second_vest.getValue() == null) {
            MainController.nullAlert.setAlertType(Alert.AlertType.WARNING);
            MainController.nullAlert.setTitle(Constants.ERR);
            MainController.nullAlert.setContentText("Заполните все поля!");
            MainController.nullAlert.show();
        } else {
            player.setName(second_name.getText());
            player.setCommand(second_command.getValue());
            player.setWeaponNum(second_weapon.getValue());
            player.setVestNum(second_vest.getValue());
            player.setKills(0);
            player.setDeaths(0);
            player.setPlace(0);
        }
    }
}