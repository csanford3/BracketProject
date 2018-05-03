package application;

import com.sun.prism.paint.Color;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class Team {
	
	private String name;
	private int score;
	TextField textField;
	Text text;
	Button btn;
	
	public Team(String name) {
		this.name = name;
		text = new Text(name);
		textField = new TextField();
		textField.setPrefColumnCount(10);
		this.btn = new Button();
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setScore() {
		this.score = Integer.parseInt(textField.getText());
	}
	
	public int getScore() {
		
		return this.score;
	}
}
