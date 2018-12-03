/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package typer;

import java.util.Date;
import javafx.animation.KeyFrame;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.util.Duration;

/**
 *
 * @author happy
 */
public class Tile extends Button{
    
    public static SimpleStringProperty typed;
    private double moveY, moveX;    
    private String content;
    private KeyFrame keyframe;
    
    private ChangeListener<String> listener = (ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
        if (newValue.toUpperCase().contains(content.toUpperCase())){
            stop();
            typed.set("");
        }
    };
    
    public Tile(String content, int x, int y){
        this(content);
        this.setLayoutX(x);
        this.setLayoutY(y);
    }
    
    public Tile(String content){
        super(content);
        this.content = content;
        moveX = 0;
        moveY = 3;
        typed.addListener( listener);
    }
    
    private long countdown;
    
    public void move(){
        this.setLayoutX(Math.round(this.getLayoutX()+moveX));
        this.setLayoutY(Math.round(this.getLayoutY()+moveY));
    }
    
    /*
    
    */
    public void setDirection(double direction){
        setDirection(direction, 1);
    }
    
    public void setDirection(double direction, double magnitude){
        moveX = Math.cos(direction)*magnitude;
        moveY = Math.sin(direction)*magnitude;
    }
    
    @Override
    public String toString(){
        return "tile [" + content + "] is at x=" +this.getLayoutX() + ", y=" +this.getLayoutY()  ;
    }
    
    public EventHandler<ActionEvent> handle = e -> this.setLayoutY(this.getLayoutY()+2);
    public KeyFrame setKeyFrame(){
        keyframe = new KeyFrame(Duration.millis(50),handle);
        return keyframe;
    }
    
    public KeyFrame setKeyFrame(long millis){
        long endtime = new Date().getTime() + millis;
        EventHandler<ActionEvent> waithandle = e -> {
            if (endtime < new Date().getTime())
                this.setLayoutY(this.getLayoutY()+2);
        };
        keyframe = new KeyFrame(Duration.millis(50),waithandle);
        return keyframe;
    }
    
    public void stop(){
        // remove listener
        typed.removeListener(listener);
        
        // TODO stop moving
        
        // closing animation
        //TODO improve
        this.visibleProperty().set(false);
    }
}
