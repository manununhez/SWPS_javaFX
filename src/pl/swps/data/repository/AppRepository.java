package pl.swps.data.repository;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pl.swps.data.local.FileDataSource;
import pl.swps.model.InstructionMessages;
import pl.swps.model.Participant;
import pl.swps.model.WordList;

import java.util.List;

public class AppRepository {
    //For singleton instantiation
    private static AppRepository INSTANCE;
    private InstructionMessages mInstructionsMessages;
    private ObservableList<WordList> mWordLists;
    private ObservableList<Participant> mParticipants;

    private final FileDataSource mFileDataSource;

    private AppRepository(FileDataSource fileDataSource) {
        mFileDataSource = fileDataSource;
        mInstructionsMessages = new InstructionMessages();

        mWordLists = FXCollections.observableArrayList();
        mParticipants = FXCollections.observableArrayList();
    }

    public synchronized static AppRepository getInstance(FileDataSource fileDataSource) {
        if (INSTANCE == null) {
            synchronized (AppRepository.class) {
                INSTANCE = new AppRepository(fileDataSource);
            }
        }
        return INSTANCE;
    }

    public ObservableList<Participant> getParticipants() {
        return mParticipants;
    }

    public ObservableList<WordList> getWordLists() {
        return mWordLists;
    }


    public void loadWordsList() {
        List<WordList> wordList = mFileDataSource.loadWordListsFromCSV();

        mWordLists.clear();
        mWordLists.addAll(wordList);
    }

    public void loadParticipants() {
        List<Participant> results = mFileDataSource.loadParticipants(mWordLists);

        mParticipants.clear();
        mParticipants.addAll(results);
    }

    /**
     * @param chooseSource indicates if the user wants to upload data from specific source (file in this case).
     *                     true -> a FileChooser appears to the user to select its document
     */
    public void saveResults(boolean chooseSource) {
        mFileDataSource.saveResults(chooseSource, mParticipants);
    }

    /**
     * @param chooseSource indicates if the user wants to upload data from specific source (file in this case).
     *                     true -> a FileChooser appears to the user to select its document
     */
    public InstructionMessages loadInstructions(boolean chooseSource) {
        mInstructionsMessages = mFileDataSource.loadInstructions(chooseSource);
        return getInstructions();
    }

    public InstructionMessages getInstructions() {
        return mInstructionsMessages;
    }


    //TODO Add to repository
    public void addParticipant(Participant participant) {
        //In this point, participant contains all the information about the participant, including the selected list category,
        // and the randomized order of these lists

        //We add the participant to the list of participants, later will be saved to file
        mParticipants.add(participant);
    }

}
