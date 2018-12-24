/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package typer;

import java.util.ArrayList;
import java.util.Collections;
import javafx.scene.layout.Pane;

/**
 *
 * @author happy
 */
public abstract class TileFactory {
    protected Pane pane;
    protected final ArrayList<TileData> sequence = new ArrayList();
    protected int index = 0;
    
    abstract public Tile getTile(int index);

    public void sendTile(){
        Tile tile = getTile(index++);
        activeTiles.add(tile);
        tile.setFactory(this);
        pane.getChildren().add(tile);
        tile.play();
    };
    
    protected ArrayList<Tile> activeTiles = new ArrayList();
    
    public boolean check(String typed){
        boolean found = false;
        int countdown = activeTiles.size()-1;
        while (countdown >= 0){
            Tile tile = activeTiles.get(countdown);
            if(tile.match(typed)){
                found = true;
                activeTiles.remove(countdown);
            }
            countdown--;
        }
        return found;
    }

    public void shuffle(){
        Collections.shuffle(sequence);
    }
    
    public void close(){}
}
