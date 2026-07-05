package multiplex.controllers;

import java.util.Objects;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import multiplex.ViewScenes;

public class MultiplexController {

    private static Stage stage;

    public static void setStage(Stage primaryStage) {
        stage = primaryStage;
    }

    public static void switchScene(ViewScenes view) {
        try {
            FXMLLoader loader = new FXMLLoader(MultiplexController.class.getResource(view.getFxmlFile()));

            Parent root = loader.load();
            Scene scene = new Scene(root);

            if (view.getCssFile() != null)
                scene.getStylesheets().add(Objects.requireNonNull(MultiplexController.class.getResource(view.getCssFile()).toExternalForm()));
            stage.setScene(scene);
            stage.show();
            
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Could not load " + view, e);
        }
    }
}
