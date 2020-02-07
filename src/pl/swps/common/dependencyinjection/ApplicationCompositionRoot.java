package pl.swps.common.dependencyinjection;

import javafx.scene.layout.BorderPane;
import pl.swps.MainApp;
import pl.swps.common.ScreensNavigator;
import pl.swps.model.InstructionMessages;
import pl.swps.view.ScreenController;
import pl.swps.viewmodel.ViewModelFactory;

public class ApplicationCompositionRoot {
    private MainApp applicationContext;
    private ScreenController screenController;


    public ApplicationCompositionRoot(MainApp applicationContext, BorderPane rootLayout) {
        this.applicationContext = applicationContext;
        screenController = getScreenController(rootLayout);
    }

    public ScreensNavigator getScreenNavigator() {
        return new ScreensNavigator(applicationContext, screenController);
    }

    private ScreenController getScreenController(BorderPane main){
        return new ScreenController(main);
    }

    public ViewModelFactory getViewModelFactory(){
        return new ViewModelFactory(getScreenNavigator());
    }

    public InstructionMessages getInstructionMessages(){
        return new InstructionMessages();
    }

}
