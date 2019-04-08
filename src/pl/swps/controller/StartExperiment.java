package pl.swps.controller;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
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

import java.io.IOException;
import java.util.HashMap;

public class StartExperiment implements EventHandler<KeyEvent> {
    private static final String VIEW_EXPERIMENT_FXML = "../view/Experiment.fxml";
    private Stage experimentStage;
    private Scene scene;
    private boolean isWhiteSpaceKeyPressed = false;
    private ScreenController screenController;
    private BorderPane rootLayout;
    private int SETTINGS_LIST_PER_PERSON = 4;
    private HashMap<String, Experiment> experimentHashMap = new HashMap<>();
//    private Label lbTest;

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {

    }

    public void initRootLayout(Stage experimentStage, BorderPane page) {
        this.experimentStage = experimentStage;

        this.scene = this.experimentStage.getScene();
        this.scene.setOnKeyPressed(this);
        this.experimentStage.setMaximized(true);
        this.rootLayout = page;

        screenController = new ScreenController(rootLayout);

        //we add this pane to the stack
        screenController.addScreen(wizard_5_final());

        screenController.addScreen(showExperiment("Test #" + SETTINGS_LIST_PER_PERSON)); //Last test

        for (int i = SETTINGS_LIST_PER_PERSON - 1; i > 0; i--) {
            screenController.addScreen(wizard_4_next_space_bar());
            screenController.addScreen(wizard_3_after_list());
            screenController.addScreen(showExperiment("Test #" + i));
        }

        screenController.addScreen(wizard_2_first_space_bar());
        screenController.addScreen(wizard_1());


        // Set person overview into the center of root layout.
        screenController.activate();

    }


