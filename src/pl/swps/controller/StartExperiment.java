package pl.swps.controller;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class StartExperiment implements EventHandler<KeyEvent> {
    public Label resultLabel;
    private Stage stage;
    private Scene scene;
    private boolean isWhiteSpaceKeyPressed = false;

    public void setStage(Stage stage) {
        this.stage = stage;

        //init scene
        this.scene = stage.getScene();
        this.scene.setOnKeyPressed(this);

    }

    public void handleKeyPressed(KeyEvent keyEvent) {
//        if(keyEvent.getCode().isWhitespaceKey()) {
//            if(!isWhiteSpaceKeyPressed) {
//                resultLabel.setText("WhiteSpaceKey true");
//                isWhiteSpaceKeyPressed = true;
//            } else {
//                resultLabel.setText("WhiteSpaceKey false");
//            }
//        }

    }

    @Override
    public void handle(KeyEvent event) {
        if (event.getCode().isWhitespaceKey()) {
            if (!isWhiteSpaceKeyPressed) {
                resultLabel.setText("WhiteSpaceKey true");
                isWhiteSpaceKeyPressed = true;
            } else {
                resultLabel.setText("WhiteSpaceKey false");
                isWhiteSpaceKeyPressed = false;
            }
        }
    }
}
