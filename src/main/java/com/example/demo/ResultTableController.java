package com.example.demo;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class ResultTableController {
    @FXML
    private VBox redResult;

    @FXML
    private VBox blueResult;

    DataBaseHandler handler = new DataBaseHandler();
    @FXML
    void initialize() {
        getRed();
        getBlue();
    }
    private void getRed(){
        for (Player players : handler.getRedTable()) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("player_item.fxml"));
            try {
                HBox hBox = fxmlLoader.load();
                PlayerItemController pic = fxmlLoader.getController();
                pic.setData(players);
                redResult.getChildren().add(hBox);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void getBlue(){
        for (Player players : handler.getBlueTable()) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("player_item.fxml"));
            try {
                HBox hBox = fxmlLoader.load();
                PlayerItemController pic = fxmlLoader.getController();
                pic.setData(players);
                blueResult.getChildren().add(hBox);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
