package pl.swps.viewmodel;

import pl.swps.common.ScreensNavigator;
import pl.swps.model.Participant;
import pl.swps.model.StyleDesign;

public class AddNewParticipantViewModel extends ViewModel{
    private ScreensNavigator screensNavigator;

    public AddNewParticipantViewModel(ScreensNavigator screensNavigator) {

        this.screensNavigator = screensNavigator;
    }

    public void showStartExperiment(Participant participant, StyleDesign selectedStyleDesign) {
        screensNavigator.goToStartExperiment(selectedStyleDesign, participant);
    }

}
