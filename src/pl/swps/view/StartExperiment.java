package pl.swps.view;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import pl.swps.model.Participant;
import pl.swps.model.StyleDesign;
import pl.swps.model.WordList;

import java.io.IOException;
import java.util.HashMap;

public class StartExperiment implements EventHandler<KeyEvent> {
    private static final String VIEW_EXPERIMENT_FXML = "Experiment.fxml";

    //    private static final String KINDLE_SEPIA_FONT_NAME = "Georgia"; //Palatino
    private Stage experimentStage;
    private Scene scene;
    private ScreenController screenController;
    private BorderPane rootLayout;
    private int SETTINGS_LIST_PER_PARTICIPANT = 4;
    private HashMap<String, Experiment> experimentHashMap = new HashMap<>();
    private Participant participant;
    private StyleDesign styleDesign;


    public void initScreenFlow() {
        screenController = new ScreenController(rootLayout);

        //we add this pane to the stack
        screenController.addScreen(wizard_5_final());

        screenController.addScreen(wizard_3_after_list());

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
    private AnchorPane showExperiment(WordList s) {
        try {
            // Load participant overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(StartExperiment.class.getResource(VIEW_EXPERIMENT_FXML));
//            GridPane pane = loader.load();
            AnchorPane pane = loader.load();

            pane.setId(s.key);

            //Give the controller acces to the main app.
            Experiment controller = loader.getController();
            controller.setValues(s);
            controller.setStartExperiment(this);
            controller.setStyleDesign(styleDesign);

            experimentHashMap.put(pane.getId(), controller);

            return pane;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

    private AnchorPane setAnchorPane(String id, StyleDesign style, String labelText) {

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setStyle("-fx-background-color:" + style.backgroundColor + ";");

        Label label = new Label(labelText);
        label.setPadding(new Insets(50, 100, 50, 100));
        label.setTextAlignment(TextAlignment.CENTER);
        label.setAlignment(Pos.CENTER);
        label.setWrapText(true);
        label.setTextFill(Paint.valueOf(style.fontColor));
        label.setFont(new Font(style.fontName, style.fontSize));

        grid.add(label, 0, 0);

        AnchorPane.setTopAnchor(grid, 0.0);
        AnchorPane.setBottomAnchor(grid, 0.0);
        AnchorPane.setLeftAnchor(grid, 0.0);
        AnchorPane.setRightAnchor(grid, 0.0);

        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setId(id);
        anchorPane.getChildren().add(grid);

        return anchorPane;

    }

    private AnchorPane wizard_1() {
        String text = "Za chwilę rozpocznie się zadanie komputerowe. " +
                "Na ekranie będą wyświetlane po kolei słowa. Twoim zadaniem będzie zapamiętanie jak największej ich liczby. " +
                "Naciśnij spację, aby przejść dalej.";

        return setAnchorPane("wizard_1", styleDesign, text);
    }


    private AnchorPane wizard_2_first_space_bar() {
        String text = "Naciśnij spację, aby rozpocząć zadanie.";

        return setAnchorPane("wizard_2_first_space_bar", styleDesign, text);
    }

    private AnchorPane wizard_3_after_list() {
        String text = "Proszę teraz wypisać na kartce wszystkie zapamiętane słowa.\n" +
                "Kolejność słów nie ma znaczenia.\n" +
                "Po wypisaniu słów, proszę nacisnąć spację.";
        return setAnchorPane("wizard_3_after_list", styleDesign, text);
    }


    private AnchorPane wizard_4_next_space_bar() {
        String text = "Naciśnij spację, aby rozpocząć kolejne zadanie.";
        return setAnchorPane("wizard_4_next_space_bar", styleDesign, text);
    }

    private AnchorPane wizard_5_final() {
        String text = " Dziękujemy, to już koniec zadania komputerowego.";
        return setAnchorPane("wizard_5_final", styleDesign, text);
    }


//    private AnchorPane experimentScreen(WordList s) {
//        VBox vBox = new VBox();
//
//        vBox.getChildren().add(new Separator());
//
//        Label labelTest = new Label();
//        labelTest.setAlignment(Pos.CENTER);
//        labelTest.setContentDisplay(ContentDisplay.CENTER);
//        labelTest.setTextAlignment(TextAlignment.CENTER);
//        labelTest.setWrapText(true);
//
//        labelTest.setFont(new Font(35.0));
//        vBox.getChildren().add(labelTest);
//
//
//        vBox.getChildren().add(new Separator());
//
////=============
//        AnchorPane.setTopAnchor(vBox, 0.0);
//        AnchorPane.setBottomAnchor(vBox, 0.0);
//        AnchorPane.setLeftAnchor(vBox, 0.0);
//        AnchorPane.setRightAnchor(vBox, 0.0);
//
//        AnchorPane anchorPane = new AnchorPane();
//        anchorPane.getChildren().add(vBox);
//        anchorPane.setId(s.key);
//
////        startLoop(s);
//
//        experimentHashMap2.put(anchorPane.getId(), s);
//
//        return new AnchorPane();
//    }
//


    @Override
    public void handle(KeyEvent event) {
        if (event.getCode().isWhitespaceKey()) {
            keyBoardEvent();

        }
    }

    public void keyBoardEvent() {
        if (screenController.isNotEmpty()) {
            AnchorPane pane = (AnchorPane) screenController.getPane();
            if (isTestScreen(pane)) {
                if (isLoopIsOver(pane))
                    activateNewScreen();
            } else
                activateNewScreen();

        } else {
            activateNewScreen();
        }
    }

    private void activateNewScreen() {
        screenController.removeScreen();
        screenController.activate();

        if (screenController.isNotEmpty()) {
            //After the change, we verified if the NEW screen is test, to initiate the loop of words
            AnchorPane newPane = (AnchorPane) screenController.getPane();
            if (isTestScreen(newPane)) startLoopInTestScreen(newPane);

        }
    }

    private void startLoopInTestScreen(AnchorPane pane) {
        Experiment controller = experimentHashMap.get(pane.getId());
        controller.startLoop();
    }

    private boolean isTestScreen(AnchorPane pane) {
        return experimentHashMap.containsKey(pane.getId());
    }

    private boolean isLoopIsOver(AnchorPane pane) {
        Experiment controller = experimentHashMap.get(pane.getId());
        return controller.isTimeLineIsOver();
    }


    public void setStyle(StyleDesign style) {
        this.styleDesign = style;
    }
}
