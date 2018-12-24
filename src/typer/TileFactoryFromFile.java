/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package typer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import javafx.scene.layout.Pane;

/**
 *
 * @author happy
 */
public class TileFactoryFromFile extends TileFactory{
    private final File file;
    
    public TileFactoryFromFile(Pane pane, File file){
        this.pane = pane;
        this.file = file;
        load(file);
    }
    
    @Override
    public Tile getTile(int index){
        Tile tile = new Tile(sequence.get(index));
        double range = pane.getWidth() - tile.getWidth();
        tile.setLayoutX(Math.random() * range);
        return tile;
    }
    
    public void load(File file){
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF-8"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                sequence.add(new TileData(line));
            }
        } catch(Exception e) {
            System.out.println("failed to load file " +file.getAbsolutePath());
        }
    }
    
    private final boolean append = false;
    public void save(){
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, append), "UTF-8"))) {
            for(TileData tiledata: sequence){
                writer.write(tiledata.save() +System.lineSeparator());
            }
            writer.flush();
            //Close writer
        } catch(Exception e){
            System.out.println("failed to save to file " +file.getAbsolutePath());
        }
    }
    
    @Override
    public void close(){
        save();
    }
}
