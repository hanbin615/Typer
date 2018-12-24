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
        String[] data = line.split(",");
        
        if(data.length >= 1){
            content = data[0];
        } else {
            content = " ";
        }
        
        if (data.length >= 2){
            answer = data[1];
        } else {
            answer = content;
        }
        
        if (data.length >= 3) {
            appeared = Integer.getInteger(data[2]);
        } else {
            appeared = 0;
        }
        
        if (data.length >= 4){
            hit = Integer.getInteger(data[3]);
        } else {
            hit = 0;
        }
        
        if (data.length >= 5) miss = Integer.getInteger(data[4]);
        else miss = 0;

            
        if (data.length >= 6){
            System.out.println(content +"'s excess data will be ignored");
        }
        
    }

    public String getContent() {
        return content;
    }

    public String getAnswer() {
        return answer;
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
