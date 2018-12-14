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
public abstract class AbstractTile extends Button implements ChangeListener<String>{
    
    static private final AudioClip bar8Note = new AudioClip(AbstractTile.class.getResource("Note8.wav").toString());
    static public StringProperty textfield;
    
    public SimpleBooleanProperty stopProperty = new SimpleBooleanProperty(false);
    private String answer;


    @Override
    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
        if (newValue.toUpperCase().contains(this.getText().toUpperCase())){
            interrupted();
            textfield.removeListener(this);
            textfield.set("");
        }
    }
    
    public void beforePlay(){};
    public abstract void play();
    public void afterPlay(){};
    public void interrupted(){};
    
    public void start(){
        beforePlay();
        play();
        afterPlay();
    }
    
    public long delay;
    public AbstractTile(String content, String answer){
        super(content);
        this.answer = answer;
        if (textfield != null){
            textfield.addListener(this);
        }
        
    }
    
    private boolean stop = false;
    public void setStop(boolean stop){
        this.stop = stop;
    }
}
