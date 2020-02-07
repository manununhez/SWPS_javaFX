package pl.swps.data.local.model;

import javafx.collections.ObservableList;
import pl.swps.model.Participant;
import pl.swps.model.WordList;

import java.util.ArrayList;
import java.util.List;

public class ResultsListCSVWrapper {

    private static final int PARTICIPANT_ID_IDX = 0;
    private static final int SEX_IDX = 1;
    private static final int YEARS_EDUC_IDX = 2;
    private static final int DATE_EXP_IDX = 3;
    private static final int CATEGORY_IDX = 4;
    private static final int LISTS_IDX = 5;

    private List<Participant> participantList;
    private List mResultWords;

    public ResultsListCSVWrapper(List resultWords) {
        mResultWords = resultWords;
        participantList = new ArrayList<>();
    }

    public List<Participant> getListFromCSV(ObservableList<WordList> wordLists) {
        for (int i = 1; i < mResultWords.size(); i++) {
            String[] token = (String[]) mResultWords.get(i);
            String[] values = token[LISTS_IDX].split(",");

            //We extract the list of word list from the CSV, without considering the headers
            List<WordList> wordListTemp = new ArrayList<>();
            for (String wordId : values) {
                for (WordList wordList : wordLists) {
                    if (wordList.key.equals(wordId)) {
                        wordListTemp.add(wordList);
                        break;
                    }
                }
            }
            participantList.add(new Participant(token[SEX_IDX], Integer.valueOf(token[PARTICIPANT_ID_IDX]),
                    Integer.valueOf(token[YEARS_EDUC_IDX]), wordListTemp, token[DATE_EXP_IDX]));
        }
        return participantList;
    }
}
