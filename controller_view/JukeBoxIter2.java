package controller_view;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Pair;
import model.Player;
import model.PlayerList;
import model.Song;
import model.SongCollection;
import model.SongQueue;
import model.SongViewer;

/*
 * The one that we are going to use in the project
 */
public class JukeBoxIter2 extends Application {

  private PlayerList playerList;
  private static SongCollection songCollection;
  private static SongQueue songQueue;
  private Player currentUser; // the one who uses the GUI
  private TextField input1;
  private PasswordField input2;
  private Label loginText;
  private Button logout;
  private Button login;
  private Button play;
  private SongViewer songViewer;
  private ListView<String> listView;
  private static ObservableList<String> songQueueTitle;
  //private ObservableList<Serializable> persistList;
  private ProgressBar progBar;
  private final static String persistedSongQueueFile = "SongQueueData";
  private final static String persistedPlayerListFile = "PlayerListData";
  private final static String persistedSongCollectionFile = "SongCollectionData";

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
	 songQueue = new SongQueue();
	 songQueueTitle = FXCollections.observableArrayList();

	 //  **                      *****
	 //***DONT*** Intialize ****songViewer**** here
	 //   **                      *****

	 initializeList();

	 progBar = new ProgressBar(0);
	 List<Serializable> list = new ArrayList<Serializable>();
	 //persistList = FXCollections.observableList(list);

	 // queueViewer = new QueueViewer(songCollection.getSongQueue());
	 // Border pane is the entire scene
	 BorderPane all = new BorderPane();

	 // the top grid pane that contains the SongViewer object
	 GridPane topGrid = new GridPane();
	 ButtonListener loginHandler = new ButtonListener();

	 ButtonListener playHandler = new ButtonListener();
	 // song1.setOnAction(handler);
	 // song2.setOnAction(handler);
	 // topGrid.add(song1, 0, 0);
	 // topGrid.add(song2, 1, 0);
	 listView = new ListView<String>(songQueueTitle);

	 Label songListHeader = new Label("Song List");
	 topGrid.add(songListHeader, 0, 0);

	 // adding songViewer TableView
	 topGrid.add(songViewer, 0, 1);

	 Label queueListHeader = new Label("Song Queue");
	 topGrid.add(queueListHeader, 2, 0);

	 play = new Button("Play");
	 topGrid.add(play, 1, 1);

	 // adding queueViewer TableView
	 topGrid.add(listView, 2, 1);

	 // the buttons are set top of the border pane
	 all.setTop(topGrid);

	 // the mid grid contains the textfields, login and logout buttons
	 GridPane midGrid = new GridPane();
	 Label label1 = new Label("Account Name");
	 input1 = new TextField();
	 Label label2 = new Label("        Password");
	 input2 = new PasswordField();
	 midGrid.add(label1, 0, 1);
	 midGrid.add(input1, 1, 1);
	 midGrid.add(label2, 0, 2);
	 midGrid.add(input2, 1, 2);

	 login = new Button("Login");
	 login.setOnAction(loginHandler);
	 loginText = new Label("Login first");
	 logout = new Button("Logout");
	 logout.setDisable(true);
	 logout.setOnAction(loginHandler);

	 play.setOnAction(playHandler);

	 midGrid.add(login, 2, 1);
	 midGrid.add(loginText, 1, 0);
	 midGrid.add(logout, 2, 2);

	 // the entire body is set to the center
	 all.setCenter(midGrid);

	 BorderPane.setMargin(topGrid, new Insets(5, 10, 10, 40));
	 topGrid.setHgap(10); // horizontal gap in pixels => that's what you are asking for
	 topGrid.setVgap(10); // vertical gap in pixels

	 BorderPane.setMargin(midGrid, new Insets(5, 10, 10, 10));
	 midGrid.setHgap(10); // horizontal gap in pixels => that's what makes it clean

