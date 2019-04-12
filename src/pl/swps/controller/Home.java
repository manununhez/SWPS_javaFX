package pl.swps.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import pl.swps.MainApp;
import pl.swps.model.WordList;

public class Home {
    @FXML
    public TableView<WordList> wordsTable;
    @FXML
    public TableColumn<WordList, String> listIdColumn;
    @FXML
    public TableColumn<WordList, String> categoryColumn;
    @FXML
    public ListView listViewWords;

    private MainApp mainApp;



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
//        deleteBtn.setDisable(false);

        if (wordList != null) {
            listViewWords.getItems().clear();
            listViewWords.getItems().addAll(wordList.values);
        }

    }
}
