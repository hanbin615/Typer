/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package typer;

import java.util.ArrayList;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

/**
 *
 * @author happy
 */
public class Sequence {
    public ArrayList<AbstractTile> list = new ArrayList();
    public Pane pane;
    public StringProperty text;
    
    public Sequence(Pane parent, StringProperty text){
        super();
        pane = parent;
        this.text = text;
    }
    
    public int index = 0;
    public void next(){
        if (index < list.size()){
            AbstractTile t = list.get(index++);
//            System.out.println("placing tile " +t.getText() +" at x= " +pane.getWidth() +", y= " +pane.getHeight());
            t.setLayoutX(pane.getWidth());
            t.setLayoutY((pane.getHeight()+t.heightProperty().get())/2);
            pane.getChildren().add(t);
            t.stopProperty.addListener( e ->  next()); //TO IMPROVE may cause bugs in future
            t.run();
        }
        else {
            System.out.println("game over");
        }
    }
    
    public void load(){
        // TODO
        list.add(new AbstractTile("start", text));// TODO debug, why it start at x=0 y=0?
        list.add(new AbstractTile("my", text, 2000));
        list.add(new AbstractTile("name", text));
        list.add(new AbstractTile("is", text));
        list.add(new AbstractTile("max", text));
    }
    
    public void start(){
        if (pane != null){
            pane.getChildren().add(new AbstractTile("hello world", text));
            load();
            next();
        }
    }
}
