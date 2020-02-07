package pl.swps.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import pl.swps.MainApp;
import pl.swps.model.InstructionMessages;
import pl.swps.viewmodel.InstructionsViewModel;

public class Instructions {
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
    private InstructionsViewModel mViewModel;

    @FXML
    private void initialize() {
        btnSave.setDisable(true);
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        mViewModel = mainApp.getApplicationCompositionRoot().getViewModelFactory().get(InstructionsViewModel.class);

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
        setTextMessages(mViewModel.loadInstructions(true));
    }

}