	 midGrid.setVgap(10); // vertical gap in pixels
	 // shows the entire stage

	 if (System.getProperty("os.name").contains("Windows")) {
		Scene scene = new Scene(all);
		primaryStage.setScene(scene);
		primaryStage.sizeToScene();
		primaryStage.show();
		primaryStage.setMinWidth(primaryStage.getWidth());
		primaryStage.setMinHeight(primaryStage.getHeight());
	 } else {
		Scene scene = new Scene(all);
		primaryStage.setScene(scene);
		primaryStage.sizeToScene();
		primaryStage.show();
		primaryStage.setMinWidth(primaryStage.getWidth());
		primaryStage.setMinHeight(primaryStage.getHeight());
	 }
	 primaryStage.setOnCloseRequest(new WritePersistentObject());
  }// end start
  // Save a List<E> of the current list of strings

  private void initializeList() {
	 Alert alert = new Alert(AlertType.CONFIRMATION);
	 alert.setTitle("Start up Option");
	 alert.setHeaderText("Press ok to read persistent object(s)");
	 alert.setContentText("Press cancel while system testing.");
	 Optional<ButtonType> result = alert.showAndWait();
	 if (result.get() == ButtonType.OK) {
		readPersistList();
	 } else {
		defaultList();
	 }
  }

  private void defaultList() {
	 playerList.setUpDefault();
	 songCollection.defaultSongCollection();
	 songViewer = new SongViewer(songCollection);
  }

  private void readPersistList() {
	 try {
		FileInputStream fileOutput = new FileInputStream(persistedSongCollectionFile);
		ObjectInputStream in = new ObjectInputStream(fileOutput);

		@SuppressWarnings("unchecked")
		ArrayList<Serializable> list3 = (ArrayList<Serializable>) in.readObject();
		for (Serializable song : list3) {
		  songCollection.addSong((Song) song);
		  
		}
		
		songViewer = new SongViewer(songCollection);
		
		in.close();
	 } catch (FileNotFoundException e) {
		defaultList();
		e.printStackTrace();
		return;
	 } catch (IOException e) {
		e.printStackTrace();
	 } catch (ClassNotFoundException e) {
		e.printStackTrace();
	 }
	 	 
	 try {
		FileInputStream fileOutput = new FileInputStream(persistedSongQueueFile);
		ObjectInputStream in = new ObjectInputStream(fileOutput);
		// TODO 9: Read the object from a disk file, assuming it is present
		// FXCollections.observableList(List<E>) is not Serializable
		// so we have to store all elements into a List of some sort

		//do this

		@SuppressWarnings("unchecked")
		ArrayList<Serializable> list = (ArrayList<Serializable>) in.readObject();
		for (Serializable string : list) {
		  songQueueTitle.add((String) string);

		  //*******************************************
		  // Already did all the calculations for songs when the user was logged in
		  //
		  // This is just to play all the remaining songs when the application logs back in
		  //*******************************************
		  songQueue.addSongtoQueue(songCollection.getSong((String)string));
		}

		//or this
		/*
			observableList.toArray();
		 */
		in.close();
	 } catch (FileNotFoundException e) {
		e.printStackTrace();
	 } catch (IOException e) {
		e.printStackTrace();
	 } catch (ClassNotFoundException e) {
		e.printStackTrace();
	 }

	 try {
		FileInputStream fileOutput = new FileInputStream(persistedPlayerListFile);
		ObjectInputStream in = new ObjectInputStream(fileOutput);

		@SuppressWarnings("unchecked")
		ArrayList<Serializable> list2 = (ArrayList<Serializable>) in.readObject();
		for (Serializable player : list2) {
		  playerList.addPlayer((Player) player);
		}
		in.close();
	 } catch (FileNotFoundException e) {
		e.printStackTrace();
	 } catch (IOException e) {
		e.printStackTrace();
	 } catch (ClassNotFoundException e) {
		e.printStackTrace();
	 }

  }
  public void writePersistList() {
	 try {
		FileOutputStream fileOutput = new FileOutputStream(persistedSongQueueFile);
		ObjectOutputStream out = new ObjectOutputStream(fileOutput);
		// FXCollections.observableList(List<E>) is not Serializable
		// so we have to store all elements into a List of some sort

		ArrayList<Serializable> list = new ArrayList<Serializable>();
		for (Serializable obj : songQueueTitle) {
		  list.add(obj);
		}

		out.writeObject(list);
		out.close();
	 } catch (IOException e) {
		e.printStackTrace();
	 }

	 try {
		FileOutputStream fileOutput2 = new FileOutputStream(persistedPlayerListFile);
		ObjectOutputStream out2 = new ObjectOutputStream(fileOutput2);

		ArrayList<Serializable> list2 = new ArrayList<Serializable>();
		for (Serializable obj2 : playerList.getList()) {
		  list2.add(obj2);
		}

		out2.writeObject(list2);
		out2.close();
	 } catch (IOException e) {
		e.printStackTrace();
	 }

	 try {
		FileOutputStream fileOutput3 = new FileOutputStream(persistedSongCollectionFile);
		ObjectOutputStream out3 = new ObjectOutputStream(fileOutput3);

		ArrayList<Serializable> list3 = new ArrayList<Serializable>();
		for (Serializable obj3 : songCollection.getSongCollection()) {
		  list3.add(obj3);
		}
		out3.writeObject(list3);
		out3.close();
	 } catch (IOException e) {
		e.printStackTrace();
	 }

  }

  private class WritePersistentObject implements EventHandler<WindowEvent> {

	 @Override
	 public void handle(WindowEvent event) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Shut Down Option");
		alert.setHeaderText("Press ok to write persistent object(s)");
		alert.setContentText("Press cancel while system testing.");
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
		  writePersistList();
		}

	 }

  }

  private class ButtonListener implements EventHandler<ActionEvent> {

	 @Override
	 public void handle(ActionEvent arg) {
		Button buttonClicked = (Button) arg.getSource();
		// System.out.printf(buttonClicked.getText());
		/*
		 * The Play button clicked
		 */
		if (buttonClicked.getText().equals("Play")) {
		  // System.out.printf("play clicked\n");
		  Song song = songViewer.getSelectionModel().getSelectedItem();
		  // System.out.printf("song chosen:" +
		  // songViewer.getSelectionModel().getSelectedItem().toString());
		  if (currentUser != null) {

			 // 1) if we cannot use the song today
			 // 2) the song has already been used 3 times
			 if (!song.canUseSongToday()) {
				songUsedThreeTimesAlert(song);
			 }

			 else if (currentUser.canPlaySong()
				  && currentUser.time().canSubtractTimeBySeconds(song.getSongLengthSec())) {
				currentUser.useSong();
				currentUser.time().subtractTimeBySeconds(song.getSongLengthSec());
				song.useSongToday();
				loginText.setText(
					 currentUser.songsPlayed() + " selected. " + currentUser.time().getTimeAsString());
				songQueueTitle.add(song.getTitle());
				songQueue.addSongtoQueue(song);
				songViewer.updatePlayCount();
			 }
			 // when the player is out of 3 chances
			 else if (!currentUser.canPlaySong()) {
				outOfThreeChoiceAlert();
			 }
			 // when the player is out of 1500 minutes
			 else if (!currentUser.time().canSubtractTimeBySeconds(song.getSongLengthSec())) {
				outofTimeAlert();
			 }
			 // *******
			 // ****** CHECK if song has reached 3 number of play times per day *************
			 // *******
			 // else if()
		  }
		  // songQueueTitle.remove(0);
		}

		/*
		 * The Login button clicked
		 */
		if (buttonClicked.getText().equals("Login"))

		{
		  System.out.println("Login button clicked");

		  String account = input1.getText().trim(); // removes trailing and leading white spaces
		  String password = input2.getText();
		  // if the account name is valid, check if the password is right or wrong
		  if (playerList.getIdList().contains(account)) {
			 System.out.println("User logged in:" + input1.getText() + "," + input2.getText());
			 /*
			  * 
			  * *** *** *** currentUser *** should ONLY be initialized after checking the
			  * credentials *** ***
			  * 
			  * OR else people with a correct username (but incorrect) password can play
			  * songs
			  * 
			  * login the current user and enable the logout button
			  */
			 if (playerList.getPlayer(account).checkCredential(account, password)) {

				// initialize currentUser inside this conditional
				currentUser = playerList.getPlayer(account);
				// check for admin status
				if (currentUser.isAdmin()) {
				  alertBox();
				}

				loginText.setText(
					 currentUser.songsPlayed() + " selected. " + currentUser.time().getTimeAsString());
				logout.setDisable(false);
			 } else {
				/*
				 * 1) Never set the login text too big 2) it messes with the GUI
				 */
				loginText.setText("Wrong Password");
			 }

		  }
		  // if no valid condition, then its "invalid credentials"
		  else {
			 loginText.setText("Invalid credentials");
		  }
		}

		/*
		 * If the Logout button is clicked
		 */
		if (buttonClicked.getText().equals("Logout")) {
		  System.out.println("Logout button clicked");
		  loginText.setText("Login first");
		  currentUser = null;
		  logout.setDisable(true);
		}
	 }

	 private void outOfThreeChoiceAlert() {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setHeaderText(currentUser.getName() + ", You have selected the maximum songs today");
		alert.setContentText("Try again tomorrow . . . ");
		// Optional<ButtonType> result = alert.showAndWait();
		alert.showAndWait();

		// This is the only result possible in AlertType.WARNING
		// There are other AlertType modal dialogs that pauses the
		// application until the user clicks a button or enters something
		System.out.println("AlertType.Warning, Clicked OK");
	 }

	 public void songUsedThreeTimesAlert(Song song1) {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setHeaderText(song1.getTitle() + ": Song has already been used 3 times today");
		alert.setContentText("Select a different song!");
		// Optional<ButtonType> result = alert.showAndWait();
		alert.showAndWait();

		// This is the only result possible in AlertType.WARNING
		// There are other AlertType modal dialogs that pauses the
		// application until the user clicks a button or enters something
		System.out.println("AlertType.Warning, Clicked OK");
	 }

	 private void outofTimeAlert() {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setHeaderText(currentUser.getName() + ", You might be out of your time limit!");
		alert.setContentText("Your song is longer than your available time limit");
		// Optional<ButtonType> result = alert.showAndWait();
		alert.showAndWait();

		// This is the only result possible in AlertType.WARNING
		// There are other AlertType modal dialogs that pauses the
		// application until the user clicks a button or enters something
		System.out.println("AlertType.Warning, Clicked OK");
	 }

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

		/*
		 * final Button loginButtonType = (Button)
		 * dialog.getDialogPane().lookupButton(ButtonType.OK);
		 * loginButtonType.addEventFilter(ActionEvent.ACTION, event -> { if
		 * (!validateAndStore()) { event.consume(); } });
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
		  if (usernamePassword.getKey().trim().equals("")) {
		  } else {
			 System.out.println(
				  "Username=" + usernamePassword.getKey() + ", Password=" + usernamePassword.getValue());
			 playerList.addPlayer(new Player(usernamePassword.getKey(), usernamePassword.getValue(), false));
		  }
		});

	 }
  }

  //Very important mechanism for song Queueing to work
  public static void removeSongQueueTitle() {
	 // TODO Auto-generated method stub
	 songQueueTitle.remove(0);
	 if (songQueueTitle.isEmpty()) {
		songQueue = new SongQueue();
	 }
  }
}
