package pl.swps.view;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.util.Duration;
import pl.swps.model.StyleDesign;
import pl.swps.model.WordList;

import java.util.Iterator;


public class Experiment {

    private WordList wordList;
    @FXML
    public Label labelTest;
    private Timeline clock;
    @FXML
    public AnchorPane anchorPaneTest;
    private int SETTINGS_EXPOSITION_TIME = 2; //seconds


    void setValues(WordList wordList) {
        this.wordList = new WordList(wordList.category, wordList.key, wordList.values);
    }

    void setStyleDesign(StyleDesign design) {

        labelTest.setTextFill(Paint.valueOf(design.fontColor));
        labelTest.setFont(new Font(design.fontName, design.fontSize));
        labelTest.setAlignment(Pos.CENTER);

        anchorPaneTest.setStyle("-fx-background-color:" + design.backgroundColor + ";");
    }

    void startLoop() {
        Iterator<String> it = wordList.getValuesWithSpaces().iterator();

        int duration = SETTINGS_EXPOSITION_TIME;
        int cycleCount = wordList.getValuesWithSpaces().size();

        //We start a timer, goes from zero to cycleCount.
        //We iterate over wordList.values, without modifying its values.
        //The speed or duration of the iteration is duration.
//        clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
//            if (it.hasNext()) {
//                labelTest.setText(it.next());
//            }
//        }),
//                new KeyFrame(Duration.seconds(duration))
//        );
//        clock.setCycleCount(cycleCount);
//        clock.play();

        clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            if (it.hasNext()) {
                labelTest.setText(it.next());
            }
        }), new KeyFrame(Duration.seconds(duration)));

        clock.setCycleCount(cycleCount);
        clock.play();


    }


    boolean isTimeLineIsOver() {
        return clock.getStatus().equals(Animation.Status.STOPPED);
    }
}
