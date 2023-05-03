package com.example.demo;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.example.demo.Constants.Constants;
import com.fazecast.jSerialComm.SerialPort;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class MainController {
    public static Alert nullAlert = new Alert(Alert.AlertType.NONE);
    public static SerialPort sp;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button nextBtn;

    @FXML
    private CheckBox comCheck;

    @FXML
    private TextField comText;

    @FXML
    void initialize() {
        nextBtn.setOnAction(event -> {
            if(comText.getText().isEmpty()){
                nullAlert.setAlertType(Alert.AlertType.WARNING);
                nullAlert.setTitle(Constants.ERR);
                nullAlert.setContentText("Вы не указали COM порт!");
                nullAlert.show();
            }else {
                if(connection(comText.getText(), Constants.RATE)){
                    Parent root;
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("SecondPage.fxml"));
                        root = loader.load();
                        Stage stage = new Stage();
                        stage.setTitle(Constants.LABEL);
                        stage.setScene(new Scene(root));
                        SecondController controller = loader.getController();
                        stage.setOnCloseRequest(controller.getCloseEvent());
                        stage.show();
                        // Hide this current window (if this is what you want)
                        ((Node)(event.getSource())).getScene().getWindow().hide();
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                    }
                }else {
                    nullAlert.setAlertType(Alert.AlertType.ERROR);
                    nullAlert.setTitle(Constants.ERR);
                    nullAlert.setContentText("Ошибка соединения с платой");
                    nullAlert.show();
                }
            }
        });
    }

    private static boolean connection(String com, int rate){
        sp = SerialPort.getCommPort(com);
        sp = SerialPort.getCommPort(com);
        sp.setComPortParameters(rate,8,1,0);
        sp.setComPortTimeouts(SerialPort.TIMEOUT_READ_BLOCKING,0,0);
        return sp.openPort();
    }
}