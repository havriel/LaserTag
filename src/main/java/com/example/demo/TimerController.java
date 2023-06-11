package com.example.demo;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.util.Duration;
import jssc.SerialPort;
import jssc.SerialPortException;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class TimerController implements Initializable {
    private Timeline timeline;
    private int seconds = 10;
    private int minutes = 1;
    private boolean isStopped = false;
    private DataBaseHandler handler;

    @FXML
    private Label minute;

    @FXML
    private Label second;

    @FXML
    private Button startTimer;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        handler = new DataBaseHandler();
        try {
            handler.dbConnection = handler.getDbConnection();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


        FileInput input = new FileInput();
        SerialPort serialPort = new SerialPort(input.readFile());
        try {
            serialPort.openPort();
            serialPort.setParams(SerialPort.BAUDRATE_9600,SerialPort.DATABITS_8,SerialPort.STOPBITS_1,SerialPort.PARITY_NONE);
        } catch (SerialPortException e) {
            throw new RuntimeException(e);
        }
        if (minutes < 10) {
            minute.setText("0" + minutes);
        } else {
            minute.setText(Integer.toString(minutes));
        }
        second.setText(Integer.toString(seconds));

        startTimer.setOnAction(event -> {
            if (timeline != null) {
                timeline.stop();
            }

            timeline = new Timeline();
            timeline.setCycleCount(Timeline.INDEFINITE);
            timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1), event1 -> {
                seconds--;
                if (seconds <= 0) {
                    seconds = 60;
                    minutes--;
                    if (minutes < 0) {
                        minutes = 0;
                        seconds = 0;
                        timeline.stop();
                        isStopped = true;
                    }
                }

                if (!isStopped) {
                    try {
                        String s = serialPort.readString().trim();
                        System.out.println(s);
                        String getVest;
                        String getWeapon;

                        int w = Integer.parseInt(s.substring(s.lastIndexOf('/')+1));

                        if(s.startsWith("10")){
                            getVest = "SELECT vest FROM players WHERE vest ="+10;
                        }else{
                            getVest = "SELECT vest FROM players WHERE vest ="+Integer.parseInt(s.substring(0,1));
                        }

                        if(s.endsWith("10")){
                            getWeapon = "SELECT weapon FROM players WHERE weapon ="+10;
                        }else{
                            getWeapon = "SELECT weapon FROM players WHERE weapon ="+w;
                        }
                        PreparedStatement statementVest;
                        PreparedStatement statementWeapon;
                        ResultSet rsVest;
                        ResultSet rsWeapon;
                        try {
                            statementVest = handler.dbConnection.prepareStatement(getVest);
                            statementWeapon = handler.dbConnection.prepareStatement(getWeapon);
                            rsVest = statementVest.executeQuery();
                            rsWeapon = statementWeapon.executeQuery();
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                        if (rsVest.next()){
                            if (rsVest.getInt("vest")==Integer.parseInt(s.substring(0,1))){
                                handler.updateDeaths(Integer.parseInt(s.substring(0,1)));
                            }
                        }
                        if(rsWeapon.next()){
                            if (rsWeapon.getInt("weapon")== w){
                                handler.updateKills(w);
                            }
                        }
                    } catch (SerialPortException | SQLException e) {
                        throw new RuntimeException(e);
                    }

                }

                if (seconds < 10) {
                    second.setText("0" + seconds);
                } else {
                    second.setText(Integer.toString(seconds));
                }
                if (minutes < 10) {
                    minute.setText("0" + minutes);
                } else {
                    minute.setText(Integer.toString(minutes));
                }
            }));
            timeline.playFromStart();
        });
    }
}
