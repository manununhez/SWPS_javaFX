package pl.swps.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import pl.swps.MainApp;
import pl.swps.model.InstructionMessages;
import pl.swps.model.InstructionsCSVWrapper;
import pl.swps.util.CSVReader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class Instructions {
    private static final String CSV_EXTENSION = ".csv";

    @FXML
    private TextArea taTaskInstructions;
    @FXML
    private TextArea taTaskButtons;
    @FXML
    private TextArea taWriteResults;
    @FXML
    private TextArea taTaskButtonsNext;
    @FXML
    private TextArea taFinalMessage;
    @FXML
    private Button btnSave;

    private InstructionMessages instructionMessages;
    private MainApp mainApp;

    @FXML
    private void initialize() {
        btnSave.setDisable(true);
    }

    private void setTextMessages(InstructionMessages instructionMessages) {
        taTaskInstructions.setText(instructionMessages.messageS1_taskInstructions);
        taTaskButtons.setText(instructionMessages.messageS2_taskProcedureButtons);
        taWriteResults.setText(instructionMessages.messageS3_writeResults);
        taTaskButtonsNext.setText(instructionMessages.messageS4_nextTaskProcedureButtons);
        taFinalMessage.setText(instructionMessages.messageS5_thanks);

        taTaskInstructions.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.equals(Instructions.this.instructionMessages.messageS1_taskInstructions))
                btnSave.setDisable(false);
            else btnSave.setDisable(true);
        });

        taTaskButtons.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.equals(Instructions.this.instructionMessages.messageS2_taskProcedureButtons))
                btnSave.setDisable(false);
            else btnSave.setDisable(true);
        });

        taWriteResults.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.equals(Instructions.this.instructionMessages.messageS3_writeResults))
                btnSave.setDisable(false);
            else btnSave.setDisable(true);
        });

        taTaskButtonsNext.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.equals(Instructions.this.instructionMessages.messageS4_nextTaskProcedureButtons))
                btnSave.setDisable(false);
            else btnSave.setDisable(true);
        });

        taFinalMessage.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.equals(Instructions.this.instructionMessages.messageS5_thanks)) btnSave.setDisable(false);
            else btnSave.setDisable(true);
        });
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public void setInstructions(InstructionMessages instructionMessages) {
        this.instructionMessages = instructionMessages;

        setTextMessages(instructionMessages);
    }

    @FXML
    private void handleSaveChanges(ActionEvent actionEvent) {
        instructionMessages.messageS1_taskInstructions = taTaskInstructions.getText();
        instructionMessages.messageS2_taskProcedureButtons = taTaskButtons.getText();
        instructionMessages.messageS3_writeResults = taWriteResults.getText();
        instructionMessages.messageS4_nextTaskProcedureButtons = taTaskButtonsNext.getText();
        instructionMessages.messageS5_thanks = taFinalMessage.getText();

        btnSave.setDisable(true); //success

    }

    @FXML
    private void handleUploadInstructions(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();

        // Set extension filter

        FileChooser.ExtensionFilter extFilterCSV = new FileChooser.ExtensionFilter(
                "CSV files (*.csv)", "*.csv");
        fileChooser.getExtensionFilters().add(extFilterCSV);


        // Show open file dialog
        File file = fileChooser.showOpenDialog(mainApp.getPrimaryStage());


        if (file != null) {
            if (file.getPath().endsWith(CSV_EXTENSION)) {
                loadInstructionsFromFileCSV(file);
            }
        }
    }


    public void loadInstructionsFromFileCSV(File file) {
        BufferedReader br;
        try {
            //Create the file reader
//            br = new BufferedReader(new FileReader(file));

            br = new BufferedReader(new InputStreamReader(new FileInputStream(file),
                    StandardCharsets.UTF_8));
            CSVReader csvReader = new CSVReader(br);

            InstructionsCSVWrapper instructionsCSVWrapper = new InstructionsCSVWrapper(csvReader.readAll());

            setTextMessages(instructionsCSVWrapper.getInstructionsFromCSV());

            csvReader.close();
            // Save the file path to the registry.
//            mainApp.setPersonFilePath(file);

        } catch (Exception e) { // catches ANY exception
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Could not load data");
            alert.setContentText("Could not load data from file:\n" + file.getPath());

            alert.showAndWait();
        }
    }
}
