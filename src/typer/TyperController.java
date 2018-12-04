/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package typer;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

/**
 *
 * @author happy
 */
public class TyperController implements Initializable {
    
//    static public TyperController singleinstance;
    
    @FXML public Pane pane;
    @FXML public TextField mytext;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        singleinstance = this;
        
        // mytext always focused
        ChangeListener<Boolean> focusLossListener = (observable, wasFocused, isFocused) -> {
            if (!isFocused) 
                mytext.requestFocus();
        };
        mytext.focusedProperty().addListener(focusLossListener);
        
        // mytext always uppercase
        mytext.textProperty().addListener((ov, oldValue, newValue) -> {mytext.setText(newValue.toUpperCase()); });
        
        // start
        Sequence sequence = new Sequence(pane, mytext.textProperty());
        sequence.start();
        mytext.requestFocus();
    }
    
}
