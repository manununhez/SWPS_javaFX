package pl.swps.controller;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import pl.swps.model.Participant;
import pl.swps.model.WordList;

import java.io.IOException;
import java.util.HashMap;

public class StartExperiment implements EventHandler<KeyEvent> {
    private static final String VIEW_EXPERIMENT_FXML = "../view/Experiment.fxml";
    private Stage experimentStage;
    private Scene scene;
    private ScreenController screenController;
    private BorderPane rootLayout;
    private int SETTINGS_LIST_PER_PARTICIPANT = 4;
    private HashMap<String, Experiment> experimentHashMap = new HashMap<>();
    private Participant participant;

    public void initScreenFlow() {
        screenController = new ScreenController(rootLayout);

        //we add this pane to the stack
        screenController.addScreen(wizard_5_final());

        screenController.addScreen(showExperiment(participant.wordLists.get(SETTINGS_LIST_PER_PARTICIPANT - 1))); //Last test

        for (int i = SETTINGS_LIST_PER_PARTICIPANT - 2; i >= 0; i--) {
            screenController.addScreen(wizard_4_next_space_bar());
            screenController.addScreen(wizard_3_after_list());
            screenController.addScreen(showExperiment(participant.wordLists.get(i)));
        }

        screenController.addScreen(wizard_2_first_space_bar());
        screenController.addScreen(wizard_1());


        // Set participant overview into the center of root layout.
        screenController.activate();
    }


    public void setStage(Stage experimentStage) {
        this.experimentStage = experimentStage;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
        this.scene.setOnKeyPressed(this);
    }

    public void setPane(BorderPane page) {
        this.rootLayout = page;
    }


    public void setParticipant(Participant participant) {
        this.participant = participant;
    }


    /**
     * Shows the experiment overview inside the root layout.
     *
     * @param s List of Words
     */
    private GridPane showExperiment(WordList s) {
        try {
            // Load participant overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(StartExperiment.class.getResource(VIEW_EXPERIMENT_FXML));
            GridPane pane = loader.load();

            pane.setId(s.key);

            //Give the controller acces to the main app.
            Experiment controller = loader.getController();
            controller.setValues(s);

            experimentHashMap.put(pane.getId(), controller);

            return pane;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }


    private GridPane wizard_1() {
        GridPane grid = new GridPane();
        grid.setId("wizard_1");
        grid.setAlignment(Pos.CENTER);
        grid.setStyle("-fx-background-color:lightblue;");

        Label label = new Label("Za chwilę rozpocznie się zadanie komputerowe. " +
                "Na ekranie będą wyświetlane po kolei słowa. Twoim zadaniem będzie zapamiętanie jak największej ich liczby. " +
                "Naciśnij spację, aby przejść dalej.");
        label.setPadding(new Insets(50, 100, 50, 100));
        label.setTextAlignment(TextAlignment.CENTER);
        label.setAlignment(Pos.CENTER);
        label.setWrapText(true);
        label.setFont(new Font(47.0));
        grid.add(label, 0, 0);

        return grid;

    }

    private GridPane wizard_2_first_space_bar() {
        GridPane grid = new GridPane();
        grid.setId("wizard_2_first_space_bar");
        grid.setAlignment(Pos.CENTER);
        grid.setStyle("-fx-background-color:lightblue;");

        Label label = new Label("Naciśnij spację, aby rozpocząć zadanie.");
        label.setAlignment(Pos.CENTER);
        label.setTextAlignment(TextAlignment.CENTER);
        label.setWrapText(true);
        label.setFont(new Font(47.0));
        grid.add(label, 0, 0);

        return grid;
    }

    private GridPane wizard_3_after_list() {
        GridPane grid = new GridPane();
        grid.setId("wizard_3_after_list");
        grid.setAlignment(Pos.CENTER);
        grid.setStyle("-fx-background-color:lightblue;");


        Label label = new Label("Proszę teraz wypisać na kartce wszystkie zapamiętane słowa.\n" +
                "Kolejność słów nie ma znaczenia.\n" +
                "Po wypisaniu słów, proszę nacisnąć spację.");
        label.setPadding(new Insets(50, 100, 50, 100));
        label.setTextAlignment(TextAlignment.CENTER);
        label.setAlignment(Pos.CENTER);
        label.setWrapText(true);
        label.setFont(new Font(47.0));
        grid.add(label, 0, 0);

        return grid;

    }


    private GridPane wizard_4_next_space_bar() {
        GridPane grid = new GridPane();
        grid.setId("wizard_4_next_space_bar");
        grid.setAlignment(Pos.CENTER);
        grid.setStyle("-fx-background-color:lightblue;");

        Label label = new Label("Naciśnij spację, aby rozpocząć kolejne zadanie.");
        label.setAlignment(Pos.CENTER);
        label.setTextAlignment(TextAlignment.CENTER);
        label.setWrapText(true);
        label.setFont(new Font(47.0));
        grid.add(label, 0, 0);

        return grid;
    }

    private GridPane wizard_5_final() {

        GridPane grid = new GridPane();
        grid.setId("wizard_5_final");
        grid.setAlignment(Pos.CENTER);
        grid.setStyle("-fx-background-color:lightblue;");

        Label label = new Label(" Dziękujemy, to już koniec zadania komputerowego.");
        label.setPadding(new Insets(50, 100, 50, 100));
        label.setTextAlignment(TextAlignment.CENTER);
        label.setAlignment(Pos.CENTER);
        label.setWrapText(true);
        label.setFont(new Font(47.0));
        grid.add(label, 0, 0);

        return grid;

    }


    @Override
    public void handle(KeyEvent event) {
        if (event.getCode().isWhitespaceKey()) {
            if (screenController.isNotEmpty()) {
                GridPane pane = (GridPane) screenController.getPane();
                if (isTestScreen(pane)) {
                    if (isLoopIsOver(pane))
                        activateNewScreen();
                } else
                    activateNewScreen();
            }
        }
    }

    private void activateNewScreen() {
        screenController.removeScreen();
        screenController.activate();

        if (screenController.isNotEmpty()) {
            //After the change, we verified if the NEW screen is test, to initiate the loop of words
            GridPane newPane = (GridPane) screenController.getPane();
            if (isTestScreen(newPane)) startLoopInTestScreen(newPane);

        }
    }

    private void startLoopInTestScreen(GridPane pane) {
        Experiment controller = experimentHashMap.get(pane.getId());
        controller.startLoop();
    }

    private boolean isTestScreen(GridPane pane) {
        return experimentHashMap.containsKey(pane.getId());
    }

    private boolean isLoopIsOver(GridPane pane) {
        Experiment controller = experimentHashMap.get(pane.getId());
        return controller.isTimeLineIsOver();
    }


}
