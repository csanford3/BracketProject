package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class Challenge {
	
	private Team team1 = null;
	private Team team2 = null;
	private Team winner = null;
	Button submitBtn;
	HBox challengeBox;
	String side;
	
	
	public Challenge(Team team1, Team team2, String side) {
		
		
		
		this.team1 = team1;
		this.team2 = team2;
		this.side = side;
		//TODO:
		//***CHANGE THIS when a method to compute the winner is established***
			winner = this.team1;
		//***
		
		submitBtn = new Button();
		submitBtn.setText("Submit");
		/*submitBtn.setOnAction(new EventHandler<ActionEvent>() { 
			
			public void handle(ActionEvent event)  {
				System.out.println("TBD");
			}
		});*/
		
		VBox nameBox = new VBox();
		Text lineField = new Text(" ");
		HBox spaceSubmitBox = new HBox();
		VBox scoreSubmitBox = new VBox();
		
		
		nameBox.getChildren().addAll(team1.text, lineField, team2.text);
		
		
		challengeBox = new HBox();
		
		if (side.trim().toLowerCase().equals("left")) {
			spaceSubmitBox.getChildren().addAll(new Text("	"), submitBtn);
			scoreSubmitBox.getChildren().addAll(team1.textField, team2.textField, spaceSubmitBox);
			challengeBox.getChildren().addAll(nameBox, scoreSubmitBox);
		}
		
		else {
			challengeBox.getChildren().addAll(scoreSubmitBox, nameBox);
			scoreSubmitBox.getChildren().addAll(team1.textField, team2.textField, submitBtn);
		}
		
	}
	
	public Team computeWinner() {
		
		winner = team1;
		if (team1.getScore() < team2.getScore())
			winner = team2;
		//returns winner
		return this.winner;
	}
	
	public Team getTeam1() {
		
		return this.team1;
	}
	
	public Team getTeam2() {
		
		return this.team2;
	}
	
    public void setTeam1(Team team1) {
		
		this.team1 = team1;
	}
    
    public void setTeam2(Team team2) {
		
		this.team2 = team2;
	}

}
