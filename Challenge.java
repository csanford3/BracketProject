package application;

import org.omg.PortableServer.THREAD_POLICY_ID;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;


////////////////////ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
//Title:           Challenge.java
//Description:     This file contains the code for the Challenge class.  The Challenge class
//				   provides a way to store the graphical and team data associated with
//				   each game.
//
//Files:           Main.java, Team.java, Challenge.java
//
//Course:          CS 400, Spring 2017
//
//Authors:          Chance Sanford, Mitchell Saulsberry, Anubhav Sanjeeva Prasad.
//
//
//Email:           csanford4@wisc.edu, saulsberry@wisc.edu, sanjeevapras@wisc.edu
//
//
//Lecturer's Name: Debra Deppler
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
//NONE
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////


/*
 * Class which stores graphical data and team data associated with each game.
 */
public class Challenge {
	
	// First Team object
	private Team team1 = null;
	
	//Second Team object
	private Team team2 = null;
	
	//Team object to store winner of chalenge
	private Team winner = null;
	
	//Submit button for challenge
	Button submitBtn;
	
	//HBox which contains all graphical elements associated with challenge
	HBox challengeBox;
	
	//String field to determine which side of bracket this challenge object is on
	String side;
	
	//Field which enables propagation of winning team to next challenge 
	Challenge child = null;
	
	//Field which determines whether winning team will be sent to
	//the team1 or team2 spot of the next challenge
	boolean even;
	
	
	/**
	 * Constructor
	 * @param team1 
	 * @param team2
	 * @param side
	 * @param even
	 */
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
	/**
	 * private helper method to send winner of challenge to it's next challenge object
	 */
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
	/*
	 * method to initialize the challengeBox field with grpahical data
	 */
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
	
	/**
	 * method which computes the winner of this challenge object
	 * @return this.winner Returns this object's winner field
	 */
	public Team computeWinner() {
		
		winner = team1;
		if (team1.getScore() < team2.getScore())
			winner = team2;
		//returns winner
		return this.winner;
	}
	
	/**
	 * public getter method to return first team
	 * @return this.team1
	 */
	public Team getTeam1() {
		
		return this.team1;
	}
	
	/**
	 * public getter method to return second team
	 * @return this.team2
	 */
	public Team getTeam2() {
		
		return this.team2;
	}
	
	/**
	 * public setter method to set team1
	 * @param team1 Parameter which team1 field is set to
	 */
    public void setTeam1(Team team1) {
		
		this.team1 = team1;
	}
    
    /**
     * public setter method to set team2
     * @param team2 Parameter which team2 field is set to
     */
    public void setTeam2(Team team2) {
		
		this.team2 = team2;
	}

}