package pl.swps.viewmodel;

import pl.swps.data.repository.AppRepository;
import pl.swps.common.ScreensNavigator;
import pl.swps.model.InstructionMessages;

public class StartExperimentViewModel extends ViewModel {
    private ScreensNavigator mScreensNavigator;
    private AppRepository mRepository;

    public StartExperimentViewModel(ScreensNavigator screensNavigator, AppRepository repository) {

        mScreensNavigator = screensNavigator;
        mRepository = repository;
    }

    public InstructionMessages getInstructions() {
        return mRepository.getInstructions();
    }
}
