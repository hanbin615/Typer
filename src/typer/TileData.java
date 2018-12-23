/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package typer;

/**
 *
 * @author happy
 */
public class TileData {
    public enum TileType {TEXT, IMAGE}
    private String content;
    private String answer;
    private int appeared, hit, miss;
    private boolean changed = false;
    
    public TileData(String line){
        String[] content = line.split(",");
        switch(content.length){
            default: System.out.println("Tiledata Error: too much content: " +line);
            case 5: this.miss = Integer.getInteger(content[4]);
            case 4: this.hit = Integer.getInteger(content[3]);
            case 3: this.appeared = Integer.getInteger(content[2]);
            case 2: this.answer = content[1];
            case 1: this.content = content[0]; break;
            case 0: System.out.println("Tiledata Error: no content"); break;
        }
    }
    
    @Override
    public String toString(){
        return content + "," + answer +"," +appeared +"," + hit +"," + miss;
    }
    
    public String save(){
        changed = false;
        return toString();
    }

    public boolean isChanged() {
        return changed;
    }
    
    public void addAppeared(){
        appeared++;
        changed = true;
    }
    
    public void addMiss(){
        miss++;
        changed = true;
    }
    
    public void addHit(){
        hit++;
        changed = true;
    }
    
}
