package pl.swps.controller;

import javafx.event.ActionEvent;
import pl.swps.MainApp;

public class AddNewPerson {
    private MainApp mainApp;

    public void handleSaveAndStart(ActionEvent actionEvent) {
        //TODO save person details
        //go to test
        mainApp.showStartExperiment();
    }

    public void handleCancel(ActionEvent actionEvent) {
        mainApp.backToPreviousScene();
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
}
