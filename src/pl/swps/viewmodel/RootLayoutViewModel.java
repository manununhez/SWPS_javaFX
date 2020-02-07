package pl.swps.viewmodel;

import pl.swps.MainApp;
import pl.swps.common.ScreensNavigator;
import pl.swps.model.InstructionMessages;

public class RootLayoutViewModel extends ViewModel {
    private ScreensNavigator screensNavigator;

    public RootLayoutViewModel(ScreensNavigator screensNavigator) {

        this.screensNavigator = screensNavigator;
    }

    public void showHome() {
        screensNavigator.goHome();
    }

    public void showNewExperiment() {
        screensNavigator.goToNewExperiment();
    }

    public void showResults() {
        screensNavigator.goToResults();
    }

    public void showInstructions(InstructionMessages instructionMessages) {
        screensNavigator.goToInstructions(instructionMessages);
    }
}
