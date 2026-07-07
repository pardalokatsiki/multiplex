package multiplex;

import javafx.application.Application;
import javafx.stage.Stage;
import multiplex.controllers.MultiplexController;

// Main class
public class MultiplexView extends Application {
    public static void main(String[] args) {
       launch(args);
    }
    @Override
    public void start(Stage primaryStage) {
        // Set stage Login Page as primaryStage
        MultiplexController.setStage(primaryStage);
        primaryStage.setTitle("Multiplex");
        //primaryStage.setResizable(false); remove the comment αν δεν λειτουργεί το fullscreen
        MultiplexController.switchScene(ViewScenes.LOGIN);
    }
}
