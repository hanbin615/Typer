/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package typer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author happy
 */
public class Typer extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Typer.fxml"));
	Parent root = (Parent) loader.load();
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
        
        TyperController controller = (TyperController) loader.getController();
        stage.setOnCloseRequest(e -> controller.getFactory().close());
//        stage.setOnHiding(e -> System.out.println("calling on hiding"));
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void stop(){
        System.out.println("calling stop function");
    }
    
}
