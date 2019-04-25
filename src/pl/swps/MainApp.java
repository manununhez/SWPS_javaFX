package pl.swps;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import pl.swps.model.*;
import pl.swps.util.CSVReader;
import pl.swps.util.CSVWriter;
import pl.swps.view.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.prefs.Preferences;

public class MainApp extends Application {
    private static final String VIEW_ROOT_LAYOUT_FXML = "view/RootLayout.fxml";
    private static final String FILE_PATH = "filePath";
    private static final String VIEW_START_EXPERIMENT_FXML = "view/StartExperiment.fxml";
    private static final String VIEW_ADD_NEW_PARTICIPANT_FXML = "view/AddNewParticipant.fxml";
    private static final String CSV_EXTENSION = ".csv";

    private static final String PRIMARY_STAGE_TITLE = "SWSP University App";
    private static final String SECONDARY_STAGE_TITLE = "Experiment started";
    private static final String VIEW_HOME_FXML = "view/Home.fxml";
    private static final String VIEW_RESULTS_FXML = "view/Results.fxml";
    private static final String VIEW_INSTRUCTIONS_FXML = "view/Instructions.fxml";

    private Stage primaryStage;
    private BorderPane rootLayout;
    private ScreenController screenController;

    private Participant participant;
    private InstructionMessages instructionMessages = new InstructionMessages();

    private ObservableList<WordList> wordLists = FXCollections.observableArrayList();
    private ObservableList<Participant> participants = FXCollections.observableArrayList();


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

        showHome();
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

            //Give the controller access to the main app.
            RootLayout controller = loader.getController();
            controller.setMainApp(this);

            //We add the main scene to the stack
            screenController = new ScreenController(rootLayout);

            //Try to load last opened person file
            File file = getPersonFilePath();

