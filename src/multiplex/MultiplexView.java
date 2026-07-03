package multiplex;

import javafx.application.Application;
import javafx.stage.Stage;

public class MultiplexView extends Application {
    public static void main(String[] args) {
       launch(args);
    }
    @Override
    public void start(Stage primaryStage) {
        // Set stage Login Page as primaryStage
        MultiplexController.setStage(primaryStage);
        primaryStage.setTitle("Multiplex");
        MultiplexController.switchScene(ViewScenes.LOGIN);
    }
}
