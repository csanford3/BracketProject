package application;
	



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
			grid.setMinSize(300, 300);
			grid.setHgap(20);
			grid.setVgap(20);
			//grid.setGridLinesVisible(true); // for debugging purposes
			
			Button submitBtn = new Button(); 
			submitBtn.setText("Submit");
			submitBtn.setOnAction(new EventHandler<ActionEvent>() { 
				
				public void handle(ActionEvent event)  {
					System.out.println("TBD");
				}
			});
			
			Button submitBtn2 = new Button(); 
			submitBtn2.setText("Submit");
			submitBtn2.setOnAction(new EventHandler<ActionEvent>() { 
				
				public void handle(ActionEvent event)  {
					System.out.println("TBD");
				}
			});
			
			Button submitBtn3 = new Button(); 
			submitBtn3.setText("Submit");
			submitBtn3.setOnAction(new EventHandler<ActionEvent>() { 
				
				public void handle(ActionEvent event)  {
					System.out.println("TBD");
				}
			});
			
			Button submitBtn4 = new Button(); 
			submitBtn4.setText("Submit");
			submitBtn4.setOnAction(new EventHandler<ActionEvent>() { 
				
				public void handle(ActionEvent event)  {
					System.out.println("TBD");
				}
			});
			Button submitBtn5 = new Button(); 
			submitBtn5.setText("Submit");
			submitBtn5.setOnAction(new EventHandler<ActionEvent>() { 
				
				public void handle(ActionEvent event)  {
					System.out.println("TBD");
				}
			});
			
			Button submitBtn6 = new Button(); 
			submitBtn6.setText("Submit");
			submitBtn6.setOnAction(new EventHandler<ActionEvent>() { 
				
				public void handle(ActionEvent event)  {
					System.out.println("TBD");
				}
			});
			
			Button submitBtn7 = new Button(); 
			submitBtn7.setText("Submit");
			submitBtn7.setOnAction(new EventHandler<ActionEvent>() { 
				
				public void handle(ActionEvent event)  {
					System.out.println("TBD");
				}
			});
			
			//LEFT-HAND SIDE OF BRACKET
			
			//matchup 1 v 8
			Team team1 = new Team("Team 1");
			
			
			Team team8 = new Team("Team 8");
			
			
			VBox team1n8 = new VBox();
			Text lineField = new Text(" ");
			team1n8.getChildren().addAll(team1.text, lineField, team8.text);

			VBox teamSubmitBox = new VBox();
			teamSubmitBox.getChildren().addAll(team1.textField, team8.textField, submitBtn2);
			
			HBox challenge1=new HBox();
			challenge1.getChildren().addAll(team1n8, teamSubmitBox);
			
			
			grid.add(challenge1, 0, 0);
			
			
			
			
			//matchup 2 v 7
