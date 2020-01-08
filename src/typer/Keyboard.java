package typer;

import java.util.HashMap;
import java.util.Map;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class Keyboard extends VBox {
	
	private Map<Character, Button> keys = new HashMap<Character, Button>();
	public Keyboard() {
		super();
		keys.put('A', new Button("A"));
		keys.put('B', new Button("B"));
		keys.put('C', new Button("C"));
		keys.put('D', new Button("D"));
		keys.put('E', new Button("E"));
		keys.put('F', new Button("F"));
		keys.put('G', new Button("G"));
		keys.put('H', new Button("H"));
		keys.put('I', new Button("I"));
		keys.put('J', new Button("J"));
		keys.put('K', new Button("K"));
		keys.put('L', new Button("L"));
		keys.put('M', new Button("M"));
		keys.put('N', new Button("N"));
		keys.put('O', new Button("O"));
		keys.put('P', new Button("P"));
		keys.put('Q', new Button("Q"));
		keys.put('R', new Button("R"));
		keys.put('S', new Button("S"));
		keys.put('T', new Button("T"));
		keys.put('U', new Button("U"));
		keys.put('V', new Button("V"));
		keys.put('W', new Button("W"));
		keys.put('X', new Button("X"));
		keys.put('Y', new Button("Y"));
		keys.put('Z', new Button("Z"));
		keys.put(' ', new Button("            "));
		keys.put('0', new Button("0"));
		keys.put('1', new Button("1"));
		keys.put('2', new Button("2"));
		keys.put('3', new Button("3"));
		keys.put('4', new Button("4"));
		keys.put('5', new Button("5"));
		keys.put('6', new Button("6"));
		keys.put('7', new Button("7"));
		keys.put('8', new Button("8"));
		keys.put('9', new Button("9"));
		HBox[] rows = new HBox[5];
		
		rows[0] = new HBox();
		rows[0].setPadding(new Insets(10,10,10,10));
		rows[0].getChildren().addAll(
				keys.get('1'),
				keys.get('2'),
				keys.get('3'),
				keys.get('4'),
				keys.get('5'),
				keys.get('6'),
				keys.get('7'),
				keys.get('8'),
				keys.get('9'),
				keys.get('0'));
		
		rows[1] = new HBox();
		rows[1].setPadding(new Insets(10,10,10,20));
		rows[1].getChildren().addAll(
				keys.get('Q'),
				keys.get('W'),
				keys.get('E'),
				keys.get('R'),
				keys.get('T'),
				keys.get('Y'),
				keys.get('U'),
				keys.get('I'),
				keys.get('O'),
				keys.get('P'));
		
		rows[2] = new HBox();
		rows[2].setPadding(new Insets(10,10,10,30));
		rows[2].getChildren().addAll(
				keys.get('A'),
				keys.get('S'),
				keys.get('D'),
				keys.get('F'),
				keys.get('G'),
				keys.get('H'),
				keys.get('J'),
				keys.get('K'),
				keys.get('L'));
		
		rows[3] = new HBox();
		rows[3].setPadding(new Insets(10,10,10,60));
		rows[3].getChildren().addAll(
				keys.get('Z'),
				keys.get('X'),
				keys.get('C'),
				keys.get('V'),
				keys.get('B'),
				keys.get('N'),
				keys.get('M'));
		
		rows[4] = new HBox();
		rows[4].setPadding(new Insets(10,10,10,200));
		rows[4].getChildren().addAll(keys.get(' '));
		
		this.getChildren().addAll(rows);
		
		this.setOnKeyPressed((KeyEvent event)->{
			keyPress(event.getCharacter().toUpperCase().charAt(0));
			event.consume();
		});
		
		this.setOnKeyReleased((KeyEvent event)->{
			keyRelease(event.getCharacter().toUpperCase().charAt(0));
			event.consume();
		});
	}
	
	private Character[] hightlightedkeys;
	public void highlight(Character... key) {
		for (Character k: hightlightedkeys ) {
			keys.get(k).setStyle("-fx-background-color: white");
		}
		
		hightlightedkeys = key;
		for (Character k: hightlightedkeys ) {
			keys.get(k).setStyle("-fx-background-color: yellow");
		}
	}
	
	private Map<Character, String> pressed = new HashMap<Character, String>();
	public void keyPress(Character key) {
		if(keys.containsKey(key)) {
			pressed.put(key, keys.get(key).getStyle());
			keys.get(key).setStyle("-fx-background-color: blue");
		} else {
			System.out.println("the key '" +key +"' is not suported" );
		}
		
	}
	
	public void keyRelease(Character key) {
		if(keys.containsKey(key)) {
			keys.get(key).setStyle(pressed.remove(key));
		} else {
			System.out.println("the key '" +key +"' is not suported" );
		}
	}
	
	/**
	 * convert KeyCode to Character.
	 * 
	 * @param keycode only accept A-Z, 0-9 and space, otherwise return null
	 * @return 
	 */
	public Character keyCodeToChar(KeyCode keycode) {
		switch(keycode) {
		case A: return 'A';
		case B: return 'B';
		case C: return 'C';
		case D: return 'D';
		case E: return 'E';
		case F: return 'F';
		case G: return 'G';
		case H: return 'H';
		case I: return 'I';
		case J: return 'J';
		case K: return 'K';
		case L: return 'L';
		case M: return 'M';
		case N: return 'N';
		case O: return 'O';
		case P: return 'P';
		case Q: return 'Q';
		case R: return 'R';
		case S: return 'S';
		case T: return 'T';
		case U: return 'U';
		case V: return 'V';
		case W: return 'W';
		case X: return 'X';
		case Y: return 'Y';
		case Z: return 'Z';
		case SPACE: return ' ';
		case DIGIT0: return '0';
		case DIGIT1: return '1';
		case DIGIT2: return '2';
		case DIGIT3: return '3';
		case DIGIT4: return '4';
		case DIGIT5: return '5';
		case DIGIT6: return '6';
		case DIGIT7: return '7';
		case DIGIT8: return '8';
		case DIGIT9: return '9';
		default: return null;
		}
	}

}
