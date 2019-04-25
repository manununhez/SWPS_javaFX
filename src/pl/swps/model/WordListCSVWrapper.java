package pl.swps.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WordListCSVWrapper {

    private static final int LIST_ID_IDX = 0;
    private static final int CATEGORY_IDX = 1;
    private List<WordList> wordLists;
    private List stringWords;

    public WordListCSVWrapper(List stringWords) {
        this.stringWords = stringWords;
        wordLists = new ArrayList<>();
    }

    public List<WordList> getListFromCSV() {
        for (Object stringWord : stringWords) {
            String[] token = (String[]) stringWord;
            List<String> values = new ArrayList<>(Arrays.asList(token).subList(2, token.length));
            wordLists.add(new WordList(token[CATEGORY_IDX], token[LIST_ID_IDX], values));
        }
        return wordLists;
    }
}
