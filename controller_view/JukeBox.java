package controller_view;

import java.io.File;
import java.net.URI;
import java.util.Optional;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Pair;
import model.Player;
import model.PlayerList;
import model.Song;
import model.SongCollection;

/*
 * The one that we are going to use in the project
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
	 Scene scene = new Scene(all, 300, 225);
	 primaryStage.setScene(scene);
	 primaryStage.show();
  }// end start

  private class ButtonListener implements EventHandler<ActionEvent>{

	 @Override
	 public void handle(ActionEvent arg) {
		Button buttonClicked = (Button) arg.getSource();

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
		if(buttonClicked.getText().equals("Logout")) {
		  System.out.println("Logout button clicked");
		  loginText.setText("Login first");
		  currentUser = null;
		}
		if(buttonClicked.getText().equals("Select song 1")) {
		  System.out.println("Song 1 button clicked");

		  Song song1 = songCollection.getSongCollection().get("h");
		  String path = song1.getSongFile();
		  System.out.println("Song path = "+path);
		  // Need a File and URI object so the path works on all OSs
		  File file = new File(path);
		  URI uri = file.toURI();
		  // Play one mp3 and and have code run when the song ends
		  Media media = new Media(uri.toString());
		  MediaPlayer mediaPlayer = new MediaPlayer(media);

		  if(currentUser != null && currentUser.canPlaySong() && currentUser.time().canSubtractTimeBySeconds(song1.getSongLengthSec())) {
			 currentUser.useSong();
			 currentUser.time().subtractTimeBySeconds(song1.getSongLengthSec());
			 mediaPlayer.setOnReady(new BeginingOfSongHandler());
			 mediaPlayer.play();
			 //System.out.println(mediaPlayer.getOnEndOfMedia());
		  }
		}
		if(buttonClicked.getText().equals("Select song 2")) {
		  System.out.println("Song 2 button clicked");

		  Song song2 = songCollection.getSongCollection().get("d");
		  String path2 = song2.getSongFile();
		  System.out.println("Song path = "+path2);
		  // Need a File and URI object so the path works on all OSs
		  File file2 = new File(path2);
		  URI uri2 = file2.toURI();
		  // Play one mp3 and and have code run when the song ends
		  Media media2 = new Media(uri2.toString());
		  MediaPlayer mediaPlayer2 = new MediaPlayer(media2);

		  if(currentUser != null && currentUser.canPlaySong() && currentUser.time().canSubtractTimeBySeconds(song2.getSongLengthSec())) {
			 currentUser.useSong();
			 currentUser.time().subtractTimeBySeconds(song2.getSongLengthSec());
			 mediaPlayer2.setOnReady(new BeginingOfSongHandler());
			 mediaPlayer2.play();
			 //System.out.println(mediaPlayer2.getOnEndOfMedia());
		  }
		}
	 }
  }

  private class BeginingOfSongHandler implements Runnable {
	 @Override
	 public void run() {
		// This Runnable apparently does not get called all the time.
		// However, I have the same code in my Jukebox and it works.
		// This question "setOnEndOfMedia does not work" is unanswered on the web.
		loginText.setText(currentUser.songsPlayed() + " selected. " + currentUser.time().getTimeAsString());
		System.out.println("Song ended, played a song");
	 }
  }


  // Note: This code snippet is a modified version of the Custom Login Dialog
  // example found at: http://code.makery.ch/blog/javafx-dialogs-official/.
  // Modifications by Rick Mercer.
  //
  // Rick is providing this to use "as is" for your Jukebox project
  // and long as you in the above attribution.
  private void addNewUser() {
	 // Create a custom dialog with two input fields
	 Dialog<Pair<String, String>> dialog = new Dialog<>();
	 dialog.setTitle("Adding new user");
	 dialog.setHeaderText("Enter the new user ID and password");

	 // Set the button types
	 ButtonType loginButtonType = new ButtonType("Add new user", ButtonData.OK_DONE);
	 dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

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
		System.out.println("Username=" + usernamePassword.getKey() + ", Password=" + usernamePassword.getValue());
		playerList.getList().add(new Player(usernamePassword.getKey(), usernamePassword.getValue(), false));
	 });

  }
}

