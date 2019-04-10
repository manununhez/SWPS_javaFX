package pl.swps.controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.util.Duration;
import pl.swps.model.WordList;

import java.util.Iterator;


public class Experiment {
    public Label labelTest;
    private WordList wordList;
    private int SETTINGS_EXPOSITION_TIME = 2; //seconds
    private Timeline clock;

    void setValues(WordList wordList) {
        this.wordList = new WordList(wordList.category, wordList.key, wordList.values);
//        System.out.println("SetValue()=>"+wordList.toString());
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
