package application;
	



import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;


public class Main extends Application {

	
	@Override
	public void start(Stage primaryStage) {
		
		try {
			
			
			primaryStage.setTitle("Bracket Application");
			
			
			
			Button btn = new Button();
			
			btn.setText("Oh wow, I did a JavaFX!");
			
			btn.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent event) {
					System.out.println("Why do I have to put this print statement here");
				}
				
			});
			
			//Stackpane for the banner
			StackPane paneRoot = new StackPane();
			paneRoot.getChildren().add(btn);
			
			FlowPane flow = new FlowPane();
			flow.setPadding(new Insets(10, 10, 10, 10));
			flow.setStyle("-fx-background-color: AAE6F3;");
			flow.setHgap(5);
			flow.getChildren().add(btn);
			
			//Gridpane shich allows flexibility
			GridPane grid = new GridPane();
			grid.setPadding(new Insets(10, 10, 10, 10));
			grid.setMinSize(300, 300);
			grid.setVgap(5);
			grid.setHgap(5);

			Text team1 = new Text("Team 1");
			grid.add(team1, 0, 0);

			TextField text1 = new TextField();
			text1.setPrefColumnCount(10);
			grid.add(text1, 1, 0);

			Text team2 = new Text("Team 2");
			grid.add(team2, 0, 1);

			TextField text2 = new TextField();
			text2.setPrefColumnCount(10);
			grid.add(text2, 1, 1);
			
			Text team3 = new Text("Team 3");
			grid.add(team3, 4, 0);
			
			TextField text3 = new TextField();
			text3.setPrefColumnCount(10);
			grid.add(text3, 3, 0);
			
			
			
			Team team4 = new Team("Team4");

			
			grid.add(team4.text, 5, 1);
			
			
			team4.textField.setPrefColumnCount(10);
			grid.add(team4.textField, 4, 1);
			
			
			
			grid.setStyle("-fx-background-color: #F8BFD1;");
			
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
