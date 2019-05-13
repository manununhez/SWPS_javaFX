package pl.swps.model;

import javafx.collections.ObservableList;

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
    private List resultWords;

    public ResultsListCSVWrapper(List resultWords) {
        this.resultWords = resultWords;
        participantList = new ArrayList<>();
    }

    public List<Participant> getListFromCSV(ObservableList<WordList> wordLists) {
        for (int i = 1; i < resultWords.size(); i++) {
            String[] token = (String[]) resultWords.get(i);
            String[] values = token[LISTS_IDX].split(",");
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
