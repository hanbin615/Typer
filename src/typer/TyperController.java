/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package typer;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

/**
 *
 * @author happy
 */
public class TyperController implements Initializable {
    
//    static public TyperController singleinstance;
    
    @FXML public Pane pane;
    @FXML public TextField mytext;

    @FXML
    private void handleButtonRandom(ActionEvent event) {
        for(int i = 0; i <10 ; i++){
            String letter = Tile.getRandomLetter();
            Tile tile = new Tile(letter);
            tile.setLayoutX(Tile.getX(letter));

            TranslateTransition move = new TranslateTransition(Duration.seconds(5),tile);
            move.setCycleCount(1);
            move.setToY(pane.getHeight()-172);
            move.setDelay(Duration.seconds(i*1));
            tile.setMoveTransition(move);

            pane.getChildren().add(tile);
            tile.play();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        Platform.runLater(()->{
            Tile.setTextField(mytext.textProperty());
            
            // mytext always focused
            mytext.focusedProperty().addListener((observable, wasFocused, isFocused) -> {
                if (!isFocused) 
                    mytext.requestFocus();
            });
            
            // mytext always uppercase
            mytext.textProperty().addListener((ov, oldValue, newValue) -> {mytext.setText(newValue.toUpperCase()); });
            mytext.requestFocus();
        });
    }
    
}
