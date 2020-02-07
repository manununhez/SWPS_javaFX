package pl.swps.common;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import pl.swps.MainApp;
import pl.swps.model.InstructionMessages;
import pl.swps.model.Participant;
import pl.swps.model.StyleDesign;
import pl.swps.view.*;

import java.io.IOException;

public class ScreensNavigator {
    private static final String VIEW_HOME_FXML = "view/Home.fxml";
    private static final String VIEW_ADD_NEW_PARTICIPANT_FXML = "view/AddNewParticipant.fxml";
    private static final String VIEW_RESULTS_FXML = "view/Results.fxml";
    private static final String VIEW_INSTRUCTIONS_FXML = "view/Instructions.fxml";
    private static final String VIEW_START_EXPERIMENT_FXML = "view/StartExperiment.fxml";
    private static final String SECONDARY_STAGE_TITLE = "Experiment started";

    private MainApp applicationContext;
    private ScreenController screenController;

    public ScreensNavigator(MainApp applicationContext, ScreenController screenController) {
        this.applicationContext = applicationContext;

        this.screenController = screenController;
    }

    /**
     * Shows the home inside the root layout.
     */
    public void goHome() {
        try {
            // Load home.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource(VIEW_HOME_FXML));
            AnchorPane home = loader.load();

            //we add this pane to the stack
            screenController.addScreen(home);
            // Set home into the center of root layout.
            screenController.activate();
//
//            //Give the controller acces to the main app.
            Home controller = loader.getController();
            controller.setMainApp(applicationContext);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Shows the new experiment inside the root layout.
     */
    public void goToNewExperiment() {
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
            controller.setMainApp(applicationContext);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Shows the results inside the root layout.
     */
    public void goToResults() {
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
            controller.setMainApp(applicationContext);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void goToInstructions(InstructionMessages instructionMessages) {
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
            controller.setMainApp(applicationContext);
            controller.setInstructions(instructionMessages);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Opens a dialog to edit details for the specified participant. If the user
     * clicks OK, the changes are saved into the provided participant object and true
     * is returned.
     *
     * @param style Experiment screen design
     */
    public void goToStartExperiment(StyleDesign style, Participant participant) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource(VIEW_START_EXPERIMENT_FXML));
            BorderPane page = loader.load();

            // Create the dialog Stage.
            Stage secondaryStage = new Stage();
            secondaryStage.setTitle(SECONDARY_STAGE_TITLE);
            secondaryStage.initModality(Modality.WINDOW_MODAL);
            secondaryStage.initOwner(applicationContext.getPrimaryStage());
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
            controller.setMainApp(applicationContext);
            controller.setStyle(style);
            controller.initScreenFlow();

            // Show the dialog and wait until the user closes it
            secondaryStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
