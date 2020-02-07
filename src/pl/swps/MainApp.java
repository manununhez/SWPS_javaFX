package pl.swps;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import pl.swps.common.dependencyinjection.ApplicationCompositionRoot;
import pl.swps.view.RootLayout;

import java.io.IOException;

public class MainApp extends Application {
    private static final String VIEW_ROOT_LAYOUT_FXML = "view/RootLayout.fxml";
    private static final String PRIMARY_STAGE_TITLE = "SWSP University App";

    private Stage mPrimaryStage;
    private BorderPane rootLayout;

    //define your offsets here
    private double xOffset = 0;
    private double yOffset = 0;

    public static void main(String[] args) {
        launch(args);
    }

    private ApplicationCompositionRoot mApplicationCompositionRoot;

    @Override
    public void start(Stage primaryStage) {
        mPrimaryStage = primaryStage;
        mPrimaryStage.setTitle(PRIMARY_STAGE_TITLE);

        //you can use underdecorated or transparent.
        mPrimaryStage.initStyle(StageStyle.TRANSPARENT);

        initRootLayout();
    }

    /**
     * Initializes the root layout.
     */
    private void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource(VIEW_ROOT_LAYOUT_FXML));
            rootLayout = loader.load();

            mApplicationCompositionRoot = new ApplicationCompositionRoot(this, rootLayout);

            //grab your root here
            rootLayout.setOnMousePressed(event -> {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            });

            //move around here
            rootLayout.setOnMouseDragged(event -> {
                mPrimaryStage.setX(event.getScreenX() - xOffset);
                mPrimaryStage.setY(event.getScreenY() - yOffset);
            });

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            mPrimaryStage.setScene(scene);
            mPrimaryStage.show();

            //Give the controller access to the main app.
            RootLayout controller = loader.getController();
            controller.setMainApp(this);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ApplicationCompositionRoot getApplicationCompositionRoot() {
        return mApplicationCompositionRoot;
    }

    public Stage getPrimaryStage() {
        return mPrimaryStage;
    }


}
