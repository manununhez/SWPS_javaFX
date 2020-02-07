package pl.swps.viewmodel;

import pl.swps.data.repository.AppRepository;
import pl.swps.common.ScreensNavigator;

public class ViewModelFactory {

    private ScreensNavigator mScreensNavigator;
    private AppRepository mRepository;

    public ViewModelFactory(ScreensNavigator screensNavigator, AppRepository repository) {
        mScreensNavigator = screensNavigator;
        mRepository = repository;
    }

    public <T extends ViewModel> T get(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(RootLayoutViewModel.class)) {
            //noinspection unchecke
            return (T) new RootLayoutViewModel(mScreensNavigator, mRepository);

        } else if (modelClass.isAssignableFrom(AddNewParticipantViewModel.class)) {
            //noinspection unchecke
            return (T) new AddNewParticipantViewModel(mScreensNavigator, mRepository);

        } else if (modelClass.isAssignableFrom(HomeViewModel.class)) {
            //noinspection unchecke
            return (T) new HomeViewModel(mScreensNavigator, mRepository);

        } else if (modelClass.isAssignableFrom(ResultsViewModel.class)) {
            //noinspection unchecke
            return (T) new ResultsViewModel(mScreensNavigator, mRepository);

        } else if (modelClass.isAssignableFrom(InstructionsViewModel.class)) {
            //noinspection unchecke
            return (T) new InstructionsViewModel(mScreensNavigator, mRepository);

        }else if (modelClass.isAssignableFrom(StartExperimentViewModel.class)) {
            //noinspection unchecke
            return (T) new StartExperimentViewModel(mScreensNavigator, mRepository);

        } else
            return (T) new RootLayoutViewModel(mScreensNavigator, mRepository);
    }
}
