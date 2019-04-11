package pl.swps.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import pl.swps.MainApp;

import java.io.File;

public class RootLayout {
    public ImageView imageViewLogo;
    // Reference to the main application
    private MainApp mainApp;

    /**
     * Is called by the main application to give a reference back to itself.
     *
     * @param mainApp Main App
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }


    @FXML
    private void initialize() {
        File file = new File("logo.png");
        Image image = new Image("logo.jpg");
        imageViewLogo.setImage(image);
    }


    public void handleNew(ActionEvent actionEvent) {
        mainApp.showNewExperiment();
    }

    /**
     * Shows the experiment overview inside the root layout.
     */
    public void handleHome(ActionEvent actionEvent) {
        mainApp.showExperimentOverview();
    }

    public void handleResults(ActionEvent actionEvent) {
    }

    public void handleUpload(ActionEvent actionEvent) {
    }

    public void handleExport(ActionEvent actionEvent) {
    }

    public void handleAbout(ActionEvent actionEvent) {
    }

    /**
     * Closes the application.
     */
    @FXML
    private void handleExit() {
        System.exit(0);
    }
}
