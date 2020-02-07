package pl.swps.viewmodel;

import pl.swps.MainApp;
import pl.swps.common.ScreensNavigator;

public class ViewModelFactory {

    private ScreensNavigator screensNavigator;

    public ViewModelFactory(ScreensNavigator screensNavigator) {
        this.screensNavigator = screensNavigator;
    }

    public <T extends ViewModel> T get(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(RootLayoutViewModel.class)) {
            //noinspection unchecke
            return (T) new RootLayoutViewModel(screensNavigator);

        } else if (modelClass.isAssignableFrom(AddNewParticipantViewModel.class)) {
            //noinspection unchecke
            return (T) new AddNewParticipantViewModel(screensNavigator);
        } else
            return (T) new RootLayoutViewModel(screensNavigator);
    }
}
