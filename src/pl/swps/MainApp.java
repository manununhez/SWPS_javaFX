package pl.swps;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import pl.swps.controller.AddNewPerson;
import pl.swps.controller.ExperimentOverview;
import pl.swps.controller.ScreenController;
import pl.swps.controller.StartExperiment;

import java.io.IOException;

public class MainApp extends Application {
    private static final String VIEW_ROOT_LAYOUT_FXML = "view/RootLayout.fxml";
    private static final String VIEW_EXPERIMENT_OVERVIEW_FXML = "view/ExperimentOverview.fxml";
    private static final String VIEW_ADD_NEW_PERSON_FXML = "view/AddNewPerson.fxml";
    private static final String VIEW_START_EXPERIMENT_FXML = "view/StartExperiment.fxml";
    private static final String PRIMARY_STAGE_TITLE = "SWSPApp";
    private static final String SECONDARY_STAGE_TITLE = "Starting Experiment";

    private Stage primaryStage;
    private BorderPane rootLayout;
    private ScreenController screenController;

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
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource(VIEW_EXPERIMENT_OVERVIEW_FXML));
            AnchorPane experimentOverview = loader.load();

            //we add this pane to the stack
            screenController.addScreen(experimentOverview);
            // Set person overview into the center of root layout.
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
    public void showAddNewPerson() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource(VIEW_ADD_NEW_PERSON_FXML));
            AnchorPane addNewPerson = loader.load();

            //we add this pane to the stack
            screenController.addScreen(addNewPerson);
            // Set person overview into the center of root layout.
            screenController.activate();


            //Give the controller acces to the main app.
            AddNewPerson controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
     * Opens a dialog to edit details for the specified person. If the user
     * clicks OK, the changes are saved into the provided person object and true
     * is returned.
     */
    public void showStartExperiment() {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource(VIEW_START_EXPERIMENT_FXML));
            AnchorPane page = loader.load();

            // Create the dialog Stage.
            Stage secondaryStage = new Stage();
            secondaryStage.setTitle(SECONDARY_STAGE_TITLE);
            secondaryStage.initModality(Modality.WINDOW_MODAL);
            secondaryStage.initOwner(primaryStage);
            secondaryStage.setMaximized(true);

            //new scene
            Scene scene = new Scene(page);
            secondaryStage.setScene(scene);

            // Set the person into the controller.
            StartExperiment controller = loader.getController();
            controller.setStage(secondaryStage);

            // Show the dialog and wait until the user closes it
            secondaryStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