//			Team team2 = new Team("Team 2");
//			HBox hTeam2=new HBox();
//			team2.textField.setPrefWidth(80);
//			hTeam2.getChildren().addAll(team2.text, team2.textField);
//			team2.textField.setPromptText("Enter Score");
//			
//			Team team7 = new Team("Team 7");
//			HBox hTeam7=new HBox();
//			team7.textField.setPrefWidth(80);
//			hTeam7.getChildren().addAll(team7.text, team7.textField);
//			team7.textField.setPromptText("Enter Score");
//			
//			VBox challenge2=new VBox();
//			challenge2.getChildren().addAll(hTeam2, hTeam7, submitBtn3);
			
			Team team2 = new Team("Team 2");
			
			
			Team team7 = new Team("Team 7");
			
			VBox team2n7 = new VBox();
			Text lineField2 = new Text(" ");
			team2n7.getChildren().addAll(team2.text, lineField2, team7.text);

			VBox teamSubmitBox2 = new VBox();
			teamSubmitBox2.getChildren().addAll(team2.textField, team7.textField, submitBtn3);
			
			HBox challenge2=new HBox();
			challenge2.getChildren().addAll(team2n7, teamSubmitBox2);
			
			
			
			grid.add(challenge2, 0, 2);
			
			
			//winner of left side
			Team teamX = new Team("Team X");
			//HBox hTeamX= new HBox();
			//hTeamX.getChildren().addAll(teamX.text,teamX.textField);
			teamX.textField.setPrefWidth(80);
			VBox leftWinner = new VBox();
			leftWinner.getChildren().addAll(teamX.text, teamX.textField);
			teamX.textField.setPromptText("Enter Score");
			
			
			//RIGHT-HAND SIDE OF BRACKET
			
			//match up 3 v 6
			Team team3 = new Team("Team 3");
			HBox hTeam3=new HBox();
			
			hTeam3.getChildren().addAll(team3.textField, team3.text);
			
			
			Team team6 = new Team("Team 6");
			HBox hTeam6=new HBox();
			
			hTeam6.getChildren().addAll(team6.textField, team6.text);
			
			
			VBox challenge3=new VBox();
			challenge3.getChildren().addAll(hTeam3, hTeam6, submitBtn4);
			grid.add(challenge3, 4, 0);
			
			
			//match up 4 v 5
			Team team4 = new Team("Team 4");
			HBox hTeam4=new HBox();
		
			hTeam4.getChildren().addAll(team4.textField, team4.text);
			
			
			Team team5 = new Team("Team 5");
			HBox hTeam5=new HBox();
			
			hTeam5.getChildren().addAll(team5.textField, team5.text);
			
			
			VBox challenge4=new VBox();
			challenge4.getChildren().addAll(hTeam4, hTeam5, submitBtn5);
			grid.add(challenge4, 4, 2);
			
			
			//winner of right side 
			Team teamY = new Team("Team Y");
			
			VBox rightWinner = new VBox();
			rightWinner.getChildren().addAll(teamY.text, teamY.textField);
			
			
			//middle championship box
			HBox championship = new HBox(); 
			championship.getChildren().addAll(leftWinner, submitBtn, rightWinner);
			grid.add(championship, 2, 1);
			
			
			//left intermediate match up
//			Team teamA = new Team("Team A");
//			HBox hTeamA=new HBox();
//			teamA.textField.setPrefWidth(80);
//			hTeamA.getChildren().addAll(teamA.text, teamA.textField);
//			teamA.textField.setPromptText("Enter Score");
//			
//			Team teamB = new Team("Team B");
//			HBox hTeamB=new HBox();
//			teamB.textField.setPrefWidth(80);
//			hTeamB.getChildren().addAll(teamB.text, teamB.textField);
//			teamB.textField.setPromptText("Enter Score");
//			
//			VBox challenge5=new VBox();
//			challenge5.getChildren().addAll(hTeamA, hTeamB, submitBtn6);
			
			Team teamA = new Team("Team A");
			
			
			Team teamB = new Team("Team 8");
			
			
			VBox teamAnB = new VBox();
			Text lineField5 = new Text(" ");
			teamAnB.getChildren().addAll(teamA.text, lineField5, teamB.text);

			VBox teamSubmitBox5 = new VBox();
			teamSubmitBox5.getChildren().addAll(teamA.textField, teamB.textField, submitBtn6);
			
			HBox challenge5=new HBox();
			challenge5.getChildren().addAll(teamAnB, teamSubmitBox5);
			
			
			grid.add(challenge5, 1, 1);

			
			//right intermediate match up 
			Team teamC = new Team("Team C");
			HBox hTeamC=new HBox();
			
			hTeamC.getChildren().addAll(teamC.textField, teamC.text);
			
			Team teamD = new Team("Team D");
			HBox hTeamD=new HBox();
			
			hTeamD.getChildren().addAll(teamD.textField, teamD.text);
			
			VBox challenge6=new VBox();
			challenge6.getChildren().addAll(hTeamC, hTeamD, submitBtn7);
			grid.add(challenge6, 3, 1);
			
			
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
