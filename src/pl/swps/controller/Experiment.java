package pl.swps.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

public class Experiment {
    public Label labelTest;
    public List<String> list = null;


    public void setValues(String s) {
        labelTest.setText(s);

        list = new ArrayList<>();
        list.add("uno");
        list.add("dos");
        list.add("tres");
        list.add("cuatro");
        list.add("cinco");
        list.add("seis");
        list.add("siete");
        list.add("ocho");
        list.add("nueve");
        list.add("diez");
        list.add("diez");
        list.add("once");
        list.add("doce");
        list.add("trece");
        list.add("catorce");
        list.add("quince");
//        new String[]{"uno", "dos", "tres", "cuatro", "cinco", "seis",
//                "siete", "ocho", "nueve", "diez", "once", "doce", "trece", "catorce", "quince"};

    }


    public void startLoop() {
        //2segs in ms
        int SETTINGS_EXPOSITION_TIME = 2;


        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            labelTest.setText(list.get(0));
            list.remove(0);
        }),
                new KeyFrame(Duration.seconds(SETTINGS_EXPOSITION_TIME))
        );
        clock.setCycleCount(list.size());
        clock.play();
    }
}
