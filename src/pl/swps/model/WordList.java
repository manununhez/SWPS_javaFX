package pl.swps.model;

import java.util.ArrayList;
import java.util.List;

public class WordList {
    public static final String CATEGORY_POSITIVE = "positive";
    public static final String CATEGORY_NEGATIVE = "negative";

    public String category;
    public String key;
    public List<String> values;


    public WordList(String category, String key, List<String> values) {
        this.category = category;
        this.key = key;
        this.values = new ArrayList<>(); //to avoid referencing and overwrite the same list, we create a new copy of the list instead of referencing it
        this.values.addAll(values);

    }

    @Override
    public String toString() {
        return "WordList{" +
                "category='" + category + '\'' +
                ", key='" + key + '\'' +
                ", values=" + values +
                '}';
    }
}
