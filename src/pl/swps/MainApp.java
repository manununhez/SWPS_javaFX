package pl.swps;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import pl.swps.controller.AddNewParticipant;
import pl.swps.controller.ExperimentOverview;
import pl.swps.controller.ScreenController;
import pl.swps.controller.StartExperiment;
import pl.swps.model.Participant;
import pl.swps.model.WordList;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MainApp extends Application {
    private static final String VIEW_ROOT_LAYOUT_FXML = "view/RootLayout.fxml";
    private static final String VIEW_EXPERIMENT_OVERVIEW_FXML = "view/ExperimentOverview.fxml";
    private static final String VIEW_ADD_NEW_PARTICIPANT_FXML = "view/AddNewParticipant.fxml";
    private static final String VIEW_START_EXPERIMENT_FXML = "view/StartExperiment.fxml";
    private static final String PRIMARY_STAGE_TITLE = "SWSPApp";
    private static final String SECONDARY_STAGE_TITLE = "Starting Experiment";

    private Stage primaryStage;
    private BorderPane rootLayout;
    private ScreenController screenController;

    private Participant participant;
    private List<WordList> wordLists;
    private List<Participant> participants;

    public MainApp() {
        wordLists = new ArrayList<>();
        participants = new ArrayList<>();
        ArrayList<String> originalList = new ArrayList<>();
        originalList.add("uno");
        originalList.add("dos");
        originalList.add("tres");
        originalList.add("cuatro");
        originalList.add("cinco");
        originalList.add("seis");
        originalList.add("siete");
        originalList.add("ocho");
        originalList.add("nueve");
        originalList.add("diez");
//        originalList.add("diez");
//        originalList.add("once");
//        originalList.add("doce");
//        originalList.add("trece");
//        originalList.add("catorce");
//        originalList.add("quince");

        Collections.shuffle(originalList);
//        System.out.println(originalList);
        wordLists.add(new WordList(WordList.CATEGORY_POSITIVE, "Lists#1", originalList));
        Collections.shuffle(originalList);
//        System.out.println(originalList);
        wordLists.add(new WordList(WordList.CATEGORY_POSITIVE, "Lists#2", originalList));
        Collections.shuffle(originalList);
//        System.out.println(originalList);
        wordLists.add(new WordList(WordList.CATEGORY_POSITIVE, "Lists#3", originalList));
        Collections.shuffle(originalList);
//        System.out.println(originalList);
        wordLists.add(new WordList(WordList.CATEGORY_POSITIVE, "Lists#4", originalList));
        Collections.shuffle(originalList);
//        System.out.println(originalList);
        wordLists.add(new WordList(WordList.CATEGORY_NEGATIVE, "Lists#5", originalList));
        Collections.shuffle(originalList, new Random(5));
//        System.out.println(originalList);
        wordLists.add(new WordList(WordList.CATEGORY_NEGATIVE, "Lists#6", originalList));
        Collections.shuffle(originalList, new Random(5));
//        System.out.println(originalList);
        wordLists.add(new WordList(WordList.CATEGORY_NEGATIVE, "Lists#7", originalList));
        Collections.shuffle(originalList, new Random(5));
//        System.out.println(originalList);
        wordLists.add(new WordList(WordList.CATEGORY_NEGATIVE, "Lists#8", originalList));

//        System.out.println("Constructor =>"+wordLists);

    }

    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle(PRIMARY_STAGE_TITLE);

        // Set the application icon.
//        this.primaryStage.getIcons().add(new Image("address_book_32.png"));

        initRootLayout();

        showExperimentOverview();
    }

    /**
     * Initializes the root layout.
     */
    private void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource(VIEW_ROOT_LAYOUT_FXML));
            rootLayout = loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();

            //We add the main scene to the stack
            screenController = new ScreenController(rootLayout);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Shows the experiment overview inside the root layout.
     */
    private void showExperimentOverview() {
        try {
            // Load participant overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource(VIEW_EXPERIMENT_OVERVIEW_FXML));
            AnchorPane experimentOverview = loader.load();

            //we add this pane to the stack
            screenController.addScreen(experimentOverview);
            // Set participant overview into the center of root layout.
            screenController.activate();

            //Give the controller acces to the main app.
            ExperimentOverview controller = loader.getController();
            controller.setMainApp(this);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Shows the experiment overview inside the root layout.
     */
    public void showAddNewParticipant() {
        try {
            // Load participant overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource(VIEW_ADD_NEW_PARTICIPANT_FXML));
            AnchorPane addNewParticipant = loader.load();

            //we add this pane to the stack
            screenController.addScreen(addNewParticipant);
            // Set participant overview into the center of root layout.
            screenController.activate();


            //Give the controller access to the main app.
            AddNewParticipant controller = loader.getController();
            controller.setScene(primaryStage.getScene());
            controller.setMainApp(this);
            controller.setWordList(wordLists);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void setParticipant(Participant participant) {
        //In this point, participant contains all the information about the participant, including the selected list category,
        // and the randomized order of these lists
        this.participant = participant;
        //We add the participant to the list of participants, later will be saved to file
        //TODO this value will be saved to file
        participants.add(participant);
    }

    public void backToPreviousScene() {
        screenController.removeScreen();
        screenController.activate();
    }


    /**
     * Returns the main stage.
     *
     * @return primaryStage
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }


    /**
     * Opens a dialog to edit details for the specified participant. If the user
     * clicks OK, the changes are saved into the provided participant object and true
     * is returned.
     */
    public void showStartExperiment() {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource(VIEW_START_EXPERIMENT_FXML));
            BorderPane page = loader.load();

            // Create the dialog Stage.
            Stage secondaryStage = new Stage();
            secondaryStage.setTitle(SECONDARY_STAGE_TITLE);
            secondaryStage.initModality(Modality.WINDOW_MODAL);
//            secondaryStage.initOwner(primaryStage);
            secondaryStage.setMaximized(true);

            //new scene
            Scene scene = new Scene(page);
            secondaryStage.setScene(scene);

            // Set the participant into the controller.
            StartExperiment controller = loader.getController();
            controller.setStage(secondaryStage);
            controller.setScene(scene);
            controller.setPane(page);
            controller.setParticipant(participant);
            controller.initScreenFlow();

            // Show the dialog and wait until the user closes it
            secondaryStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
