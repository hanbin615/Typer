/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package typer;

import javafx.animation.FadeTransition;
import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
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
    
    private Transition transition;
    private FadeTransition fadeTransition;
    
    @Override
    public String toString(){
        return "Tile '" +tiledata.getAnswer() +"'";
    }
    
    public Tile(String content){
        this(content, content);
    }
    
    protected TileData tiledata;
    public Tile(TileData tiledata){
        this(tiledata.getContent(),tiledata.getAnswer());
        this.tiledata = tiledata;
        tiledata.addAppeared();
    }
    
    private final BooleanProperty hit = new SimpleBooleanProperty(false);
    
    public Tile(String content, String answer){
        super();
        Label label = new Label(content);
        label.setFont(new Font(72));
        getChildren().add(label);
//        this.setLayoutX(Math.random()*600);
                
        // set default move transition
        TranslateTransition moveTransition = new TranslateTransition(Duration.seconds(5),this);
        moveTransition.setToY(600);
        moveTransition.setCycleCount(1);
        transition = moveTransition;
        
        // set default fade transition
        fadeTransition = new FadeTransition(Duration.seconds(1), this);
        fadeTransition.setToValue(0);
        fadeTransition.setCycleCount(1);
    }
    
    public void play(){
        transition.play();
    }
    
    private TileFactory factory;
    public void setFactory(TileFactory factory){
        this.factory = factory;
    }
    
    public void onHit(){
        tiledata.addHit();
        transition.stop();
        playSound();
        fadeTransition.setOnFinished(e -> factory.sendTile());
        fadeTransition.play();
    }
    
    public void onMiss(){
        tiledata.addMiss();
        factory.sendTile();
    }
    
    public boolean match(String typed){
        if (typed.toUpperCase().contains(tiledata.getAnswer().toUpperCase())){
            // is hit
            onHit();
            return true;
        } else {
            return false;
        }
    }
}
