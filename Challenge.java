package application;

import org.omg.PortableServer.THREAD_POLICY_ID;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
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
	Challenge child = null;
	boolean even;
	
	
	public Challenge(Team team1, Team team2, String side, boolean even) {
		
		
		
		this.team1 = team1;
		this.team2 = team2;
		this.side = side;
		this.even = even;
		
		submitBtn = new Button();
		submitBtn.setText("Submit");
		createChallengeBox();
		submitBtn.setOnAction(new EventHandler<ActionEvent>() { 
			
			public void handle(ActionEvent event)  {
			    
				handleHelper();
				

			}
		});
	}
	
	private void handleHelper() { 
		
		team1.setScore();
		team2.setScore();
		int xcoord = GridPane.getColumnIndex(child.challengeBox);
		int ycoord = GridPane.getRowIndex(child.challengeBox);
		//child.challengeBox.getChildren().clear();
		Main.grid.getChildren().remove(child.challengeBox);
		if (even == true) { 
			child.setTeam1(new Team(computeWinner().getName()));
		}
		else { 
			child.setTeam2(new Team(computeWinner().getName()));
		}
		child.createChallengeBox();
		Main.grid.add(child.challengeBox, xcoord, ycoord);
	}
	
	private void createChallengeBox() { 
		
		VBox nameBox = new VBox();
		Text lineField = new Text(" ");
		HBox spaceSubmitBox = new HBox();
		VBox scoreSubmitBox = new VBox();
		
		
		nameBox.getChildren().addAll(team1.text, lineField, team2.text);
		
		
		challengeBox = new HBox();
		
		if (side.trim().toLowerCase().equals("left")) {
			spaceSubmitBox.getChildren().addAll(new Text("			"), submitBtn);
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