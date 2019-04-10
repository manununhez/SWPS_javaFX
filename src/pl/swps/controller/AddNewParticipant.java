package pl.swps.controller;

import javafx.event.ActionEvent;
import pl.swps.MainApp;
import pl.swps.model.Participant;
import pl.swps.model.WordList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AddNewParticipant {
    private MainApp mainApp;
    private List<WordList> originalWordLists;

    public void handleSaveAndStart(ActionEvent actionEvent) {
        //TODO If(validate) then {}
        List<WordList> shuffleWordList = new ArrayList<>();
        String category = WordList.CATEGORY_POSITIVE; //or WordList.CATEGORY_NEGATIVE
        //TODO is category or negative
        for (WordList wordList : originalWordLists) {
            if (wordList.category.equals(category))
                shuffleWordList.add(wordList);
        }

//        System.out.println("Original"+originalWordLists);

        Collections.shuffle(shuffleWordList);
//        System.out.println("Shuffle"+shuffleWordList);

        Participant participant = new Participant(shuffleWordList);

        mainApp.setParticipant(participant);
        //go to test
        mainApp.showStartExperiment();
    }

    public void handleCancel(ActionEvent actionEvent) {
        mainApp.backToPreviousScene();
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public void setWordList(List<WordList> originalWordLists) {
        this.originalWordLists = originalWordLists;
    }
}
