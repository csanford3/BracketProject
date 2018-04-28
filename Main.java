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

	
	@Override
	public void start(Stage primaryStage) {
		
		try {
			
			
			primaryStage.setTitle("Bracket Application");
			
		
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
			
			//FORMULA FIRST ROUND FOR ARBITRARY NUMBER OF TEAMS
			/**
			 * If the number of teams is 2^N then store those teams
			 * in an array of size 2^N-1, say teamArray[].
			 * 
			 * Next after the team names have been read into a String array
			 * of the same size, say nameArray[], assuming that the teams
			 * are given in some logical order, such as rank 1 to rank 16 then...
			 * 
			 * Loop through from k=0 to N, setting
			 * 
			 * teamArray[k] = new Team(nameArray[k]);
			 * 
			 * Now create an array of Challenges 
			 * 
			 * Next loop through again from k=0 to N/2, setting the challenges for the 
			 * left hand side
			 * 
			 * for (int k=0, N/2, k++)
			 *    challengeArray[k] = new Challenge(teamArray[k], teamArray[2^N-1-k], "left");
			 *    
			 *    grid.add(challenge1.challengeBox, 0, 2k);
			 *    
			 * Then loop from N/2 to N to finish the right hand side
			 * 
			 * for (int k=N/2, N, k++)
			 * 		challengeArray[k] = new Challenge(teamArray[k], teamArray[2^N-1-k], "right");
			 * 
			 * 		grid.add(challenge1.challengeBox, 2^N, 2k);
			 */
			
			/**
			 * Implemented some ideas from Chance's rough outline above^^. Needed to tweak a couple things, such as
			 * the assignment of Challenges to the left side or right side of the bracket. When pairing Teams with index #
			 * with their (numTeams - #) the distribution of left and right is not even. See the picture I sent 
			 * on facebook. But since we only have up to 16 teams, this can be alleviated with a little bit of 
			 * hard coding since it doesn't increase complexity. But by all means, if there is a better way, 
			 * we can definitely implement that. I stopped short of implementing the submit button stuff into this mix, 
			 * because it would probably be a good idea if you guys troubleshooted this part first. 
			 */
			
			//TO-DO ->Grid system needs to be implemented to work with these data structures
			
			//read in files here to get number of teams 
			
			//not sure of number of teams -> so use arrayList
			ArrayList<String> nameArray = new ArrayList<String>();
			int numTeams = nameArray.size();
		
			//numRounds is intended to keep track of which layer(round) of the bracket we are working with
			int numRounds = 0;
			
			//gets the number of rounds(aka the N in 2^N)
			int roundTracker = numTeams/2;
			while(roundTracker > 1) { 
				numRounds += 1;
			} 
			
			//populate initial array of Team Objects -> will populate challenge array after
			Team[] teamArray = new Team[numTeams] ;
			int i = 0;
			for (String s : nameArray) { 
				teamArray[i] = new Team(s);
			}
			
			//only need to iterate until numTeams/2 because each lower seed(ie 1-8) is matched up with higher seed. 
			//the problem here is classifying the challenge as left or right -> see hard coded k's(viable option though)
			Challenge[] challengeArray = new Challenge[numTeams/2];
			for (int k = 0; k < numTeams/2; k ++) { 
				if (k == 0 || k == 3 || k == 4 || k == 7) { 
					challengeArray[k] = new Challenge(teamArray[k], teamArray[numTeams-1-k], "left");
				}
				else { 
					challengeArray[k] = new Challenge(teamArray[k], teamArray[numTeams-1-k], "right");
				}
				
				//teams cut in half when advancing a round
				numRounds --;
				numTeams = numTeams/2;
			}
			
			
			while (numRounds != 0)  { 
				
				//means there are 3 more rounds to populate
				if (numRounds == 3) { 
					Challenge[] updateChallengeArray = new Challenge[numTeams/2];
					for (int k = 0; k < numTeams/2; k ++) { 
						
						if (k == 0 || k == 3) { 
							updateChallengeArray[k] = new Challenge(
									new Team(challengeArray[k].computeWinner().getName()), 
									new Team(challengeArray[numTeams-1-k].computeWinner().getName()), "left");
						}
						
						else { 
							updateChallengeArray[k] = new Challenge(
									new Team(challengeArray[k].computeWinner().getName()), 
									new Team(challengeArray[numTeams-1-k].computeWinner().getName()), "right");
						}
					}
				}
				
				//means two more rounds to be populated
				else if (numRounds == 2) { 
					Challenge[] updateChallengeArray = new Challenge[numTeams/2];
					for (int k = 0; k < numTeams/2; k ++) { 
						
						//since there are only indexes 0 and 1 in challengeArray when there are two rounds left
						if (k == 0) { 
							updateChallengeArray[k] = new Challenge(
									new Team(challengeArray[k].computeWinner().getName()), 
									new Team(challengeArray[numTeams-1-k].computeWinner().getName()), "left");
						}
						
						else { 
							updateChallengeArray[k] = new Challenge(
									new Team(challengeArray[k].computeWinner().getName()), 
									new Team(challengeArray[numTeams-1-k].computeWinner().getName()), "right");
						}
					}
				}
				
				//means there are two teams, and championship box has to be populated
				else if (numRounds == 1) { 
					//still have to implement
					
				}
				
				numRounds --;
				numTeams = numTeams/2;
			}
		
			
			//LEFT-HAND SIDE OF BRACKET
			
			//matchup 1 v 8
			Team team1 = new Team("Team 1");
			Team team8 = new Team("Team 8");
			
			Challenge challenge1 = new Challenge(team1, team8, "left");
			
			grid.add(challenge1.challengeBox, 0, 0);
			
			
			
			
			//matchup 2 v 7
			
			Team team2 = new Team("Team 2");
			Team team7 = new Team("Team 7");
			
			Challenge challenge2 = new Challenge(team2, team7, "left");
			
			grid.add(challenge2.challengeBox, 0, 2);
			
			//Left intermediate match up
			
			//***Could not find a better way of doing this so for rounds 2,3,etc...
			//a new challenge is created by computing the winner of the previous round's games
			//and creating new Team objects with the same name as the previous game's winner.  These
			//new Team objects are then used to create the next Challenge

			Team teamA = new Team(challenge1.computeWinner().getName());
			Team teamB = new Team(challenge2.computeWinner().getName());
			
			Challenge challenge5 = new Challenge(teamA, teamB, "left");

			grid.add(challenge5.challengeBox, 1, 1);
			
			
			//winner of left side
			Team teamX = new Team("Team X");
			
			VBox leftWinner = new VBox();
			leftWinner.getChildren().addAll(teamX.text, teamX.textField);
		
			
			
			//RIGHT-HAND SIDE OF BRACKET
			
			//match up 3 v 6
			Team team3 = new Team("Team 3");

			Team team6 = new Team("Team 6");
			
			Challenge challenge3 = new Challenge(team3, team6, "right");
			
			grid.add(challenge3.challengeBox, 4, 0);
			
			
			//match up 4 v 5
			Team team4 = new Team("Team 4");
			Team team5 = new Team("Team 5");
			
			Challenge challenge4 = new Challenge(team4, team5, "right");

			grid.add(challenge4.challengeBox, 4, 2);
			
			//right intermediate match up
			
			//***Could not find a better way of doing this so for rounds 2,3,etc...
			//a new challenge is created by computing the winner of the previous round's games
			//and creating new Team objects with the same name as the previous game's winner.  These
			//new Team objects are then used to create the next Challenge

			
			Team teamC = new Team(challenge3.computeWinner().getName());
			Team teamD = new Team(challenge4.computeWinner().getName());
			
			
			Challenge challenge6 = new Challenge(teamC, teamD, "right");
	
			grid.add(challenge6.challengeBox, 3, 1);
			
			
			//winner of right side 
			Team teamY = new Team("\t    Team Y");
			
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