            if (file != null) {
                if (file.getPath().endsWith(CSV_EXTENSION)) {
                    loadPersonDataFromFileCSV(file);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Shows the home inside the root layout.
     */
    public void showHome() {
        try {
            // Load home.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource(VIEW_HOME_FXML));
            AnchorPane home = loader.load();

            //we add this pane to the stack
            screenController.addScreen(home);
            // Set home into the center of root layout.
            screenController.activate();

            //Give the controller acces to the main app.
            Home controller = loader.getController();
            controller.setMainApp(this);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Shows the new experiment inside the root layout.
     */
    public void showNewExperiment() {
        try {
            // Load new experiment.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource(VIEW_ADD_NEW_PARTICIPANT_FXML));
            AnchorPane addNewParticipant = loader.load();

            //we add this pane to the stack
            screenController.addScreen(addNewParticipant);
            // Set new experiment into the center of root layout.
            screenController.activate();


            //Give the controller access to the main app.
            AddNewParticipant controller = loader.getController();
//            controller.setScene(primaryStage.getScene());
            controller.setMainApp(this);
//            controller.setWordList(wordLists);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Shows the results inside the root layout.
     */
    public void showResults() {
        try {
            // Load results.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource(VIEW_RESULTS_FXML));
            AnchorPane resultsPAne = loader.load();

            //we add this pane to the stack
            screenController.addScreen(resultsPAne);
            // Set results into the center of root layout.
            screenController.activate();


            //Give the controller access to the main app.
            Results controller = loader.getController();
//            controller.setScene(primaryStage.getScene());
            controller.setMainApp(this);
//            controller.setWordList(wordLists);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void showInstructions() {
        try {
            // Load results.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource(VIEW_INSTRUCTIONS_FXML));
            AnchorPane instructionsPane = loader.load();

            //we add this pane to the stack
            screenController.addScreen(instructionsPane);
            // Set results into the center of root layout.
            screenController.activate();


            //Give the controller access to the main app.
            Instructions controller = loader.getController();
//            controller.setScene(primaryStage.getScene());
            controller.setMainApp(this);
            controller.setInstructions(instructionMessages);
//            controller.setWordList(wordLists);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ObservableList<WordList> getWordLists() {
        return wordLists;
    }

    public ObservableList<Participant> getParticipants() {
        return participants;
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
     *
     * @param style Experiment screen design
     */
    public void showStartExperiment(StyleDesign style) {
        try {
//            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource(VIEW_START_EXPERIMENT_FXML));
            BorderPane page = loader.load();
//
//            // Create the dialog Stage.
            Stage secondaryStage = new Stage();
            secondaryStage.setTitle(SECONDARY_STAGE_TITLE);
            secondaryStage.initModality(Modality.WINDOW_MODAL);
            secondaryStage.initOwner(primaryStage);
            secondaryStage.setMaximized(true);
//
            //new scene
            Scene scene = new Scene(page);
            secondaryStage.setScene(scene);

            // Set the participant into the controller.
            StartExperiment controller = loader.getController();
            controller.setStage(secondaryStage);
            controller.setScene(scene);
            controller.setPane(page);
            controller.setParticipant(participant);
            controller.setInstructionMessages(instructionMessages);
            controller.setStyle(style);
            controller.initScreenFlow();

            // Show the dialog and wait until the user closes it
            secondaryStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Returns the person file preference, i.e. the file that was last opened.
     * The preference is read from the OS specific registry. If no such
     * preference can be found, null is returned.
     *
     * @return
     */
    public File getPersonFilePath() {
        Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
        String filePath = prefs.get(FILE_PATH, null);
        if (filePath != null) {
            return new File(filePath);
        } else {
            return null;
        }
    }

    /**
     * Sets the file path of the currently loaded file. The path is persisted in
     * the OS specific registry.
     *
     * @param file the file or null to remove the path
     */
    public void setPersonFilePath(File file) {
        Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
        if (file != null) {
            prefs.put(FILE_PATH, file.getPath());

            // Update the stage title.
            primaryStage.setTitle(PRIMARY_STAGE_TITLE + " - " + file.getName());
        } else {
            prefs.remove(FILE_PATH);

            // Update the stage title.
            primaryStage.setTitle(PRIMARY_STAGE_TITLE);
        }
    }


    public void loadPersonDataFromFileCSV(File file) {
        BufferedReader br;
        try {
            //Create the file reader
//            br = new BufferedReader(new FileReader(file));

            br = new BufferedReader(new InputStreamReader(new FileInputStream(file),
                    StandardCharsets.UTF_8));
            CSVReader csvReader = new CSVReader(br);

            WordListCSVWrapper wordListCSVWrapper = new WordListCSVWrapper(csvReader.readAll());
            wordLists.clear();
            wordLists.addAll(wordListCSVWrapper.getListFromCSV());

            csvReader.close();
            // Save the file path to the registry.
            setPersonFilePath(file);

        } catch (Exception e) { // catches ANY exception
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Could not load data");
            alert.setContentText("Could not load data from file:\n" + file.getPath());

            alert.showAndWait();
        }
    }

    /**
     * Saves the current person data to the specified file.
     *
     * @param file filename
     */
    public void savePersonDataToFileCSV(File file) {

        CSVWriter csvWriter;
        try {
            csvWriter = new CSVWriter(file, null);
            csvWriter.writeHeader(new String[]{"Participant#", "Sex", "Years of education",
                    "Date of the experiment", "Category", "Lists"});

            for (Participant participant : participants) {
                List<String> listOfKeys = new ArrayList<>();

                for (WordList wordList : participant.wordLists)
                    listOfKeys.add(wordList.key);

                csvWriter.writeData(new String[]{String.valueOf(participant.participantNumber),
                        participant.sex, String.valueOf(participant.yearsOfEducation), participant.getTimestampFormatted(),
                        participant.category, String.join(",", listOfKeys)});
            }

            csvWriter.close();
            // Save the file path to the registry.
            setPersonFilePath(file);
        } catch (IOException e) {
            e.printStackTrace();

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Could not save data");
            alert.setContentText("Could not save data to file:\n" + file.getPath());

            alert.showAndWait();
        }


    }

}
