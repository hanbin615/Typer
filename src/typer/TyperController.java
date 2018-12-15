/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package typer;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
    
    public Tile getRandomLetterTile(){
        String letter = Tile.getRandomLetter();
        Tile tile = new Tile(letter);
        tile.setLayoutX(Tile.getX(letter));

        TranslateTransition move = new TranslateTransition(Duration.seconds(5),tile);
        move.setCycleCount(1);
        move.setToY(pane.getHeight()-172);
        tile.setMoveTransition(move);

        pane.getChildren().add(tile);
//        tile.visibleProperty().set(true);
//        tile.visibleProperty().set(false);
        return tile;
    }

    @FXML
    private void handleButtonRandom(ActionEvent event) {
        int number = 20;
        for(int i = 0; i <number ; i++){
            getRandomLetterTile().play();
        }
//            Tile.firstTile.play();
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
            
//            mytext.textProperty().addListener((ov, oldValue, newValue) -> {mytext.setText(newValue.toUpperCase()); });// mytext always uppercase
            mytext.requestFocus();
        });
    }
    
}
