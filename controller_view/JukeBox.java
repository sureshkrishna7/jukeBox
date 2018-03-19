package controller_view;

import java.util.Optional;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Pair;
import model.Player;
import model.PlayerList;
import model.Song;
import model.SongCollection;

/*
 * Class: JukeBox
 * Authors: Suresh Krishna and Andrew Lane
 * Purpose: The GUI for a Jukebox, the controller. Sets the stage for the jukebox, instantiates necessary class variables,
 * many of which are of classes in the model. Includes handlers for when the buttons are pressed. Includes methods for various
 * possibilities of the restrictions of using the jukebox for each user.
 */
public class JukeBox extends Application {

  private PlayerList playerList;
  private SongCollection songCollection;
  private Player currentUser; // the one who uses the GUI
  private TextField input1;
  private PasswordField input2;
  private Label loginText;
  private Button logout;
  private Button login;

  public static void main(String[] args) {
	 launch(args);
  }

  /*
   * start -- inherited from Application, starts the GUI, utilizes BorderPane and GridPane for display.
   */
  @Override
  public void start(Stage primaryStage) throws Exception {
	 /*
	  * song1 is the "Select song 1" button song2 is the "Select song 2" button
	  * 
	  * input1 is the textfield that accepts the account name input2 is the textfield
	  * that accepts the password ***NOTE: input2 "may" need modification so that
	  * text entered is hid and shown on screen as stars or asterix
	  * 
	  * login is the login button loginText is the text after the login button
	  * ***NOTE: loginText "may" need to be dynamically updated to show if the
	  * account is valid or not logout is the logout button
	  */

	 // a PlayerList object is instantiated, default will have 5 hardcoded users
	 playerList = new PlayerList();
	 songCollection = new SongCollection();

	 // Border pane is the entire scene
	 BorderPane all = new BorderPane();

	 // the top grid pane that contains "Select song 1" and "Select song 2" buttons
	 GridPane topGrid = new GridPane();
	 Button song1 = new Button("Select song 1");
	 Button song2 = new Button("Select song 2");
	 ButtonListener handler = new ButtonListener();
	 song1.setOnAction(handler);
	 song2.setOnAction(handler);
	 topGrid.add(song1, 0, 0);
	 topGrid.add(song2, 1, 0);

	 // the buttons are set top of the border pane
	 all.setTop(topGrid);

	 // the mid grid contains the textfields, login and logout buttons
	 GridPane midGrid = new GridPane();
	 Label label1 = new Label("Account Name");
	 input1 = new TextField();
	 Label label2 = new Label("        Password");
	 input2 = new PasswordField();
	 midGrid.add(label1, 0, 0);
	 midGrid.add(input1, 1, 0);
	 midGrid.add(label2, 0, 1);
	 midGrid.add(input2, 1, 1);

	 login = new Button("Login");
	 login.setOnAction(handler);
	 loginText = new Label("Login first");
	 logout = new Button("Logout");
	 logout.setDisable(true);
	 logout.setOnAction(handler);

	 midGrid.add(login, 1, 2);
	 midGrid.add(loginText, 1, 3);
	 midGrid.add(logout, 1, 4);

	 // the entire body is set to the center
	 all.setCenter(midGrid);

	 BorderPane.setMargin(topGrid, new Insets(5, 10, 10, 40));
	 topGrid.setHgap(10); // horizontal gap in pixels => that's what you are asking for
	 topGrid.setVgap(10); // vertical gap in pixels

	 BorderPane.setMargin(midGrid, new Insets(5, 10, 10, 10));
	 midGrid.setHgap(10); // horizontal gap in pixels => that's what makes it clean
	 midGrid.setVgap(10); // vertical gap in pixels

	 // shows the entire stage

	 if(System.getProperty("os.name").contains("Windows")) {
		Scene scene = new Scene(all, 325, 250);
		primaryStage.setScene(scene);
		primaryStage.show();
	 }
	 else {
		Scene scene = new Scene(all, 300, 225);
		primaryStage.setScene(scene);
		primaryStage.show();
	 }
  }// end start

  /*
   * ButtonListener -- handler for buttons pressed in the GUI. Has all necessary actions for possible events and situations.
   */
  private class ButtonListener implements EventHandler<ActionEvent>{

