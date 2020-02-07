package pl.swps.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import pl.swps.MainApp;
import pl.swps.model.Participant;
import pl.swps.model.WordList;
import pl.swps.viewmodel.ResultsViewModel;

import java.util.ArrayList;
import java.util.List;

public class Results {
    @FXML
    private Label sexLabel;
    @FXML
    private Label educLabel;
    @FXML
    private Label categoryLabel;
    @FXML
    private Label listOrderLabel;
    @FXML
    private TableColumn<Participant, String> dateExperimentColumn;
    @FXML
    private TableColumn<Participant, String> participantNumberColumn;
    @FXML
    private TableView<Participant> resultsTable;

    private MainApp mainApp;
    private ResultsViewModel mViewModel;


    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        // Initialize the person table with the two columns.
        participantNumberColumn.setCellValueFactory(cellData -> cellData.getValue().getParticipantNumberProperty());
        dateExperimentColumn.setCellValueFactory(cellData -> cellData.getValue().getTimestampProperty());

        //Clear person details
        showParticipants(null);

        //Listen for selection changes and show the person details whe changed.
        resultsTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showParticipants(newValue));

    }

    /**
     * Is called by the main application to give a reference back to itself.
     *
     * @param mainApp main app
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        mViewModel = mainApp.getApplicationCompositionRoot().getViewModelFactory().get(ResultsViewModel.class);

        // Add observable list data to the table
        resultsTable.setItems(mViewModel.getParticipants());
    }

    private void showParticipants(Participant participant) {
//        deleteBtn.setDisable(false);

        if (participant != null) {
            sexLabel.setText(participant.sex);
            educLabel.setText(String.valueOf(participant.yearsOfEducation));
            categoryLabel.setText(participant.category);

            List<String> key = new ArrayList<>();

            for (WordList wordList : participant.wordLists)
                key.add(wordList.key);

            listOrderLabel.setText(String.join(",", key));

        }
    }

    @FXML
    private void handleExportResults(ActionEvent actionEvent) {
        mViewModel.saveResults(true);

    }
}
