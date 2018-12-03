/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package typer;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

/**
 *
 * @author happy
 */
public class TyperController implements Initializable {
    
//    @FXML private BorderPane borderPane;
    @FXML public StackPane stackPane;
    @FXML public TextField mytext;
    
    private ChangeListener<Boolean> focusLossListener = (observable, wasFocused, isFocused) -> {
        if (!isFocused) 
            mytext.requestFocus();
    };
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        mytext.focusedProperty().addListener(focusLossListener);
        mytext.requestFocus();
        mytext.textProperty().addListener((ov, oldValue, newValue) -> {mytext.setText(newValue.toUpperCase()); });    
    }
    
}
