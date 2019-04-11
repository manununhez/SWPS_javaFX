package pl.swps.controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.util.Duration;
import pl.swps.model.StyleDesign;
import pl.swps.model.WordList;

import java.util.Iterator;


public class Experiment {

    private static final String KINDLE_SEPIA_TEXT_COLOR_CODE = "#5F4B32";
    private static final String KINDLE_SEPIA_COLOR_CODE = "#FBF0D9";
    private static final String KINDLE_SEPIA_FONT_NAME = "Georgia"; //Palatino

    private WordList wordList;
    @FXML
    public Label labelTest;
    private Timeline clock;
    @FXML
    public AnchorPane anchorPaneTest;
    private int SETTINGS_EXPOSITION_TIME = 1; //seconds //TODO change for production
    private StyleDesign design;

//    /**
//     * Initializes the controller class. This method is automatically called
//     * after the fxml file has been loaded.
//     */
//    @FXML
//    private void initialize() {
//        labelTest.setTextFill(Paint.valueOf(KINDLE_SEPIA_TEXT_COLOR_CODE));
//        labelTest.setFont(new Font(KINDLE_SEPIA_FONT_NAME, 47.0));
//
//        anchorPaneTest.setStyle("-fx-background-color:" + KINDLE_SEPIA_COLOR_CODE + ";");
//    }

    void setValues(WordList wordList) {
        this.wordList = new WordList(wordList.category, wordList.key, wordList.values);
    }

    void setStyleDesign(StyleDesign design) {
        this.design = design;

        labelTest.setTextFill(Paint.valueOf(design.fontColor));
        labelTest.setFont(new Font(design.fontName, design.fontSize));

        anchorPaneTest.setStyle("-fx-background-color:" + design.backgroundColor + ";");
    }

    void startLoop() {
        Iterator<String> it = wordList.values.iterator();

        int duration = SETTINGS_EXPOSITION_TIME;
        int cycleCount = wordList.values.size();

        //We start a timer, goes from zero to cycleCount.
        //We iterate over wordList.values, without modifying its values.
        //The speed or duration of the iteration is duration.
        clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            if (it.hasNext()) {
                labelTest.setText(it.next());
            }
        }),
                new KeyFrame(Duration.seconds(duration))
        );
        clock.setCycleCount(cycleCount);
        clock.play();
    }


    boolean isTimeLineIsOver() {
        return clock.getStatus().equals(Animation.Status.STOPPED);
    }
}
