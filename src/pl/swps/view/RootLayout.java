package pl.swps.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import pl.swps.MainApp;

public class RootLayout {
    private static final String LOGO_APP = "logo.jpg";
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

    @FXML
    private void handleNew(ActionEvent actionEvent) {
        mainApp.showNewExperiment();
    }

    /**
     * Shows the experiment overview inside the root layout.
     */
    @FXML
    private void handleHome(ActionEvent actionEvent) {
        mainApp.showHome();
    }

    @FXML
    private void handleResults(ActionEvent actionEvent) {
        mainApp.showResults();
    }

    @FXML
    private void handleInstructions(ActionEvent actionEvent) {
        mainApp.showInstructions();
    }



    /**
     * Opens an about dialog.
     */
    @FXML
    private void handleAbout() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("SWPS University App");
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
