package multiplex;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MultiplexView extends Application {
    public static void main(String[] args) {
       launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("LoginPageScene.fxml"));
        Scene scene = new Scene(root);

        String css = this.getClass().getResource("css-files/loginPage.css").toExternalForm();
        scene.getStylesheets().add(css);
        
        primaryStage.setTitle("Multiplex");
        primaryStage.setScene(scene);
        
        primaryStage.show();
    }
}
