package pl.swps.viewmodel;

import javafx.collections.ObservableList;
import pl.swps.data.repository.AppRepository;
import pl.swps.common.ScreensNavigator;
import pl.swps.model.WordList;

public class HomeViewModel extends ViewModel {
    private ScreensNavigator mScreensNavigator;
    private AppRepository mRepository;

    public HomeViewModel(ScreensNavigator screensNavigator, AppRepository repository) {

        mScreensNavigator = screensNavigator;
        mRepository = repository;
    }

    public ObservableList<WordList> getWordLists() {
        return mRepository.getWordLists();
    }

    public void loadWordsList() {
        mRepository.loadWordsList();
    }
}
