package pl.swps.viewmodel;

import pl.swps.data.repository.AppRepository;
import pl.swps.common.ScreensNavigator;
import pl.swps.model.InstructionMessages;

public class InstructionsViewModel extends ViewModel {
    private ScreensNavigator mScreensNavigator;
    private AppRepository mRepository;

    public InstructionsViewModel(ScreensNavigator screensNavigator, AppRepository repository) {
        mScreensNavigator = screensNavigator;
        mRepository = repository;
    }

    public InstructionMessages loadInstructions(boolean chooseSource) {
        return mRepository.loadInstructions(chooseSource);
    }
}
