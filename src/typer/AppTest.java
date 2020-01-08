package typer;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AppTest extends Application{

    @Override
    public void start(Stage primaryStage) {
        Scene scene = new Scene(new Keyboard(), 800, 600);
        
        primaryStage.setTitle("Keyboardtest");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }


}