	 @Override
	 public void handle(ActionEvent arg) {
		Button buttonClicked = (Button) arg.getSource();

		/*
		 * The Login button clicked
		 */
		if(buttonClicked.getText().equals("Login")){
		  System.out.println("Login button clicked");

		  String account = input1.getText().trim(); //removes trailing and leading white spaces
		  String password = input2.getText();
		  //if the account name is valid, check if the password is right or wrong
		  if(playerList.getIdList().contains(account)) {
			 System.out.println("User logged in:"+input1.getText()+","+ input2.getText());
			 /*
			  * 
			  * 			***						  ***
			  *  *** currentUser *** should ONLY be initialized after checking the credentials
			  *  			***						  ***
			  *  
			  * OR else people with a correct username (but incorrect) password can play songs
			  *  
			  * login the current user and enable the logout button
			  */
			 if(playerList.getPlayer(account).checkCredential(account, password)) {

				// initialize currentUser inside this conditional
				currentUser = playerList.getPlayer(account);

				// check for admin status
				if(currentUser.isAdmin()) {
				  alertBox();
				}

				loginText.setText(currentUser.songsPlayed() + " selected. " + currentUser.time().getTimeAsString());
				logout.setDisable(false);
			 }
			 else {
				/*
				 * 1) Never set the login text too big
				 * 2) it messes with the GUI
				 */
				loginText.setText("Wrong Password");
			 }

		  }
		  //if no valid condition, then its "invalid credentials"
		  else {
			 loginText.setText("Invalid credentials");
		  }
		}



		/*
		 * If the Logout button is clicked	
		 */
		if(buttonClicked.getText().equals("Logout")) {
		  System.out.println("Logout button clicked");
		  loginText.setText("Login first");
		  currentUser = null;
		  logout.setDisable(true);
		}



		/*
		 * If the song 1 button is clicked
		 */
		if(buttonClicked.getText().equals("Select song 1")) {
		  System.out.println("Song 1 button clicked");

		  Song song1 = songCollection.getSongCollection().get("a");
		  
		  if(currentUser != null) {

			  // 1) if we cannot use the song today
			  // 2) the song has already been used 3 times
			 if(!song1.canUseSongToday()) {
				songUsedThreeTimesAlert(song1);
			 }

			 else if(currentUser.canPlaySong() && currentUser.time().canSubtractTimeBySeconds(song1.getSongLengthSec())) {
				currentUser.useSong();
				currentUser.time().subtractTimeBySeconds(song1.getSongLengthSec());
				song1.useSongToday();
				loginText.setText(currentUser.songsPlayed() + " selected. " + currentUser.time().getTimeAsString());
				songCollection.addSongtoQueue(song1);
			 }
			 // when the player is out of 3 chances
			 else if(!currentUser.canPlaySong()) {
				outOfThreeChoiceAlert();
			 }
			 // when the player is out of 1500 minutes
			 else if(!currentUser.time().canSubtractTimeBySeconds(song1.getSongLengthSec())){
				outofTimeAlert();
			 }
			 //       *******
			 // ****** CHECK if song has reached 3 number of play times per day *************
			 //       *******
			 //else if()
		  }
		}

		/*
		 * If the song 2 button is clicked
		 */
		if(buttonClicked.getText().equals("Select song 2")) {
		  System.out.println("Song 2 button clicked");

		  Song song2 = songCollection.getSongCollection().get("d");

		  if(currentUser != null) {

			  // 1) if we cannot use the song today
			  // 2) the song has already been used 3 times 
			 if(!song2.canUseSongToday()) {
				songUsedThreeTimesAlert(song2);
			 }

			 else if(currentUser.canPlaySong() && currentUser.time().canSubtractTimeBySeconds(song2.getSongLengthSec())) {
				currentUser.useSong();
				currentUser.time().subtractTimeBySeconds(song2.getSongLengthSec());
				song2.useSongToday();
				loginText.setText(currentUser.songsPlayed() + " selected. " + currentUser.time().getTimeAsString());
				
				songCollection.addSongtoQueue(song2);
			 }
			 // when the player is out of 3 chances
			 else if(!currentUser.canPlaySong()) {
				outOfThreeChoiceAlert();
			 }
			 // when the player is out of 1500 minutes
			 else if(!currentUser.time().canSubtractTimeBySeconds(song2.getSongLengthSec())){
				outofTimeAlert();
			 }
			 //       *******
			 // ****** CHECK if song has reached 3 number of play times per day *************
			 //       *******
			 //else if()
		  }
		}
	 }
  }

  /*
   * outOfThreeChoiceAlert() -- method called when a user has maxed out their number of songs to play.
   */
  private void outOfThreeChoiceAlert() {
	 Alert alert = new Alert(AlertType.WARNING);
	 alert.setHeaderText(currentUser.getName()+", You have selected the maximum songs today");
	 alert.setContentText("Try again tomorrow . . . ");
	 //Optional<ButtonType> result = alert.showAndWait();
	 alert.showAndWait();

	 // This is the only result possible in AlertType.WARNING
	 // There are other AlertType modal dialogs that pauses the
	 // application until the user clicks a button or enters something
	 System.out.println("AlertType.Warning, Clicked OK");
  }

