package controller_view;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


/*
 * The one that we are going to use in the project
 */
public class JukeBox extends Application{

  public static void main(String[] args) {
	 launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
	 /*
	  * song1 		is the "Select song 1" button
	  * song2 		is the "Select song 2" button
	  * 
	  * input1 		is the textfield that accepts the account name
	  * input2 		is the textfield that accepts the password 
	  * ***NOTE: input2 "may" need modification so that text entered is hid and shown on screen as stars or asterix
	  * 
	  * login 		is the login button
	  * loginText 	is the text after the login button
	  * ***NOTE: loginText "may" need to be dynamically updated to show if the account is valid or not
	  * logout 		is the logout button
	  */

	 //Border pane is the entire scene
	 BorderPane all = new BorderPane();

	 //the top grid pane that contains "Select song 1" and "Select song 2" buttons
	 GridPane topGrid = new GridPane();
	 Button song1 = new Button("Select song 1");
	 Button song2 = new Button("Select song 2");
	 topGrid.add(song1, 0, 0);
	 topGrid.add(song2, 1, 0);

	 //the buttons are set top of the border pane
	 all.setTop(topGrid);

	 //the mid grid contains the textfields, login and logout buttons
	 GridPane midGrid = new GridPane();
	 Label label1 = new Label("Account Name");
	 TextField input1 = new TextField();
	 Label label2 = new Label("        Password");
	 TextField input2 = new TextField();
	 midGrid.add(label1, 0, 0);
	 midGrid.add(input1, 1, 0);
	 midGrid.add(label2, 0, 1);
	 midGrid.add(input2, 1, 1);

	 Button login = new Button("Login");
	 Label loginText = new Label("Login first");
	 Button logout = new Button("Logout");

	 midGrid.add(login, 1,2);
	 midGrid.add(loginText, 1,3);
	 midGrid.add(logout, 1,4);

	 //the entire body is set to the center
	 all.setCenter(midGrid);

	 BorderPane.setMargin(topGrid, new Insets(5, 10, 10, 40));
	 topGrid.setHgap(10); //horizontal gap in pixels => that's what you are asking for
	 topGrid.setVgap(10); //vertical gap in pixels

	 BorderPane.setMargin(midGrid, new Insets(5, 10, 10, 10));
	 midGrid.setHgap(10); //horizontal gap in pixels => that's what makes it clean
	 midGrid.setVgap(10); //vertical gap in pixels

	 //shows the entire stage
	 Scene scene = new Scene(all, 300, 225);
	 primaryStage.setScene(scene);
	 primaryStage.show();
  }

}
