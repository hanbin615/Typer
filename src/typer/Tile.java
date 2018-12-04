/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package typer;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.media.AudioClip;
import javafx.util.Duration;

/**
 *
 * @author happy
 */
public class Tile extends Button implements ChangeListener<String>{
    
    static private final AudioClip bar8Note = new AudioClip(Tile.class.getResource("Note8.wav").toString());
    public SimpleBooleanProperty stopProperty = new SimpleBooleanProperty(false);
    public StringProperty text;


    @Override
    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
        if (newValue.toUpperCase().contains(this.getText().toUpperCase())){
            stop();
            text.removeListener(this);
            text.set("");
            
        }
    }
    
    private TranslateTransition leftTransition;
    private FadeTransition fadeTransition;
    public EventHandler onHit = e ->{
        stop();
    };
    
    public long delay;
    public Tile(String content, StringProperty text, long delay){
        super(content);
        this.text = text;
        this.delay = delay;
    }
    
    public Tile(String content, StringProperty text){
        this(content, text, 0);
    }
    
    public void run(){
        leftTransition = new TranslateTransition(Duration.seconds(5), this);
        leftTransition.setToX(-1 * this.getLayoutX());
        leftTransition.setCycleCount(1);
        leftTransition.setDelay(Duration.millis(delay));
        leftTransition.play();
        
        fadeTransition = new FadeTransition(Duration.seconds(1), this);
        fadeTransition.setToValue(0);
        fadeTransition.setCycleCount(1);
        
        text.addListener(this);
        
    }
    
    public void stop(){
        leftTransition.stop();
        bar8Note.play();
        fadeTransition.play();
        stopProperty.set(true);
        this.disableProperty().set(true);
    }
}
