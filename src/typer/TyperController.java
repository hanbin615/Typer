/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package typer;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;

/**
 *
 * @author happy
 */
public class TyperController implements Initializable {
    
    @FXML public Pane pane;
    @FXML public TextField mytext;
    private TileFactory factory;

    @FXML
    private void handleButtonDefault(ActionEvent event) {
        File file = new File("./samples/letters.txt");
        if (!file.exists()){
            System.err.println("file " +file.getAbsolutePath() +" doesn't exist");
            return;
        }
        
        factory = new TileFactoryFromFile(pane, file);
        factory.sendTile();
    }

    @FXML
    private void handleButtonLoad(ActionEvent event) {
        FileChooser filechooser = new FileChooser();
        filechooser.setTitle("choose tile list");
        filechooser.setInitialDirectory(new File("./samples/"));
        File file = filechooser.showOpenDialog(pane.getScene().getWindow());
        if (file == null) return;
        
        factory = new TileFactoryFromFile(pane, file);
        factory.sendTile();
    }
    
    @FXML
    private void handleButtonRandom(ActionEvent event) {
        factory = new TileFactoryRandomLetter(pane);
        factory.sendTile();
    }
    
@Override
    public void initialize(URL url, ResourceBundle rb) {
        Platform.runLater(()->{
            mytext.textProperty().addListener((observable, oldvalue, newvalue)->{
                if (factory.check(newvalue))
                    mytext.setText("");
            });
            
            // mytext always focused
            mytext.focusedProperty().addListener((observable, wasFocused, isFocused) -> {
                if (!isFocused) 
                    mytext.requestFocus();
            });
            mytext.requestFocus();
        });
    }
    
    public TileFactory getFactory(){
        return factory;
    }
}
