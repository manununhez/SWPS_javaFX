package pl.swps.viewmodel;

import javafx.collections.ObservableList;
import pl.swps.data.repository.AppRepository;
import pl.swps.common.ScreensNavigator;
import pl.swps.model.Participant;
import pl.swps.model.StyleDesign;
import pl.swps.model.WordList;

public class AddNewParticipantViewModel extends ViewModel {
    private ScreensNavigator screensNavigator;
    private AppRepository mRepository;

    public AddNewParticipantViewModel(ScreensNavigator screensNavigator, AppRepository repository) {

        this.screensNavigator = screensNavigator;
        mRepository = repository;
    }

    public void showStartExperiment(Participant participant, StyleDesign selectedStyleDesign) {
        screensNavigator.goToStartExperiment(selectedStyleDesign, participant);
    }

    public ObservableList<WordList> getWordLists() {
        return mRepository.getWordLists();
    }

    public void addParticipant(Participant participant) {
        mRepository.addParticipant(participant);
    }

    public void saveResults(boolean chooseSource) {
        mRepository.saveResults(chooseSource);
    }
}
