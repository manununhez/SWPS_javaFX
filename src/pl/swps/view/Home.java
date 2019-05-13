package pl.swps.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.FileChooser;
import pl.swps.MainApp;
import pl.swps.model.WordList;

import java.io.File;

public class Home {
    private static final String CSV_EXTENSION = ".csv";
    private MainApp mainApp;

    @FXML
    private TableView<WordList> wordsTable;
    @FXML
    private TableColumn<WordList, String> listIdColumn;
    @FXML
    private TableColumn<WordList, String> categoryColumn;
    @FXML
    private ListView listViewWords;




    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        // Initialize the person table with the two columns.
        listIdColumn.setCellValueFactory(cellData -> cellData.getValue().getKeyProperty());
        categoryColumn.setCellValueFactory(cellData -> cellData.getValue().getCategoryProperty());

        //Clear person details
        showWordDetails(null);

        //Listen for selection changes and show the person details whe changed.
        wordsTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showWordDetails(newValue));

//        deleteBtn.setDisable(true);

    }

    /**
     * Is called by the main application to give a reference back to itself.
     *
     * @param mainApp main app
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        // Add observable list data to the table
        wordsTable.setItems(mainApp.getWordLists());
    }

    private void showWordDetails(WordList wordList) {
        if (wordList != null) {
            listViewWords.getItems().clear();
            listViewWords.getItems().addAll(wordList.values);
        }

    }


    public void handleUploadLists(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();

        // Set extension filter

        FileChooser.ExtensionFilter extFilterCSV = new FileChooser.ExtensionFilter(
                "CSV files (*.csv)", "*.csv");
        fileChooser.getExtensionFilters().add(extFilterCSV);
        fileChooser.setTitle("Open file...");

        // Show open file dialog
        File file = fileChooser.showOpenDialog(mainApp.getPrimaryStage());


        if (file != null) {
            if (file.getPath().endsWith(CSV_EXTENSION)) {
                mainApp.loadPersonDataFromFileCSV(file);
            }
        }
    }
}
