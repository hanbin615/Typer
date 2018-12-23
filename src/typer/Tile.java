/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package typer;

import java.util.ArrayList;
import javafx.animation.FadeTransition;
import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import javafx.scene.text.Font;
import javafx.util.Duration;

/**
 *
 * @author happy
 */
public class Tile extends Pane{
    
    static private final AudioClip bar8Note = new AudioClip(Tile.class.getResource("Note8.wav").toString());
    static private void playSound(){ bar8Note.play();}
    static private StringProperty textProperty;
    static private ArrayList<Tile> list = new ArrayList();
    
    private Boolean active = false;
    static public void setTextField(StringProperty textfield){ 
        textProperty = textfield;
        textProperty.addListener((observable, oldvalue, newvalue)->{
            int index = 0;
            while (index < list.size()){
                Tile tile = list.get(index);
                if(!tile.active) {
                    index++;
                    continue;
                }
                if (newvalue.toUpperCase().contains(tile.answer.toUpperCase())){
                    tile.onHitEvent.handle(null);
                    list.remove(index); // ALT set to inactive
                    tile.active = false;
                    textProperty.set("");
                    return;
                } else {
                    index++;
                }
            }
        });
    }
//    public EventHandler<ActionEvent> onFinished;
    private EventHandler<ActionEvent> start;
    private Transition transition;
    private FadeTransition fadeTransition;
    private String answer;
    
    static public Tile lastTile;
    static public Tile firstTile;
    
    /**
     * follow first and last tile if exist
     */
    static public void followLastTile(Tile tile){
        //TODO
        if(lastTile != null) {
            lastTile.fadeTransition.setOnFinished(e-> tile.play());
//            tile.fadeTransition.setOnFinished(e->lastTile.play());
//            System.out.println(this +" is following " +lastTile);
        } else {
            firstTile = tile;
//            System.out.println("firstTile: " +firstTile);
        }
        lastTile = tile;
//        System.out.println("lastTile: " +lastTile);
    }
    
    @Override
    public String toString(){
        return "Tile '" +this.answer +"'";
    }
    
    private final EventHandler onHitEvent = e ->{
        transition.stop();
        playSound();
        fadeTransition.play();
        // TODO add onfinished?
    };
    
    public Tile(String content){
        this(content, content);
    }
    
    private TileData tiledata;
    public Tile(TileData tiledata){
        this.tiledata = tiledata;
    }
    
    private final BooleanProperty hit = new SimpleBooleanProperty(false);
    
    public Tile(String content, String answer){
        super();
        Label label = new Label(content);
        label.setFont(new Font(72));
        getChildren().add(label);
        
        this.answer = answer;
        boolean add = list.add(this);
                
        // set default move transition
        TranslateTransition moveTransition = new TranslateTransition(Duration.seconds(5),this);
        moveTransition.setToY(600);
        moveTransition.setCycleCount(1);
        transition = moveTransition;
        
        // set default fade transition
        fadeTransition = new FadeTransition(Duration.seconds(1), this);
        fadeTransition.setToValue(0);
        fadeTransition.setCycleCount(1);
        
        this.setVisible(false);
    }
    
    public void play(){
        setVisible(true);
//        activeProperty.set(true);
        active = true;
        transition.play();
    }
    
    public void setMoveTransition(TranslateTransition transition){
        this.transition = transition;
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
    
    static public double getY(String letter){
        switch(letter){
            case "Q": return 100;
            case "W": return 100;
            case "E": return 100;
            case "R": return 100;
            case "T": return 100;
            case "Y": return 100;
            case "U": return 100;
            case "I": return 100;
            case "O": return 100;
            case "P": return 100;
            case "A": return 200;
            case "S": return 200;
            case "D": return 200;
            case "F": return 200;
            case "G": return 200;
            case "H": return 200;
            case "J": return 200;
            case "K": return 200;
            case "L": return 200;
            case "Z": return 300;
            case "X": return 300;
            case "C": return 300;
            case "V": return 300;
            case "B": return 300;
            case "N": return 300;
            case "M": return 300;
            default : return 0;
        }
    }
    
    static public String getRandomLetter(){
        int index = (int) (Math.random() * 26);
        return alfabet.substring(index, index+1);
    }
    
}