    /**
     * Shows the experiment overview inside the root layout.
     *
     * @param s
     */
    public GridPane showExperiment(String s) {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(StartExperiment.class.getResource(VIEW_EXPERIMENT_FXML));
            GridPane pane = loader.load();

            pane.setId(s);

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


//    private GridPane test(String tx) {
//
//        //        try {
////            Thread.sleep(1000);
////        } catch (InterruptedException e) {
////            e.printStackTrace();
////        }
//        GridPane grid = new GridPane();
//        grid.setAlignment(Pos.CENTER);
////        grid.setHgap(10);
////        grid.setVgap(10);
////        grid.setPadding(new Insets(125, 25, 25, 25));
//        grid.setStyle("-fx-background-color:lightblue;");
//
//
//        lbTest = new Label(tx);
////        alignment="CENTER" contentDisplay="CENTER" lineSpacing="2.0"
////        textAlignment="CENTER" wrapText="true" BorderPane.alignment="CENTER"
//        lbTest.setPadding(new Insets(50, 100, 50, 100));
//        lbTest.setTextAlignment(TextAlignment.CENTER);
//        lbTest.setAlignment(Pos.CENTER);
//        lbTest.setWrapText(true);
//        lbTest.setFont(new Font(47.0));
//        grid.add(lbTest, 0, 0);
//
//        return grid;
//
//    }


    private GridPane wizard_5_final() {

        //        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        GridPane grid = new GridPane();
        grid.setId("wizard_5_final");
        grid.setAlignment(Pos.CENTER);
//        grid.setHgap(10);
//        grid.setVgap(10);
//        grid.setPadding(new Insets(125, 25, 25, 25));
        grid.setStyle("-fx-background-color:lightblue;");


        Label text = new Label(" Dziękujemy, to już koniec zadania komputerowego.");
//        alignment="CENTER" contentDisplay="CENTER" lineSpacing="2.0"
//        textAlignment="CENTER" wrapText="true" BorderPane.alignment="CENTER"
        text.setPadding(new Insets(50, 100, 50, 100));
        text.setTextAlignment(TextAlignment.CENTER);
        text.setAlignment(Pos.CENTER);
        text.setWrapText(true);
        text.setFont(new Font(47.0));
        grid.add(text, 0, 0);

        return grid;

    }


    private GridPane wizard_3_after_list() {
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        GridPane grid = new GridPane();
        grid.setId("wizard_3_after_list");
        grid.setAlignment(Pos.CENTER);
//        grid.setHgap(10);
//        grid.setVgap(10);
//        grid.setPadding(new Insets(125, 25, 25, 25));
        grid.setStyle("-fx-background-color:lightblue;");


        Label text = new Label("Proszę teraz wypisać na kartce wszystkie zapamiętane słowa.\n" +
                "Kolejność słów nie ma znaczenia.\n" +
                "Po wypisaniu słów, proszę nacisnąć spację.");
//        alignment="CENTER" contentDisplay="CENTER" lineSpacing="2.0"
//        textAlignment="CENTER" wrapText="true" BorderPane.alignment="CENTER"
        text.setPadding(new Insets(50, 100, 50, 100));
        text.setTextAlignment(TextAlignment.CENTER);
        text.setAlignment(Pos.CENTER);
        text.setWrapText(true);
        text.setFont(new Font(47.0));
        grid.add(text, 0, 0);

        return grid;

    }

    private GridPane wizard_1() {
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        GridPane grid = new GridPane();
        grid.setId("wizard_1");
        grid.setAlignment(Pos.CENTER);
//        grid.setHgap(10);
//        grid.setVgap(10);
//        grid.setPadding(new Insets(125, 25, 25, 25));
        grid.setStyle("-fx-background-color:lightblue;");


        Label text = new Label("Za chwilę rozpocznie się zadanie komputerowe. " +
                "Na ekranie będą wyświetlane po kolei słowa. Twoim zadaniem będzie zapamiętanie jak największej ich liczby. " +
                "Naciśnij spację, aby przejść dalej.");
//        alignment="CENTER" contentDisplay="CENTER" lineSpacing="2.0"
//        textAlignment="CENTER" wrapText="true" BorderPane.alignment="CENTER"
        text.setPadding(new Insets(50, 100, 50, 100));
        text.setTextAlignment(TextAlignment.CENTER);
        text.setAlignment(Pos.CENTER);
        text.setWrapText(true);
        text.setFont(new Font(47.0));
        grid.add(text, 0, 0);

        return grid;

    }


    private GridPane wizard_2_first_space_bar() {
        GridPane grid = new GridPane();
        grid.setId("wizard_2_first_space_bar");
        grid.setAlignment(Pos.CENTER);
//        grid.setHgap(10);
//        grid.setVgap(10);
//        grid.setPadding(new Insets(125, 25, 25, 25));
        grid.setStyle("-fx-background-color:lightblue;");

        Label text = new Label("Naciśnij spację, aby rozpocząć zadanie.");
//        alignment="CENTER" contentDisplay="CENTER" lineSpacing="2.0"
//        textAlignment="CENTER" wrapText="true" BorderPane.alignment="CENTER"
        text.setAlignment(Pos.CENTER);
        text.setTextAlignment(TextAlignment.CENTER);
        text.setWrapText(true);
        text.setFont(new Font(47.0));
        grid.add(text, 0, 0);

        return grid;
    }

    private GridPane wizard_4_next_space_bar() {
        GridPane grid = new GridPane();
        grid.setId("wizard_4_next_space_bar");
        grid.setAlignment(Pos.CENTER);
//        grid.setHgap(10);
//        grid.setVgap(10);
//        grid.setPadding(new Insets(125, 25, 25, 25));
        grid.setStyle("-fx-background-color:lightblue;");

        Label text = new Label("Naciśnij spację, aby rozpocząć kolejne zadanie.");
//        alignment="CENTER" contentDisplay="CENTER" lineSpacing="2.0"
//        textAlignment="CENTER" wrapText="true" BorderPane.alignment="CENTER"
        text.setAlignment(Pos.CENTER);
        text.setTextAlignment(TextAlignment.CENTER);
        text.setWrapText(true);
        text.setFont(new Font(47.0));
        grid.add(text, 0, 0);

        return grid;
    }


    @Override
    public void handle(KeyEvent event) {
        if (event.getCode().isWhitespaceKey()) {
            if (!screenController.isEmpty()) {
                screenController.removeScreen();
                screenController.activate();
                GridPane pane = (GridPane) screenController.getPane();

                if (experimentHashMap.containsKey(pane.getId())) {
                    Experiment controller = experimentHashMap.get(pane.getId());
                    controller.startLoop();
                }

            }
        }
    }


}
