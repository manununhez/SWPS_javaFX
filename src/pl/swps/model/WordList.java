package pl.swps.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class WordList implements Serializable {
    public static final String CATEGORY_POSITIVE = "Positive";
    public static final String CATEGORY_NEGATIVE = "Negative";

    public String category;
    public String key;
    public List<String> values;
    private StringProperty keyProperty;
    private StringProperty categoryProperty;


    public WordList(String category, String key, List<String> values) {
        this.category = category;
        this.key = key;
        this.values = new ArrayList<>(); //to avoid referencing and overwrite the same list, we create a new copy of the list instead of referencing it
        this.values.addAll(values);

        this.keyProperty = new SimpleStringProperty(key);
        this.categoryProperty = new SimpleStringProperty(category);

    }

    public List<String> getValuesWithSpaces() {
        List<String> temp = new ArrayList<>();
        for (String value : values) {
            temp.add(value);
            temp.add("");
        }

        return temp;
    }

    public StringProperty getKeyProperty() {
        return keyProperty;
    }

    public StringProperty getCategoryProperty() {
        return categoryProperty;
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
