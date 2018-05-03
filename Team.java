package application;

import com.sun.prism.paint.Color;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

////////////////////ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
//Title:           Team.java
//Description:     This file contains the code for the Team class.  The team class
//				   provides a way to store the graphical and game data associated with
//				   each team.
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


/**
 * Class which stores graphical data as well as the team name and score 
 * for each Team object.
 * 
 */
public class Team {
	
	//Team name
	private String name;
	
	//Score associated with the team
	private int score;
	
	//Text box for GUI
	TextField textField;
	
	//Text field for GUI which displays team name
	Text text;
	
	
	/**
	 * Constructor
	 * @param name Parameter to be assigned to name class field
	 */
	public Team(String name) {
		this.name = name;
		text = new Text(name);
		textField = new TextField();
		textField.setPrefColumnCount(10);
	
	}
	
	/**
	 * public getter method to return team name
	 * @return this.name
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * public setter method to set score
	 */
	public void setScore() {
		this.score = Integer.parseInt(textField.getText());
	}
	
	/**
	 * public getter method to retrieve score
	 * @return this.score
	 */
	public int getScore() {
		
		return this.score;
	}
}