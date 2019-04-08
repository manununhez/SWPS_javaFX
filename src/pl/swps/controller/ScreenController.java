package pl.swps.controller;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.util.Stack;

public class ScreenController {
    private Stack<Pane> screenMap = new Stack<>();
    private BorderPane main;

    public ScreenController(BorderPane main) {
        this.main = main;
    }

    public void addScreen(Pane pane) {
        screenMap.push(pane);
    }

    public void removeScreen() {
        screenMap.pop();
    }

    public void activate() {
        main.setCenter(screenMap.peek());
    }
}