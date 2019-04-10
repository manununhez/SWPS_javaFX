package pl.swps.controller;

import javafx.event.ActionEvent;
import pl.swps.MainApp;

public class ExperimentOverview {
    private MainApp mainApp;

    public void handleNewExperiment(ActionEvent actionEvent) {
        mainApp.showAddNewParticipant();
    }

    public void handleSeePreviousExperiment(ActionEvent actionEvent) {
    }

    /**
     * Is called by the main application to give a reference back to itself.
     *
     * @param mainApp main app
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;


    }
}
