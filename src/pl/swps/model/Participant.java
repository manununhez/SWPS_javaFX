package pl.swps.model;

import java.time.LocalDateTime;
import java.util.List;

public class Participant {
    public List<WordList> wordLists;
    public String category;
    public LocalDateTime timestamp;

    public Participant(List<WordList> wordLists) {
        this.wordLists = wordLists;
        this.category = wordLists.get(0).category;
        this.timestamp = LocalDateTime.now();
    }
}
