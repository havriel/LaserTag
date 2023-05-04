package com.example.demo;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class TimerController implements Initializable {
    private Timeline timeline;
    private int seconds = 10;
    private int minutes = 1;
    private boolean isStopped = false;

    DataBaseHandler dataBaseHandler = new DataBaseHandler();

    @FXML
    private Label minute;

    @FXML
    private Label second;

    @FXML
    private Button startTimer;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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
            timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    seconds--;
                    if (seconds <= 0) {
                        seconds = 60;
                        minutes--;
                        if (minutes<0){
                            minutes=0;
                            seconds=0;
                            timeline.stop();
                            isStopped = true;
                        }
                    }
                    if(!isStopped){

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
                }
            }));
            timeline.playFromStart();
        });
    }
}