  /*
   * songUsedThreeTimesAlert(Song) -- method called when a particular song has been played its maximum alloted times.
   */
  public void songUsedThreeTimesAlert(Song song1) {
	 Alert alert = new Alert(AlertType.WARNING);
	 alert.setHeaderText(song1.getSongTitle()+": Song has already been used 3 times today");
	 alert.setContentText("Select a different song!");
	 //Optional<ButtonType> result = alert.showAndWait();
	 alert.showAndWait();

	 // This is the only result possible in AlertType.WARNING
	 // There are other AlertType modal dialogs that pauses the
	 // application until the user clicks a button or enters something
	 System.out.println("AlertType.Warning, Clicked OK");
  }

  /*
   * outofTimeAlert() -- method called when the user has used all alloted time for one day.
   */
  private void outofTimeAlert() {
	 Alert alert = new Alert(AlertType.WARNING);
	 alert.setHeaderText(currentUser.getName()+", You might be out of your time limit!");
	 alert.setContentText("Your song is longer than your available time limit");
	 //Optional<ButtonType> result = alert.showAndWait();
	 alert.showAndWait();

	 // This is the only result possible in AlertType.WARNING
	 // There are other AlertType modal dialogs that pauses the
	 // application until the user clicks a button or enters something
	 System.out.println("AlertType.Warning, Clicked OK");
  }

  /*
   * alertBox() -- called when the admin logs in, prompts to see if the admin wants to add a new account or just play songs.
   */
  private void alertBox() {
	 // A confirm Dialog
	 Alert confirmAlert = new Alert(AlertType.CONFIRMATION);
	 confirmAlert.setHeaderText("Add a new account?");
	 confirmAlert.setContentText("Click cancel to select songs");
	 Optional<ButtonType> confirmResult = confirmAlert.showAndWait();

	 if (confirmResult.get() == ButtonType.OK) {
		System.out.println("AlertType.CONFIRMATION, Clicked OK");
		addNewUser();
	 }

	 if (confirmResult.get() == ButtonType.CANCEL) {
		System.out.println("AlertType.CONFIRMATION, Clicked Cancel");
	 }
  }

  /*
   * addNewUser() -- called in alertBox() when the admin confirms that they want to add a new user
   */
  private void addNewUser() {
	 // Create a custom dialog with two input fields
	 Dialog<Pair<String, String>> dialog = new Dialog<>();
	 dialog.setTitle("Adding new user");
	 dialog.setHeaderText("Enter the new user ID and password");

	 // Set the button types
	 ButtonType loginButtonType = new ButtonType("Add new user", ButtonData.OK_DONE);
	 dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

	 /*final Button loginButtonType = (Button) dialog.getDialogPane().lookupButton(ButtonType.OK);
	 loginButtonType.addEventFilter(ActionEvent.ACTION, event -> {
	     if (!validateAndStore()) {
	         event.consume();
	     }
	 });
	  */
	 // Create the Account Name and password labels and fields
	 GridPane grid = new GridPane();
	 grid.setHgap(10);
	 grid.setVgap(10);
	 grid.setPadding(new Insets(20, 150, 10, 10));

	 TextField username = new TextField();
	 username.setPromptText("Account Name");
	 PasswordField password = new PasswordField();
	 password.setPromptText("Password");

	 grid.add(new Label("Account Name:"), 0, 0);
	 grid.add(username, 1, 0);
	 grid.add(new Label("Password:"), 0, 1);
	 grid.add(password, 1, 1);

	 dialog.getDialogPane().setContent(grid);

	 // Convert the result to a username-password-pair when the Add user button is
	 // clicked.
	 // This is lambda instead of an instance of a new event handler: shorter code.
	 dialog.setResultConverter(dialogButton -> {
		if (dialogButton == loginButtonType) {
		  return new Pair<>(username.getText(), password.getText());
		}
		return null;
	 });

	 Optional<Pair<String, String>> result = dialog.showAndWait();

	 result.ifPresent(usernamePassword -> {
		if(usernamePassword.getKey().trim().equals("")) {
		}
		else {
		  System.out.println("Username=" + usernamePassword.getKey() + ", Password=" + usernamePassword.getValue());
		  playerList.addPlayer(new Player(usernamePassword.getKey(), usernamePassword.getValue(), false));
		}
	 });

  }
}

