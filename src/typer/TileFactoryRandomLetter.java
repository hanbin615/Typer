/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package typer;

import javafx.scene.layout.Pane;

/**
 *
 * @author happy
 */
public class TileFactoryRandomLetter extends TileFactory{
        
    public TileFactoryRandomLetter(Pane pane){
        this.pane = pane;
    }
    
    @Override
    public Tile getTile(int index){
        String letter = getRandomLetter();
        Tile tile = new Tile(letter);
        tile.setLayoutX(getY(letter));
        return tile;
    }
    
    static public String getRandomLetter(){
        int index = (int) (Math.random() * 26);
        return alfabet.substring(index, index+1);
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
    
}
