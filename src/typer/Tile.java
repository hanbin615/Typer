/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package typer;

import java.util.Objects;
import javafx.animation.FadeTransition;
import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.media.AudioClip;
import javafx.scene.text.Font;
import javafx.util.Duration;

/**
 *
 * @author happy
 */
public class Tile extends Button{
    
    static private final AudioClip bar8Note = new AudioClip(Tile.class.getResource("Note8.wav").toString());
    static private StringProperty textProperty;
    static public void setTextField(StringProperty textfield){ textProperty = textfield;}
//    public EventHandler<ActionEvent> onFinished;
    private EventHandler<ActionEvent> start;
    private Transition transition;
    private FadeTransition fadeTransition;
    private String answer;
    
    static private Tile lastTile;
    static public Tile firstTile;
    
    /**
     * follow first and last tile if exist
     */
    public void followLastTile(){
        //TODO
        if(lastTile != null) {
            fadeTransition.setOnFinished(e->{lastTile.play();});
        } else {
            firstTile = this;
        }
        lastTile = this;
    }
    
    private BooleanProperty activeProperty = new SimpleBooleanProperty(false);
    
    private final EventHandler onHitEvent = e ->{
        activeProperty.set(false);
        textProperty.set("");
        
        transition.stop();
        playSound();
        fadeTransition.play();
        // TODO add onfinished?
    };
    
    static public void playSound(){
        bar8Note.play();
    }
    
    public Tile(String content){
        this(content, content);
    }
    
    private final BooleanProperty hit = new SimpleBooleanProperty(false);
    
    public Tile(String content, String answer){
        super(content);
        this.answer = answer;
        
        ChangeListener<String> listenKeyboard = (observable, oldValue, newValue) -> {
            if (newValue.toUpperCase().contains(answer.toUpperCase())){
                onHitEvent.handle(null);
            }
        };

        
        activeProperty.addListener((observable, oldvalue, newvalue)->{
            if (!Objects.equals(newvalue, oldvalue)){
                if(newvalue){
                    textProperty.addListener(listenKeyboard);
                    this.visibleProperty().set(true);
                    this.disableProperty().set(false);
                } else {
                    textProperty.removeListener(listenKeyboard);
                    this.visibleProperty().set(false);
                    this.disableProperty().set(true);
                }
            }
        });
        activeProperty.set(false);
        
        hit.addListener((observable, oldvalue, newvalue)->{
            if (!Objects.equals(newvalue, oldvalue)){
                if(newvalue){
                    onHitEvent.handle(null);
                }
            }
        });
        
        // set default move transition
        TranslateTransition moveTransition = new TranslateTransition(Duration.seconds(5),this);
        moveTransition.setToY(600);
        moveTransition.setCycleCount(1);
        
        transition = moveTransition;
        
        // set default fade transition
        fadeTransition = new FadeTransition(Duration.seconds(1), this);
        fadeTransition.setToValue(0);
        fadeTransition.setCycleCount(1);

        this.setFont(new Font(80));
    }
    
    static private String alfabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    
    static public double getX(String letter){
        switch(letter){
            case "Q": return 0;
            case "A": return 0;
            case "W": return 100;
            case "S": return 100;
            case "Z": return 100;
            case "E": return 200;
            case "D": return 200;
            case "X": return 200;
            case "R": return 300;
            case "F": return 300;
            case "C": return 300;
            case "T": return 400;
            case "G": return 400;
            case "V": return 400;
            case "Y": return 500;
            case "H": return 500;
            case "B": return 400;
            case "U": return 600;
            case "J": return 600;
            case "N": return 600;
            case "I": return 700;
            case "K": return 700;
            case "M": return 700;
            case "O": return 800;
            case "L": return 800;
            case "P": return 900;
            default : return 0;
        }
    }
    
    static public String getRandomLetter(){
        int index = (int) (Math.random() * 26);
        return alfabet.substring(index, index+1);
    }
    
    public void play(){
        activeProperty.set(true);
        transition.play();
    }
    
    
    public void setMoveTransition(TranslateTransition transition){
        this.transition = transition;
    }
    
}
