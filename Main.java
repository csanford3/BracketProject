package application;
import java.util.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

////////////////////ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
//Title:           Main.java
//Description:     This file provides the driver code for the tournament
//				   bracket program with GUI.  This application allows
//				   the user to input 2-16 teams and track each teams' 
//				   progress throughout the tournament by updating a 
//				   team's location in the bracket as the user enters
//				   scores for the games.
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

public class Main extends Application {
	
	public static GridPane grid = new GridPane();

	private ArrayList<Integer> initialSeeding(int numRounds) {
		
		ArrayList<Integer> indexArray = new ArrayList<Integer>();
		indexArray.add(1);
		indexArray.add(2);
		for(int k = 0; k < numRounds - 1; k ++) {
			ArrayList<Integer> out = new ArrayList<Integer>();
			int length = indexArray.size()*2 +1;
			for(Integer d: indexArray) {
				out.add(d);
				out.add(length - d);
			}
			
			indexArray = out;
		}
		
		return indexArray;
		
	}
	
	@Override
	public void start(Stage primaryStage) {
		
		ArrayList<String> nameArray = new ArrayList<String>();
		
		Parameters param = getParameters();
		List<String> list = param.getRaw();
		
		String filePath = list.get(0).trim();
		
		Boolean empty = true;
		
		File inFile = new File( "src"+File.separator + filePath);
		
		if (inFile.length() != 0) {
			
			empty = false;
		
		
			Scanner input = null;
			try {
				//use Scanner to read from the file, may throw FileNotFoundException
				input = new Scanner( inFile);



				BufferedReader br = new BufferedReader(new FileReader(inFile));

				//while there are lines  in the file.
				String s;
				while ((s = br.readLine()) != null && !s.trim().equals("")) {
					nameArray.add(s);



				}



			}


			catch (FileNotFoundException e) {
				System.err.println("Error: File not found");
				//		e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 

			finally {
				//make sure and close the Scanner when done.
				if ( input != null) input.close();
			}
		
		}
		
		try {
			
			
			primaryStage.setTitle("Bracket Application");
			
			
			
		
			FlowPane flow = new FlowPane();
			flow.setPadding(new Insets(10, 10, 10, 10));
			flow.setStyle("-fx-background-color: AAE6F3;");
			flow.setHgap(5);
			
			
			//Gridpane shich allows flexibility
			
			//Padding sets space around edge of window
			grid.setPadding(new Insets(10, 10, 10, 10));
			
			//Alignment of window set to center
			grid.setAlignment(Pos.CENTER);
			grid.setMinSize(500, 500);
			grid.setHgap(20);
			grid.setVgap(20);
			//grid.setGridLinesVisible(true); // for debugging purposes
			
			//need to delete this
			Button submitBtn = new Button(); 
			submitBtn.setText("Submit");
			submitBtn.setOnAction(new EventHandler<ActionEvent>() { 
				
				public void handle(ActionEvent event)  {
					System.out.println("TBD");
				}
			});
			
			int numTeams = nameArray.size();
		
			if (numTeams == 1) {
				
				Text champText = new Text();
				champText.setText("Champion: " + nameArray.get(0));
				grid.add(champText, 0, 0);
			}
			
			System.out.println(empty);
			
			if (numTeams > 1) {
			
				
				
				//numRounds is intended to keep track of which layer(round) of the bracket we are working with
				int numRounds = 0;
				
				//gets the number of rounds(aka the N in 2^N)
				numRounds = (int)(Math.log((double)numTeams)/Math.log(2));
				
				//populate initial array of Team Objects -> will populate challenge array after
				Team[] teamArray = new Team[numTeams] ;
				int i = 0;
				for (String s : nameArray) { 
					teamArray[i] = new Team(s);
					i ++;
				}
				 
				Challenge[] challengeArray = new Challenge[numTeams/2];
				ArrayList<Integer> indexArray = initialSeeding(numRounds);
				
				//populates the outer instance of the bracket
				//only need to iterate until numTeams/2 because each lower seed(ie 1-8) is matched up with higher seed.
				int p = 0; //for iterating through indexArray
				for (int k = 0; k < numTeams/2; k ++) { 
					if (k < numTeams/4) 
					    challengeArray[k] = new Challenge(teamArray[indexArray.get(p)-1], teamArray[indexArray.get(p+1)-1], "left", k % 2 == 0);
					else
						challengeArray[k] = new Challenge(teamArray[indexArray.get(p)-1], teamArray[indexArray.get(p+1)-1], "right", k % 2 == 0);
					p+= 2;
					
				/*	//teams cut in half when advancing a round
					numRounds --;
					numTeams = numTeams/2; */
				}
				
				if (numTeams == 2) {

					Button submitBtn1 = new Button(); 
					submitBtn1.setText("Submit");

					//winner of left side
					Team teamX = new Team(challengeArray[0].getTeam1().getName());

					VBox leftWinner = new VBox();
					leftWinner.getChildren().addAll(teamX.text, teamX.textField);

					//winner of right side 
					Team teamY = new Team(challengeArray[0].getTeam2().getName());

					VBox rightWinner = new VBox();
					rightWinner.getChildren().addAll(teamY.text, teamY.textField);


					//middle championship box
					VBox submitBtnBox = new VBox();
					submitBtnBox.getChildren().addAll(new Text(""), submitBtn1);
					HBox championship = new HBox(); 
					championship.getChildren().addAll(leftWinner, submitBtnBox, rightWinner);
					grid.add(championship, 2, 1);

					//champion and runner up areas - mostly lines 
					VBox championArea = new VBox();
					Line hLine = new Line();
					Line vLine = new Line();
					Text championText = new Text();
					championText.setText("Champion: Team Name");


					hLine.setStartX(0.0f);
					hLine.setStartY(160.0f);
					hLine.setEndX(160.0f);
					hLine.setEndY(160.00f);

					vLine.setStartX(0.0f);
					vLine.setStartY(0.0f);
					vLine.setEndX(0.0);
					vLine.setEndY(30.00f);

					championArea.getChildren().addAll(championText, hLine, vLine);
					championArea.setAlignment(Pos.BOTTOM_CENTER);
					grid.add(championArea, 2, 0);

					VBox secondArea = new VBox();
					Line hLine2 = new Line();
					Line vLine2 = new Line();
					Text secondText = new Text();
					secondText.setText("Runner-Up: Team Name");


					hLine2.setStartX(0.0f);
					hLine2.setStartY(160.0f);
					hLine2.setEndX(160.0f);
					hLine2.setEndY(160.00f);

					vLine2.setStartX(0.0f);
					vLine2.setStartY(0.0f);
					vLine2.setEndX(0.0);
					vLine2.setEndY(30.00f);

					secondArea.getChildren().addAll(vLine2, hLine2, secondText);
					secondArea.setAlignment(Pos.TOP_CENTER);
					grid.add(secondArea, 2, 2);

					submitBtn1.setOnAction(new EventHandler<ActionEvent>() { 

						public void handle(ActionEvent event)  {
							if (challengeArray[0].getTeam1().getScore() > challengeArray[0].getTeam2().getScore()) { 
								championText.setText("Champion: " + challengeArray[0].getTeam1().getName());
								secondText.setText("Runner Up: " + challengeArray[0].getTeam2().getName());
							}
							else { 
								championText.setText("Champion: " + challengeArray[0].getTeam2().getName());
								secondText.setText("Runner Up: " + challengeArray[0].getTeam1().getName());
							}
						}
					});
				}


				else if (numTeams == 4) { 

					Button submitBtn2 = new Button(); 
					submitBtn2.setText("Submit");

					Button finalBtn = new Button(); 
					finalBtn.setText("Load Final Matchup!");


					grid.add(challengeArray[0].challengeBox, 0, 1);
					grid.add(challengeArray[1].challengeBox, 2, 1);

					//winner of left side
					Team teamX = new Team("");

					VBox leftWinner = new VBox();
					leftWinner.getChildren().addAll(teamX.text, teamX.textField);

					//winner of right side 
					Team teamY = new Team("");

					VBox rightWinner = new VBox();
					rightWinner.getChildren().addAll(teamY.text, teamY.textField);


					//middle championship box
					VBox submitBtnBox = new VBox();
					submitBtnBox.getChildren().addAll(new Text(""), submitBtn2, finalBtn);
					HBox championship = new HBox(); 
					championship.getChildren().addAll(leftWinner, submitBtnBox, rightWinner);
					grid.add(championship, 1, 1);

					//champion and runner up areas - mostly lines 
					VBox championArea = new VBox();
					Line hLine = new Line();
					Line vLine = new Line();
					Text championText = new Text();
					championText.setText("Champion: Team Name");


					hLine.setStartX(0.0f);
					hLine.setStartY(160.0f);
					hLine.setEndX(160.0f);
					hLine.setEndY(160.00f);

					vLine.setStartX(0.0f);
					vLine.setStartY(0.0f);
					vLine.setEndX(0.0);
					vLine.setEndY(30.00f);

					championArea.getChildren().addAll(championText, hLine, vLine);
					championArea.setAlignment(Pos.BOTTOM_CENTER);
					grid.add(championArea, 1, 0);

					VBox secondArea = new VBox();
					Line hLine2 = new Line();
					Line vLine2 = new Line();
					Text secondText = new Text();
					secondText.setText("Runner-Up: Team Name");


					hLine2.setStartX(0.0f);
					hLine2.setStartY(160.0f);
					hLine2.setEndX(160.0f);
					hLine2.setEndY(160.00f);

					vLine2.setStartX(0.0f);
					vLine2.setStartY(0.0f);
					vLine2.setEndX(0.0);
					vLine2.setEndY(30.00f);

					secondArea.getChildren().addAll(vLine2, hLine2, secondText);
					secondArea.setAlignment(Pos.TOP_CENTER);
					grid.add(secondArea, 1, 2);

					finalBtn.setOnAction(new EventHandler<ActionEvent>() { 

						public void handle(ActionEvent event) {
							grid.getChildren().remove(championship);
							Team teamX = new Team(challengeArray[0].computeWinner().getName());

							VBox leftWinner = new VBox();
							leftWinner.getChildren().addAll(teamX.text, teamX.textField);

							//winner of right side 
							Team teamY = new Team(challengeArray[1].computeWinner().getName());

							VBox rightWinner = new VBox();
							rightWinner.getChildren().addAll(teamY.text, teamY.textField);


							//middle championship box
							VBox submitBtnBox = new VBox();
							submitBtnBox.getChildren().addAll(new Text(""), submitBtn2);
							HBox championship = new HBox(); 
							championship.getChildren().addAll(leftWinner, submitBtnBox, rightWinner);
							grid.add(championship, 1, 1);
							challengeArray[0].setTeam1(teamX);
							challengeArray[0].setTeam2(teamY);
						}
					});


					submitBtn2.setOnAction(new EventHandler<ActionEvent>() { 

						public void handle(ActionEvent event)  {
							if (challengeArray[0].getTeam1().getScore() > challengeArray[0].getTeam2().getScore()) { 
								championText.setText("Champion: " + challengeArray[0].getTeam1().getName());
								secondText.setText("Runner Up: " + challengeArray[0].getTeam2().getName());
							}
							else { 
								championText.setText("Champion: " + challengeArray[0].getTeam2().getName());
								secondText.setText("Runner Up: " + challengeArray[0].getTeam1().getName());
							}
						}
					});	


				}


				else {
					Button submitBtn3 = new Button(); 
					submitBtn3.setText("Submit");

					Button finalBtn2 = new Button(); 
					finalBtn2.setText("Load Final Matchup!");


					ArrayList<ArrayList<Challenge>> innerChallengesLeft = new ArrayList<ArrayList<Challenge>>();

					//Left Hand Side of Grid
					Challenge[] leftEmpty = new Challenge[9];

					for (int g = 0; g < 9; g++) {
						if (g % 2 == 0) { 
							leftEmpty[g] = new Challenge(new Team(""), new Team(""), "left", true);
						}
						else { 
							leftEmpty[g] = new Challenge(new Team(""), new Team(""), "left", false);
						}

					}



					for(int j = 0; j < numTeams/4; j++) {
						grid.add(challengeArray[j].challengeBox, 0, 2*j);		
					}
					int inc = 0;
					for (int j = 0; j < numTeams/8; j++) {

						ArrayList<Challenge> inner = new ArrayList<Challenge>();
						for(int k = 0; k < numTeams/8-j; k++) {
							grid.add(leftEmpty[inc].challengeBox, j+1, 4*k + (2*j+1));
							inner.add(leftEmpty[inc]);
							inc++;
						}
						innerChallengesLeft.add(inner);

					}

					int innerCounter = 0;
					for (int k = 0; k < challengeArray.length/2; k+=2) { 
						challengeArray[k].child = innerChallengesLeft.get(0).get(innerCounter);
						challengeArray[k + 1].child = innerChallengesLeft.get(0).get(innerCounter);
						innerCounter += 1;
					}


					for (int k = 0; k < innerChallengesLeft.size(); k++) { 
						innerCounter = 0;
						for(int j = 0; j < innerChallengesLeft.get(k).size() - 1; j+=2) { 
							innerChallengesLeft.get(k).get(j).child = innerChallengesLeft.get(j+1).get(innerCounter);
							innerChallengesLeft.get(k).get(j+1).child = innerChallengesLeft.get(j+1).get(innerCounter);
							innerCounter += 1;
						}
					}




					ArrayList<ArrayList<Challenge>> innerChallengesRight = new ArrayList<ArrayList<Challenge>>();

					Challenge[] rightEmpty = new Challenge[9];

					for (int g = 0; g < 9; g++) {
						if (g % 2 == 0) { 
							rightEmpty[g] = new Challenge(new Team(""), new Team(""), "right", false);
						}
						else { 
							rightEmpty[g] = new Challenge(new Team(""), new Team(""), "right", true);
						}

					}


					//Right Hand Side of Grid
					int z = 2; 
					if (numTeams == 8) { 
						z = 0;
					}

					for(int j = numTeams/4; j < numTeams/2; j++) {
						grid.add(challengeArray[j].challengeBox, numTeams/2 - z, 2*j-2*numTeams/4);		
					}
					int inc2 = 0;
					for (int j = 0; j < numTeams/8; j++) {

						ArrayList<Challenge> inner = new ArrayList<Challenge>();
						for(int k = 0; k < numTeams/8-j; k++) {
							System.out.println("(" +  (numTeams/4+1-j) + "," +  (4*k + (2*j+1)) + ")");
							grid.add(rightEmpty[numTeams/8 + inc2 + 1].challengeBox, numTeams/4+1-j, 4*k + (2*j+1));
							inner.add(rightEmpty[numTeams/8 + inc2 + 1]);
							inc2++;
						}
						innerChallengesRight.add(inner);

					}

					innerCounter = 0;
					for (int k = challengeArray.length/2; k < challengeArray.length; k+=2) { 
						challengeArray[k].child = innerChallengesRight.get(0).get(innerCounter);
						challengeArray[k + 1].child = innerChallengesRight.get(0).get(innerCounter);
						innerCounter += 1;
					}

					for (int k = 0; k < innerChallengesRight.size(); k ++) { 
						innerCounter = 0;
						for(int j = 0; j < innerChallengesRight.get(k).size() - 1; j+=2) { 
							innerChallengesRight.get(k).get(j).child = innerChallengesRight.get(j+1).get(innerCounter);
							innerChallengesRight.get(k).get(j+1).child = innerChallengesRight.get(j+1).get(innerCounter);
							innerCounter += 1;
						}
					}

					//winner of left side
					Team teamX = new Team("");

					VBox leftWinner = new VBox();
					leftWinner.getChildren().addAll(teamX.text, teamX.textField);

					//winner of right side 
					Team teamY = new Team("");

					VBox rightWinner = new VBox();
					rightWinner.getChildren().addAll(teamY.text, teamY.textField);


					//middle championship box
					VBox submitBtnBox = new VBox();
					submitBtnBox.getChildren().addAll(new Text(""), submitBtn3, finalBtn2);
					HBox championship = new HBox(); 
					championship.getChildren().addAll(leftWinner, submitBtnBox, rightWinner);


					//champion and runner up areas - mostly lines 
					VBox championArea = new VBox();
					Line hLine = new Line();
					Line vLine = new Line();
					Text championText = new Text();
					championText.setText("Champion: Team Name");


					hLine.setStartX(0.0f);
					hLine.setStartY(160.0f);
					hLine.setEndX(160.0f);
					hLine.setEndY(160.00f);

					vLine.setStartX(0.0f);
					vLine.setStartY(0.0f);
					vLine.setEndX(0.0);
					vLine.setEndY(30.00f);

					championArea.getChildren().addAll(championText, hLine, vLine);
					championArea.setAlignment(Pos.BOTTOM_CENTER);


					VBox secondArea = new VBox();
					Line hLine2 = new Line();
					Line vLine2 = new Line();
					Text secondText = new Text();
					secondText.setText("Runner-Up: Team Name");


					hLine2.setStartX(0.0f);
					hLine2.setStartY(160.0f);
					hLine2.setEndX(160.0f);
					hLine2.setEndY(160.00f);

					vLine2.setStartX(0.0f);
					vLine2.setStartY(0.0f);
					vLine2.setEndX(0.0);
					vLine2.setEndY(30.00f);

					secondArea.getChildren().addAll(vLine2, hLine2, secondText);
					secondArea.setAlignment(Pos.TOP_CENTER);


					VBox centerBox = new VBox();
					centerBox.getChildren().addAll(championship);

					int w = 0; 
					if(numTeams == 8) { 
						w = 1;
					}


					grid.add(centerBox, numTeams/4 - 1 + w, numTeams/4 -1);
					grid.add(championArea, numTeams/4 - 1 + w, numTeams/4 - 2);
					grid.add(secondArea, numTeams/4 - 1 + w, numTeams/4);


					finalBtn2.setOnAction(new EventHandler<ActionEvent>() { 

						public void handle(ActionEvent event) {
							int w = 0; 
							if(numTeams == 8) { 
								w = 1;
							}
							grid.getChildren().remove(championship);
							Team teamX = new Team(innerChallengesLeft.get(innerChallengesLeft.size() - 1).get(0).computeWinner().getName());

							VBox leftWinner = new VBox();
							leftWinner.getChildren().addAll(teamX.text, teamX.textField);

							//winner of right side 
							Team teamY = new Team(innerChallengesLeft.get(innerChallengesRight.size() - 1).get(0).computeWinner().getName());; //needs to be fix

							VBox rightWinner = new VBox();
							rightWinner.getChildren().addAll(teamY.text, teamY.textField);


							//middle championship box
							VBox submitBtnBox = new VBox();
							submitBtnBox.getChildren().addAll(new Text(""), submitBtn3);
							HBox championship = new HBox(); 
							championship.getChildren().addAll(leftWinner, submitBtnBox, rightWinner);
							challengeArray[0].setTeam1(teamX);
							challengeArray[0].setTeam2(teamY);
							grid.add(championArea, numTeams/4 - 1 + w, numTeams/4 - 2);

						}
					});




					submitBtn3.setOnAction(new EventHandler<ActionEvent>() { 

						public void handle(ActionEvent event)  {
							if (challengeArray[0].getTeam1().getScore() > challengeArray[0].getTeam2().getScore()) { 
								championText.setText("Champion: " + challengeArray[0].getTeam1().getName());
								secondText.setText("Runner Up: " + challengeArray[0].getTeam2().getName());
							}
							else { 
								championText.setText("Champion: " + challengeArray[0].getTeam2().getName());
								secondText.setText("Runner Up: " + challengeArray[0].getTeam1().getName());
							}
						}
					});
				}
			
			}
			grid.setStyle("-fx-background-color: #F8BFD1;");
			
			
			//BorderPane containing flow and grid pane as elements
			BorderPane pane = new BorderPane();
			pane.setTop(flow);
			pane.setCenter(grid);
			

			Scene scene = new Scene(pane,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);

			primaryStage.show();

			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
}
