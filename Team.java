package application;

import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class Team {
	
	private String name;
	private int score;
	TextField textField;
	Text text;
	
	public Team(String name, int score) {
		this.name = name;
		this.score = score;
		text = new Text(name);
		textField = new TextField();
		textField.setPrefColumnCount(10);
	}
	
	public String getString() {
		
		return this.name;
	}
	
	public int getScore() {
		
		return this.score;
	}
}
