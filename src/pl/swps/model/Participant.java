package pl.swps.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import pl.swps.util.DateUtil;

import java.time.LocalDateTime;
import java.util.List;

public class Participant {
    public List<WordList> wordLists;
    public String category;
    public LocalDateTime timestamp;
    public String sex;
    public int participantNumber;
    public int yearsOfEducation;

    private StringProperty participantNumberProperty;
    private StringProperty timestampProperty;

    public Participant(String sex, int participantNumber, int yearsOfEducation, List<WordList> wordLists) {
        this.wordLists = wordLists;
        this.category = wordLists.get(0).category;
        this.timestamp = LocalDateTime.now();
        this.sex = sex;
        this.participantNumber = participantNumber;
        this.yearsOfEducation = yearsOfEducation;

        this.participantNumberProperty = new SimpleStringProperty(String.valueOf(participantNumber));

        this.timestampProperty = new SimpleStringProperty(DateUtil.format(timestamp));
    }

    public StringProperty getParticipantNumberProperty() {
        return participantNumberProperty;
    }

    public StringProperty getTimestampProperty() {
        return timestampProperty;
    }

    public String getTimestampFormatted() {
        return DateUtil.format(timestamp);
    }

    @Override
    public String toString() {
        return "Participant{" +
                "wordLists=" + wordLists +
                ", category='" + category + '\'' +
                ", timestamp=" + timestamp +
                ", sex='" + sex + '\'' +
                ", participantNumber=" + participantNumber +
                ", yearsOfEducation=" + yearsOfEducation +
                '}';
    }
}
