package pl.swps.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import pl.swps.MainApp;

import java.io.File;

public class RootLayout {
    private static final String LOGO_APP = "logo.jpg";
    private static final String CSV_EXTENSION = ".csv";
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
        Image image = new Image(LOGO_APP);
        imageViewLogo.setImage(image);
    }


    public void handleNew(ActionEvent actionEvent) {
        mainApp.showNewExperiment();
    }

    /**
     * Shows the experiment overview inside the root layout.
     */
    public void handleHome(ActionEvent actionEvent) {
        mainApp.showHome();
    }

    public void handleResults(ActionEvent actionEvent) {
        mainApp.showResults();
    }

    public void handleUpload(ActionEvent actionEvent) {
        mainApp.showHome();

        FileChooser fileChooser = new FileChooser();

        // Set extension filter

        FileChooser.ExtensionFilter extFilterCSV = new FileChooser.ExtensionFilter(
                "CSV files (*.csv)", "*.csv");
        fileChooser.getExtensionFilters().add(extFilterCSV);


        // Show open file dialog
        File file = fileChooser.showOpenDialog(mainApp.getPrimaryStage());


        if (file != null) {
            if (file.getPath().endsWith(CSV_EXTENSION)) {
                mainApp.loadPersonDataFromFileCSV(file);
            }
        }
    }


    /**
     * Opens an about dialog.
     */
    @FXML
    private void handleAbout() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("SWPS Univeristy App");
        alert.setHeaderText("About");
        alert.setContentText("Developed by: Manuel Nunez\nWebsite: http://manuelnunhez.com\nEmail:manuel.nunhez90@gmail.com");

        alert.showAndWait();
    }

    /**
     * Closes the application.
     */
    @FXML
    private void handleExit() {
        System.exit(0);
    }
}
