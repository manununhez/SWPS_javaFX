package pl.swps.common.dependencyinjection;

import javafx.scene.layout.BorderPane;
import pl.swps.MainApp;
import pl.swps.data.repository.AppRepository;
import pl.swps.data.local.FileDataSource;
import pl.swps.common.ScreensNavigator;
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


    public ViewModelFactory getViewModelFactory(){
        return new ViewModelFactory(getScreenNavigator(), getRepository());
    }

    private ScreenController getScreenController(BorderPane main){
        return new ScreenController(main);
    }

    private AppRepository getRepository(){
        FileDataSource fileDataSource = FileDataSource.getInstance();
        return AppRepository.getInstance(fileDataSource);
    }

}
