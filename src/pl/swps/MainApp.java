package pl.swps;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import pl.swps.model.*;
import pl.swps.util.CSVReader;
import pl.swps.util.CSVWriter;
import pl.swps.view.*;
import pl.swps.common.dependencyinjection.ApplicationCompositionRoot;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.prefs.Preferences;

public class MainApp extends Application {
    private static final String VIEW_ROOT_LAYOUT_FXML = "view/RootLayout.fxml";
    private static final String FILE_PATH = "filePath";
    private static final String CSV_EXTENSION = ".csv";

    private static final String PRIMARY_STAGE_TITLE = "SWSP University App";
    public static final String RESULTS_FILE_CSV_NAME = "Results.csv";

    private Stage mPrimaryStage;
    private BorderPane rootLayout;

    private Participant participant;

    //TODO Add to repository
    private ObservableList<WordList> wordLists = FXCollections.observableArrayList();
    private ObservableList<Participant> participants = FXCollections.observableArrayList();

    //define your offsets here
    private double xOffset = 0;
    private double yOffset = 0;

    public static void main(String[] args) {
        launch(args);
    }

    private ApplicationCompositionRoot mApplicationCompositionRoot;

    @Override
    public void start(Stage primaryStage) {
        mPrimaryStage = primaryStage;
        mPrimaryStage.setTitle(PRIMARY_STAGE_TITLE);

        //you can use underdecorated or transparent.
        mPrimaryStage.initStyle(StageStyle.TRANSPARENT);

        initRootLayout();
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

            mApplicationCompositionRoot = new ApplicationCompositionRoot(this, rootLayout);

            //grab your root here
            rootLayout.setOnMousePressed(event -> {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            });

            //move around here
            rootLayout.setOnMouseDragged(event -> {
                mPrimaryStage.setX(event.getScreenX() - xOffset);
                mPrimaryStage.setY(event.getScreenY() - yOffset);
            });

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            mPrimaryStage.setScene(scene);
            mPrimaryStage.show();

            //Give the controller access to the main app.
            RootLayout controller = loader.getController();
            controller.setMainApp(this);

            //Try to load last opened person file
            File file = getFilePath();

            if (file != null) {
                if (file.getPath().endsWith(CSV_EXTENSION)) {
                    loadListsFromFileCSV(file);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ApplicationCompositionRoot getApplicationCompositionRoot() {
        return mApplicationCompositionRoot;
    }


    public ObservableList<WordList> getWordLists() {
        return wordLists;
    }

    public ObservableList<Participant> getParticipants() {
        return participants;
    }

    //TODO Add to repository
    public void setParticipant(Participant participant) {
        //In this point, participant contains all the information about the participant, including the selected list category,
        // and the randomized order of these lists
        this.participant = participant;
        //We add the participant to the list of participants, later will be saved to file
        //TODO this value will be saved to file
        participants.add(participant);
    }


    /**
     * Returns the main stage.
     *
     * @return mPrimaryStage
     */
    public Stage getPrimaryStage() {
        return mPrimaryStage;
    }


//    /**
//     * Opens a dialog to edit details for the specified participant. If the user
//     * clicks OK, the changes are saved into the provided participant object and true
//     * is returned.
//     *
//     * @param style Experiment screen design
//     */
//    public void showStartExperiment(StyleDesign style) {
//        try {
//            // Load the fxml file and create a new stage for the popup dialog.
//            FXMLLoader loader = new FXMLLoader();
//            loader.setLocation(MainApp.class.getResource(VIEW_START_EXPERIMENT_FXML));
//            BorderPane page = loader.load();
//
//            // Create the dialog Stage.
//            Stage secondaryStage = new Stage();
//            secondaryStage.setTitle(SECONDARY_STAGE_TITLE);
//            secondaryStage.initModality(Modality.WINDOW_MODAL);
//            secondaryStage.initOwner(mPrimaryStage);
//            secondaryStage.setMaximized(true);
//
//            //new scene
//            Scene scene = new Scene(page);
//            secondaryStage.setScene(scene);
//
//            // Set the participant into the controller.
//            StartExperiment controller = loader.getController();
//            controller.setStage(secondaryStage);
//            controller.setScene(scene);
//            controller.setPane(page);
//            controller.setParticipant(participant);
//            controller.setMainApp(this);
//            controller.setStyle(style);
//            controller.initScreenFlow();
//
//            // Show the dialog and wait until the user closes it
//            secondaryStage.showAndWait();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

//TODO Add to repository
    /**
     * Returns the person file preference, i.e. the file that was last opened.
     * The preference is read from the OS specific registry. If no such
     * preference can be found, null is returned.
     *
     * @return File
     */
    private File getFilePath() {
        Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
        String filePath = prefs.get(FILE_PATH, null);
        if (filePath != null) {
            return new File(filePath);
        } else {
            return null;
        }
    }

    //TODO Add to repository
    /**
     * Sets the file path of the currently loaded file. The path is persisted in
     * the OS specific registry.
     *
     * @param file the file or null to remove the path
     */
    private void setFilePath(File file) {
        Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
        if (file != null) {
            prefs.put(FILE_PATH, file.getPath());

            // Update the stage title.
            mPrimaryStage.setTitle(PRIMARY_STAGE_TITLE + " - " + file.getName());
        } else {
            prefs.remove(FILE_PATH);

            // Update the stage title.
            mPrimaryStage.setTitle(PRIMARY_STAGE_TITLE);
        }
    }

    //TODO Add to repository
    public void loadListsFromFileCSV(File file) {
        BufferedReader br;
        try {
            //Create the file reader

            br = new BufferedReader(new InputStreamReader(new FileInputStream(file),
                    StandardCharsets.UTF_8));
            CSVReader csvReader = new CSVReader(br);

            WordListCSVWrapper wordListCSVWrapper = new WordListCSVWrapper(csvReader.readAll());
            wordLists.clear();
            wordLists.addAll(wordListCSVWrapper.getListFromCSV());

            csvReader.close();
            // Save the file path to the registry.
            setFilePath(file);

            //After loading lists FromFile
            loadResultsFromCSV(wordLists);

        } catch (Exception e) { // catches ANY exception
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Could not load data");
            alert.setContentText("Could not load data from file:\n" + file.getPath());

            alert.showAndWait();
        }
    }

    //TODO Add to repository
    private void loadResultsFromCSV(ObservableList<WordList> wordLists) {
        File file = new File(RESULTS_FILE_CSV_NAME);
        if (file.exists()) {
            try {
                //Create the file reader
                BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file),
                        StandardCharsets.UTF_8));

                CSVReader csvReader = new CSVReader(br);

                ResultsListCSVWrapper resultsListCSVWrapper = new ResultsListCSVWrapper(csvReader.readAll());
                participants.clear();
                participants.addAll(resultsListCSVWrapper.getListFromCSV(wordLists));

                csvReader.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    //TODO Add to repository
    /**
     * Saves the current person data to the specified file.
     *
     * @param file filename
     */
    public void saveResultsToFileCSV(File file) {

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
            setFilePath(file);
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
