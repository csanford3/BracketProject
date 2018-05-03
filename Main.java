package application;
	



import java.util.ArrayList;

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


public class Main extends Application {

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
		
		try {
			
			
			primaryStage.setTitle("Bracket Application");
			
			ScrollPane scroll= new ScrollPane();
			
		
			FlowPane flow = new FlowPane();
			flow.setPadding(new Insets(10, 10, 10, 10));
			flow.setStyle("-fx-background-color: AAE6F3;");
			flow.setHgap(5);
			
			
			//Gridpane shich allows flexibility
			GridPane grid = new GridPane();
			//Padding sets space around edge of window
			grid.setPadding(new Insets(10, 10, 10, 10));
			
			//Alignment of window set to center
			grid.setAlignment(Pos.CENTER);
			grid.setMinSize(500, 500);
			grid.setHgap(20);
			grid.setVgap(20);
			grid.setGridLinesVisible(true); // for debugging purposes
			
			Button submitBtn = new Button(); 
			submitBtn.setText("Submit");
			submitBtn.setOnAction(new EventHandler<ActionEvent>() { 
				
				public void handle(ActionEvent event)  {
					System.out.println("TBD");
				}
			});
			
			
			//TO-DO ->Grid system needs to be implemented to work with these data structures
			
			//read in files here to get number of teams 
			
			//not sure of number of teams -> so use arrayList
			ArrayList<String> nameArray = new ArrayList<String>();
			nameArray.add("1");
			nameArray.add("2");
			nameArray.add("3");
			nameArray.add("4");
//			nameArray.add("5");
//			nameArray.add("6");
//			nameArray.add("7");
//			nameArray.add("8");
//			nameArray.add("9");
//			nameArray.add("10");
//			nameArray.add("11");
//			nameArray.add("12");
//			nameArray.add("13");
//			nameArray.add("14");
//			nameArray.add("15");
//			nameArray.add("16");
			int numTeams = nameArray.size();
		
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
			
			//populates the outter instance of the bracket
			//only need to iterate until numTeams/2 because each lower seed(ie 1-8) is matched up with higher seed.
			int p = 0; //for iterating through indexArray
			for (int k = 0; k < numTeams/2; k ++) { 
				if (k < numTeams/4)
				    challengeArray[k] = new Challenge(teamArray[indexArray.get(p)-1], teamArray[indexArray.get(p+1)-1], "left");
				else
					challengeArray[k] = new Challenge(teamArray[indexArray.get(p)-1], teamArray[indexArray.get(p+1)-1], "right");
				p+= 2;
				
			/*	//teams cut in half when advancing a round
				numRounds --;
				numTeams = numTeams/2; */
			}
			
			if (numTeams == 2) {
				
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
				submitBtnBox.getChildren().addAll(new Text(""), submitBtn);
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
			}
			
			
			else {
				

				//Left Hand Side of Grid
				Challenge[] leftEmpty = new Challenge[9];

				for (int g = 0; g < 9; g++) {
					leftEmpty[g] = new Challenge(new Team(""), new Team(""), "left");
				}
				
				

				for(int j = 0; j < numTeams/4; j++) {
					grid.add(challengeArray[j].challengeBox, 0, 2*j);		
				}
				int inc = 0;
				for (int j = 0; j < numTeams/8; j++) {

					for(int k = 0; k < numTeams/8-j; k++) {
						grid.add(leftEmpty[inc].challengeBox, j+1, 4*k + (2*j+1));
						inc++;
					}

				}

				
				Challenge[] rightEmpty = new Challenge[9];

				for (int g = 0; g < 9; g++) {
					rightEmpty[g] = new Challenge(new Team(""), new Team(""), "right");
				}


				//Right Hand Side of Grid

				for(int j = numTeams/4; j < numTeams/2; j++) {
					grid.add(challengeArray[j].challengeBox, numTeams/2 - 2, 2*j-2*numTeams/4);		
				}
				int inc2 = 0;
				for (int j = 0; j < numTeams/8; j++) {

					for(int k = 0; k < numTeams/8-j; k++) {
						System.out.println("(" +  (numTeams/4+1-j) + "," +  (4*k + (2*j+1)) + ")");

						grid.add(rightEmpty[numTeams/8 + inc2 + 1].challengeBox, numTeams/4+1-j, 4*k + (2*j+1));
						inc2++;


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
				submitBtnBox.getChildren().addAll(new Text(""), submitBtn);
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
				
				
				grid.add(centerBox, numTeams/4 - 1, numTeams/4 -1);
				grid.add(championArea, numTeams/4 - 1, numTeams/4 - 2);
				grid.add(secondArea, numTeams/4 - 1, numTeams/4);
			}
			
//			grid.add(emptyArray[4].challengeBox, 1, 5);
//			
//			grid.add(emptyArray[6].challengeBox, 2, 3);
//			
//			//LEFT-HAND SIDE OF BRACKET
//			
//			//matchup 1 v 8
//			Team team1 = new Team("Team 1");
//			Team team8 = new Team("Team 8");
//			
//			Challenge challenge1 = new Challenge(team1, team8, "left");
//			
//			grid.add(challenge1.challengeBox, 0, 0);
//			
//			
//			
//			
//			//matchup 2 v 7
//			
//			Team team2 = new Team("Team 2");
//			Team team7 = new Team("Team 7");
//			
//			Challenge challenge2 = new Challenge(team2, team7, "left");
//			
//			grid.add(challenge2.challengeBox, 0, 2);
//			
//			//Left intermediate match up
//			
//			//***Could not find a better way of doing this so for rounds 2,3,etc...
//			//a new challenge is created by computing the winner of the previous round's games
//			//and creating new Team objects with the same name as the previous game's winner.  These
//			//new Team objects are then used to create the next Challenge
//
//			Team teamA = new Team(challenge1.computeWinner().getName());
//			Team teamB = new Team(challenge2.computeWinner().getName());
//			
//			Challenge challenge5 = new Challenge(teamA, teamB, "left");
//
//			grid.add(challenge5.challengeBox, 1, 1);
//			
//			
//			//winner of left side
//			Team teamX = new Team("Team X");
//			
//			VBox leftWinner = new VBox();
//			leftWinner.getChildren().addAll(teamX.text, teamX.textField);
//		
//			
//			
//			//RIGHT-HAND SIDE OF BRACKET
//			
//			//match up 3 v 6
//			Team team3 = new Team("Team 3");
//
//			Team team6 = new Team("Team 6");
//			
//			Challenge challenge3 = new Challenge(team3, team6, "right");
//			
//			grid.add(challenge3.challengeBox, 4, 0);
//			
//			
//			//match up 4 v 5
//			Team team4 = new Team("Team 4");
//			Team team5 = new Team("Team 5");
//			
//			Challenge challenge4 = new Challenge(team4, team5, "right");
//
//			grid.add(challenge4.challengeBox, 4, 2);
//			
//			//right intermediate match up
//			
//			//***Could not find a better way of doing this so for rounds 2,3,etc...
//			//a new challenge is created by computing the winner of the previous round's games
//			//and creating new Team objects with the same name as the previous game's winner.  These
//			//new Team objects are then used to create the next Challenge
//
//			
//			Team teamC = new Team(challenge3.computeWinner().getName());
//			Team teamD = new Team(challenge4.computeWinner().getName());
//			
//			
//			Challenge challenge6 = new Challenge(teamC, teamD, "right");
//	
//			grid.add(challenge6.challengeBox, 3, 1);
//			
//			
//			//winner of right side 
//			Team teamY = new Team("\t    Team Y");
//			
//			VBox rightWinner = new VBox();
//			rightWinner.getChildren().addAll(teamY.text, teamY.textField);
//			
//			
//			//middle championship box
//			VBox submitBtnBox = new VBox();
//			submitBtnBox.getChildren().addAll(new Text(""), submitBtn);
//			HBox championship = new HBox(); 
//			championship.getChildren().addAll(leftWinner, submitBtnBox, rightWinner);
//			grid.add(championship, 2, 1);
//			
//			//champion and runner up areas - mostly lines 
//			VBox championArea = new VBox();
//			Line hLine = new Line();
//			Line vLine = new Line();
//			Text championText = new Text();
//			championText.setText("Champion: Team Name");
//			
//			
//			hLine.setStartX(0.0f);
//			hLine.setStartY(160.0f);
//			hLine.setEndX(160.0f);
//			hLine.setEndY(160.00f);
//			
//			vLine.setStartX(0.0f);
//			vLine.setStartY(0.0f);
//			vLine.setEndX(0.0);
//			vLine.setEndY(30.00f);
//			
//			championArea.getChildren().addAll(championText, hLine, vLine);
//			championArea.setAlignment(Pos.BOTTOM_CENTER);
//			grid.add(championArea, 2, 0);
//			
//			VBox secondArea = new VBox();
//			Line hLine2 = new Line();
//			Line vLine2 = new Line();
//			Text secondText = new Text();
//			secondText.setText("Runner-Up: Team Name");
//			
//			
//			hLine2.setStartX(0.0f);
//			hLine2.setStartY(160.0f);
//			hLine2.setEndX(160.0f);
//			hLine2.setEndY(160.00f);
//			
//			vLine2.setStartX(0.0f);
//			vLine2.setStartY(0.0f);
//			vLine2.setEndX(0.0);
//			vLine2.setEndY(30.00f);
//			
//			secondArea.getChildren().addAll(vLine2, hLine2, secondText);
//			secondArea.setAlignment(Pos.TOP_CENTER);
//			grid.add(secondArea, 2, 2);
//			
			
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