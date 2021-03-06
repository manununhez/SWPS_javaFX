package pl.swps.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import pl.swps.MainApp;
import pl.swps.model.Participant;
import pl.swps.model.StyleDesign;
import pl.swps.model.StyleDesign.StyleType;
import pl.swps.model.WordList;
import pl.swps.viewmodel.AddNewParticipantViewModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class AddNewParticipant {
    private static ObservableList<String> wordListTmp = FXCollections.observableArrayList();
    @FXML
    private TextField etParticipantNumber;
    @FXML
    private TextField etYearsEduc;
    @FXML
    private ToggleGroup toggleGroupNumber;
    @FXML
    private ToggleGroup toggleGroupSex;
    @FXML
    private ListView listView;
    @FXML
    private ComboBox comboBoxStyle;
    @FXML
    private Button btnSave;
    @FXML
    private AnchorPane anchorPaneExample;
    @FXML
    private Label labelExample;
    @FXML
    private ComboBox comboBoxFontType;
    @FXML
    private Spinner spinnerFontSize;

    private HashMap<String, WordList> hashMap = new HashMap<>();


    private MainApp mainApp;
    private ObservableList<String> positiveWordList = FXCollections.observableArrayList();
    private ObservableList<String> negativeWordList = FXCollections.observableArrayList();
    private AddNewParticipantViewModel mViewModel;

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        toggleGroupNumber.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println(observable.getValue().toString());
            String category = ((RadioButton) observable.getValue()).getText();
            if (category.equals(WordList.CATEGORY_NEGATIVE))
                setListView(negativeWordList);
            else if (category.equals(WordList.CATEGORY_POSITIVE))
                setListView(positiveWordList);
        });


        etParticipantNumber.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d{0,3}")) {
                etParticipantNumber.setText(oldValue);
            } else {
                etParticipantNumber.setText(newValue);
                isInputValid();
            }
        });

        etYearsEduc.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d{0,2}")) {
                etYearsEduc.setText(oldValue);
            } else {
                etYearsEduc.setText(newValue);
                isInputValid();
            }
        });

        comboBoxStyle.getItems().addAll(StyleType.GREEN.toString(),
                StyleType.SEPIA.toString(),
                StyleType.BLACK.toString(),
                StyleType.LIGHT.toString(),
                StyleType.DARK.toString(),
                StyleType.WHITE.toString());


        // Default value for comboBox
        StyleDesign styleDesign = StyleDesign.newInstance(StyleType.LIGHT);
        comboBoxStyle.setValue(StyleType.LIGHT.toString());
        anchorPaneExample.setBackground(new Background(new BackgroundFill(Paint.valueOf(styleDesign.backgroundColor), CornerRadii.EMPTY, Insets.EMPTY)));
        labelExample.setTextFill(Paint.valueOf(styleDesign.fontColor));


        comboBoxStyle.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            String item = (String) observable.getValue();
            String selectedColor;
            String selectedFontColor;
            StyleDesign styleDesign1;
            if (item.contains(StyleType.GREEN.toString())) {
                styleDesign1 = StyleDesign.newInstance(StyleType.GREEN);

            } else if (item.contains(StyleType.SEPIA.toString())) {
                styleDesign1 = StyleDesign.newInstance(StyleType.SEPIA);

            } else if (item.contains(StyleType.BLACK.toString())) {
                styleDesign1 = StyleDesign.newInstance(StyleType.BLACK);

            } else if (item.contains(StyleType.WHITE.toString())) {
                styleDesign1 = StyleDesign.newInstance(StyleType.WHITE);

            } else if (item.contains(StyleType.LIGHT.toString())) {
                styleDesign1 = StyleDesign.newInstance(StyleType.LIGHT);

            } else {
                styleDesign1 = StyleDesign.newInstance(StyleType.DARK);

            }

            selectedColor = styleDesign1.backgroundColor;
            selectedFontColor = styleDesign1.fontColor;

            anchorPaneExample.setBackground(new Background(new BackgroundFill(Paint.valueOf(selectedColor), CornerRadii.EMPTY, Insets.EMPTY)));
            labelExample.setTextFill(Paint.valueOf(selectedFontColor));

        });

        List<String> families = Font.getFamilies();
        comboBoxFontType.setItems(FXCollections.observableList(families));
        comboBoxFontType.setMaxWidth(Double.MAX_VALUE);
        comboBoxFontType.getSelectionModel().select("Georgia");

        labelExample.setFont(new Font(comboBoxFontType.getSelectionModel().getSelectedItem().toString(), 15.0));

        comboBoxFontType.getSelectionModel().selectedItemProperty().addListener((observableValue, o, t1) -> {
            String item = (String) observableValue.getValue();
            labelExample.setFont(new Font(item, 15.0));
        });


        // Value factory.
        SpinnerValueFactory<Integer> valueFactory = //
                new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, 47);//default value 47

        spinnerFontSize.setValueFactory(valueFactory);

        btnSave.setDisable(true);

    }

    private void setListView(ObservableList<String> words) {

        wordListTmp = FXCollections.observableArrayList();

        wordListTmp.addAll(words);

        listView.setItems(words);
        listView.setCellFactory(param -> new WordCell());
    }

    private void setWordList(List<WordList> originalWordLists) {

        if(originalWordLists.isEmpty())btnSave.setDisable(true);

        for (WordList wordList : originalWordLists) {
            hashMap.put(wordList.key, wordList);
            if (wordList.category.equals(WordList.CATEGORY_POSITIVE)) {
                positiveWordList.add(wordList.key);
            } else if (wordList.category.equals(WordList.CATEGORY_NEGATIVE)) {
                negativeWordList.add(wordList.key);
            }
        }

        setListView(positiveWordList); //default
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        mViewModel = mainApp.getApplicationCompositionRoot().getViewModelFactory().get(AddNewParticipantViewModel.class);

        setWordList(mViewModel.getWordLists());
    }

    /**
     * Validates the user input in the text fields.
     */
    private void isInputValid() {
        String errorMessage = "";

        if (etParticipantNumber.getText() == null || etParticipantNumber.getText().length() == 0) {
            errorMessage += "No valid participant number!\n";
        }
        if (etYearsEduc.getText() == null || etYearsEduc.getText().length() == 0) {
            errorMessage += "No valid years of education!\n";
        }

        if (errorMessage.length() == 0) {
            btnSave.setDisable(false);
        } else {
            btnSave.setDisable(true);
        }
    }


    @FXML
    private void handleSaveAndStart(ActionEvent actionEvent) {

        RadioButton selectedSexRadioButton = (RadioButton) toggleGroupSex.getSelectedToggle();
        RadioButton selectedGroupRadioButton = (RadioButton) toggleGroupNumber.getSelectedToggle();
        String selectedFontType = (String) comboBoxFontType.getSelectionModel().getSelectedItem();
        int selectedFontSize = (int) spinnerFontSize.getValue();

        List<WordList> shuffleWordList = new ArrayList<>();

        if (selectedGroupRadioButton.getText().equals(WordList.CATEGORY_POSITIVE)) {
            for (String key : positiveWordList) {
                shuffleWordList.add(hashMap.get(key));
            }
        } else if (selectedGroupRadioButton.getText().equals(WordList.CATEGORY_NEGATIVE)) {
            for (String key : negativeWordList) {
                shuffleWordList.add(hashMap.get(key));
            }
        }

        Participant participant = new Participant(selectedSexRadioButton.getText(),
                Integer.valueOf(etParticipantNumber.getText()),
                Integer.valueOf(etYearsEduc.getText()),
                shuffleWordList);

        System.out.println(participant);




        //go to test
        StyleDesign selectedStyleDesign = StyleDesign.getStyleInstance((String) comboBoxStyle.getValue());
        selectedStyleDesign.fontSize = selectedFontSize;
        selectedStyleDesign.fontName = selectedFontType;

        mViewModel.showStartExperiment(participant, selectedStyleDesign);

        mViewModel.addParticipant(participant);

        mViewModel.saveResults(false);

    }


    private class WordCell extends ListCell<String> {

        static final String PAPER_X_JAVA_ICON_48_PX_PNG = "paper-x-java-icon-48px.png";

        WordCell() {
            ListCell thisCell = this;

            setContentDisplay(ContentDisplay.TEXT_ONLY);
            setAlignment(Pos.CENTER_LEFT);

            setOnDragDetected(event -> {
                if (getItem() == null) {
                    return;
                }


                Dragboard dragboard = startDragAndDrop(TransferMode.MOVE);
                ClipboardContent content = new ClipboardContent();
                content.putString(getItem());

                dragboard.setDragView(new Image(PAPER_X_JAVA_ICON_48_PX_PNG));
                dragboard.setContent(content);


                event.consume();
            });

            setOnDragOver(event -> {
                if (event.getGestureSource() != thisCell &&
                        event.getDragboard().hasString()) {
                    event.acceptTransferModes(TransferMode.MOVE);
                }


                event.consume();
            });

            setOnDragEntered(event -> {
                if (event.getGestureSource() != thisCell &&
                        event.getDragboard().hasString()) {
                    setOpacity(0.3);


                }
            });

            setOnDragExited(event -> {
                if (event.getGestureSource() != thisCell &&
                        event.getDragboard().hasString()) {
                    setOpacity(1);

                }
            });

            setOnDragDropped(event -> {
                if (getItem() == null) {
                    return;
                }


                Dragboard db = event.getDragboard();
                boolean success = false;

                if (db.hasString()) {
                    ObservableList<String> items = getListView().getItems();
                    int draggedIdx = items.indexOf(db.getString());
                    int thisIdx = items.indexOf(getItem());

                    String temp = wordListTmp.get(draggedIdx);
                    wordListTmp.set(draggedIdx, wordListTmp.get(thisIdx));
                    wordListTmp.set(thisIdx, temp);

                    items.set(draggedIdx, getItem());
                    items.set(thisIdx, db.getString());

                    List<String> itemsCopy = new ArrayList<>(getListView().getItems());
                    getListView().getItems().setAll(itemsCopy);

                    System.out.println(itemsCopy);

                    success = true;
                }
                event.setDropCompleted(success);

                event.consume();
            });

            setOnDragDone(DragEvent::consume);
        }

        @Override
        protected void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);

            if (empty || item == null) {
                setText(null);
            } else {
                setText(wordListTmp.get(getListView().getItems().indexOf(item)));

            }
        }
    }

}
