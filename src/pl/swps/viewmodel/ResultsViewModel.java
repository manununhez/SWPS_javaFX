package pl.swps.viewmodel;

import javafx.collections.ObservableList;
import pl.swps.data.repository.AppRepository;
import pl.swps.common.ScreensNavigator;
import pl.swps.model.Participant;

public class ResultsViewModel extends ViewModel {
    private ScreensNavigator mScreensNavigator;
    private AppRepository mRepository;

    public ResultsViewModel(ScreensNavigator screensNavigator, AppRepository repository) {

        mScreensNavigator = screensNavigator;
        mRepository = repository;
    }

    public ObservableList<Participant> getParticipants() {
        return mRepository.getParticipants();
    }

    public void saveResults(boolean chooseSource) {
        mRepository.saveResults(chooseSource);
    }
}
