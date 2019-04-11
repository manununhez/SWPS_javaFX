package pl.swps.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Paint;
import javafx.util.Callback;
import pl.swps.MainApp;
import pl.swps.model.Participant;
import pl.swps.model.StyleDesign;
import pl.swps.model.WordList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class AddNewParticipant {
    private static final ObservableList<String> birds = FXCollections.observableArrayList();
    private static ObservableList<String> birdImages = FXCollections.observableArrayList();
    @FXML
    public TextField etParticipantNumber;
    @FXML
    public TextField etYearsEduc;
    @FXML
    public RadioButton rbMale;
    @FXML
    public RadioButton rbFemale;
    @FXML
    public RadioButton rbPositive;
    @FXML
    public RadioButton rbNegative;
    @FXML
    public ToggleGroup toggleGroupNumber;
    @FXML
    public ToggleGroup toggleGroupSex;
    @FXML
    public ListView listView;
    @FXML
    public ComboBox comboBoxStyle;
    private HashMap<String, WordList> hashMap = new HashMap<>();


    private MainApp mainApp;
    private List<WordList> originalWordLists;
    private Scene scene;
    private ObservableList<String> positiveWordList = FXCollections.observableArrayList();
    private ObservableList<String> negativeWordList = FXCollections.observableArrayList();

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
            }
        });

        etYearsEduc.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d{0,2}")) {
                etYearsEduc.setText(oldValue);
            } else {
                etYearsEduc.setText(newValue);
            }
        });

        comboBoxStyle.getItems().addAll(StyleDesign.StyleType.GREEN.toString(),
                StyleDesign.StyleType.SEPIA.toString(),
                StyleDesign.StyleType.BLACK.toString(),
                StyleDesign.StyleType.WHITE.toString());


        comboBoxStyle.setValue(StyleDesign.StyleType.SEPIA.toString());

        comboBoxStyle.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
            @Override
            public ListCell<String> call(ListView<String> param) {
                return new ListCell<String>() {

                    @Override
                    public void updateItem(String item,
                                           boolean empty) {
                        super.updateItem(item, empty);
                        if (item != null) {
                            setText(item);
                            String selectedColor;
                            String selectedFontColor;
                            if (item.contains(StyleDesign.StyleType.GREEN.toString())) {
                                StyleDesign styleDesign = StyleDesign.newInstance(StyleDesign.StyleType.GREEN);
                                selectedColor = styleDesign.backgroundColor;
                                selectedFontColor = styleDesign.fontColor;
                            } else if (item.contains(StyleDesign.StyleType.SEPIA.toString())) {
                                StyleDesign styleDesign = StyleDesign.newInstance(StyleDesign.StyleType.SEPIA);
                                selectedColor = styleDesign.backgroundColor;
                                selectedFontColor = styleDesign.fontColor;
                            } else if (item.contains(StyleDesign.StyleType.BLACK.toString())) {
                                StyleDesign styleDesign = StyleDesign.newInstance(StyleDesign.StyleType.BLACK);
                                selectedColor = styleDesign.backgroundColor;
                                selectedFontColor = styleDesign.fontColor;
                            } else {
                                StyleDesign styleDesign = StyleDesign.newInstance(StyleDesign.StyleType.WHITE);
                                selectedColor = styleDesign.backgroundColor;
                                selectedFontColor = styleDesign.fontColor;
                            }

                            setBackground(new Background(new BackgroundFill(Paint.valueOf(selectedColor), CornerRadii.EMPTY, Insets.EMPTY)));
                            setTextFill(Paint.valueOf(selectedFontColor));
                        } else {
                            setText(null);
                        }
                    }
                };
            }
        });

    }

    public void setListView(ObservableList<String> birds) {

        birdImages = FXCollections.observableArrayList();

        birds.forEach(bird -> birdImages.add(bird));

        listView.setItems(birds);
//        ListView<String> birdList = new ListView<>(birds);
        listView.setCellFactory(param -> new BirdCell());
//        listView.setPrefWidth(180);
//        listView.setStyle("-fx-focus-color: transparent;");
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public void handleSaveAndStart(ActionEvent actionEvent) {

        RadioButton selectedSexRadioButton = (RadioButton) toggleGroupSex.getSelectedToggle();
        RadioButton selectedGroupRadioButton = (RadioButton) toggleGroupNumber.getSelectedToggle();


        if (isInputValid()) {
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

            mainApp.setParticipant(participant);
            //go to test
            mainApp.showStartExperiment(getStyle((String) comboBoxStyle.getValue()));
        }
    }

    private StyleDesign getStyle(String value) {
        if (value.contains(StyleDesign.StyleType.GREEN.toString())) {
            return StyleDesign.newInstance(StyleDesign.StyleType.GREEN);
        } else if (value.contains(StyleDesign.StyleType.SEPIA.toString())) {
            return StyleDesign.newInstance(StyleDesign.StyleType.SEPIA);

        } else if (value.contains(StyleDesign.StyleType.BLACK.toString())) {
            return StyleDesign.newInstance(StyleDesign.StyleType.BLACK);

        } else {
            return StyleDesign.newInstance(StyleDesign.StyleType.WHITE);

        }

    }

    public void setWordList(List<WordList> originalWordLists) {
        this.originalWordLists = originalWordLists;


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

    public void handleCancel(ActionEvent actionEvent) {
        mainApp.backToPreviousScene();
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    /**
     * Validates the user input in the text fields.
     *
     * @return true if the input is valid
     */
    private boolean isInputValid() {
        String errorMessage = "";

        if (etParticipantNumber.getText() == null || etParticipantNumber.getText().length() == 0) {
            errorMessage += "No valid participant number!\n";
        }
        if (etYearsEduc.getText() == null || etYearsEduc.getText().length() == 0) {
            errorMessage += "No valid years of education!\n";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }

    private class BirdCell extends ListCell<String> {

        public BirdCell() {
            ListCell thisCell = this;

            setContentDisplay(ContentDisplay.TEXT_ONLY);
            setAlignment(Pos.CENTER_LEFT);

            setOnDragDetected(event -> {
                if (getItem() == null) {
                    return;
                }

//                ObservableList<String> items = getListView().getItems();

                Dragboard dragboard = startDragAndDrop(TransferMode.MOVE);
                ClipboardContent content = new ClipboardContent();
                content.putString(getItem());

                dragboard.setDragView(new Image("paper-x-java-icon-48px.png"));
                dragboard.setContent(content);

//                System.out.println("setOnDragDetected");
//
//                scene.setCursor(Cursor.OPEN_HAND);

                event.consume();
            });

            setOnDragOver(event -> {
                if (event.getGestureSource() != thisCell &&
                        event.getDragboard().hasString()) {
                    event.acceptTransferModes(TransferMode.MOVE);
                }

//                scene.setCursor(Cursor.CLOSED_HAND);
//
//                System.out.println("setOnDragOver");

                event.consume();
            });

            setOnDragEntered(event -> {
                if (event.getGestureSource() != thisCell &&
                        event.getDragboard().hasString()) {
                    setOpacity(0.3);
//                    scene.setCursor(Cursor.HAND);
//
//                    System.out.println("setOnDragEntered");

                }
            });

            setOnDragExited(event -> {
                if (event.getGestureSource() != thisCell &&
                        event.getDragboard().hasString()) {
                    setOpacity(1);
//                    System.out.println("setOnDragExited");
//                    scene.setCursor(Cursor.DEFAULT);
                }
            });

            setOnDragDropped(event -> {
                if (getItem() == null) {
                    return;
                }

//                System.out.println("setOnDragDropped");


                Dragboard db = event.getDragboard();
                boolean success = false;

                if (db.hasString()) {
                    ObservableList<String> items = getListView().getItems();
                    int draggedIdx = items.indexOf(db.getString());
                    int thisIdx = items.indexOf(getItem());

                    String temp = birdImages.get(draggedIdx);
                    birdImages.set(draggedIdx, birdImages.get(thisIdx));
                    birdImages.set(thisIdx, temp);

                    items.set(draggedIdx, getItem());
                    items.set(thisIdx, db.getString());

                    List<String> itemscopy = new ArrayList<>(getListView().getItems());
                    getListView().getItems().setAll(itemscopy);

                    System.out.println(itemscopy);

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
                setText(birdImages.get(getListView().getItems().indexOf(item)));

            }
        }
    }

}
