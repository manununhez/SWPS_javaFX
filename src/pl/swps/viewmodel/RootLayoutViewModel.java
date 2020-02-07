package pl.swps.viewmodel;

import pl.swps.data.repository.AppRepository;
import pl.swps.common.ScreensNavigator;
import pl.swps.model.InstructionMessages;

public class RootLayoutViewModel extends ViewModel {
    private ScreensNavigator mScreensNavigator;
    private AppRepository mRepository;

    public RootLayoutViewModel(ScreensNavigator screensNavigator, AppRepository repository) {

        mScreensNavigator = screensNavigator;
        mRepository = repository;
    }

    public void showHome() {
        mScreensNavigator.goHome();
    }

    public void showNewExperiment() {
        mScreensNavigator.goToNewExperiment();
    }

    public void showResults() {
        mScreensNavigator.goToResults();
    }

    public void showInstructions(InstructionMessages instructionMessages) {
        mScreensNavigator.goToInstructions(instructionMessages);
    }

    public void loadData() {
        mRepository.loadWordsList();
        mRepository.loadParticipants();
    }

    public InstructionMessages getInstructionMessages() {
        return mRepository.getInstructions();
    }
}
