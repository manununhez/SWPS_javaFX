package pl.swps.model;

import java.time.LocalDateTime;
import java.util.List;

public class Participant {
    public List<WordList> wordLists;
    public String category;
    public LocalDateTime timestamp;
    public String sex;
    public int participantNumber;
    public int yearsOfEducation;

    public Participant(String sex, int participantNumber, int yearsOfEducation, List<WordList> wordLists) {
        this.wordLists = wordLists;
        this.category = wordLists.get(0).category;
        this.timestamp = LocalDateTime.now();
        this.sex = sex;
        this.participantNumber = participantNumber;
        this.yearsOfEducation = yearsOfEducation;
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
