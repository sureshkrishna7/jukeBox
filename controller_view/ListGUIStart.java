package controller_view;
/*
 * The app will show a graphical representation of a list by 
 * enclosing a List<String> inside an FXCollections.observableList,
 * inside a ListView, inside a BordPane, inside a Scene, inside a Stage.
 * 
 * Names, or any string, can be added to or removed from the model and the view. 
 */
import java.util.Optional;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ListGUIStart extends Application {

  public static void main(String[] args) {
	 launch(args);
  }

  private Button addButton, removeButton;
  private ObservableList<String> observableList;
  private ListView<String> listView;

  public void start(Stage stage) {
	 // TODO 1: Build an Observable list that takes a List argument
	 // When you add to or remove from observableList, the ListView, which
	 // is part of the GUI, will automatically update itself (see setItems).
	 observableList = FXCollections.observableArrayList();
	 listView = new ListView<String>(observableList);
	 
	 BorderPane pane = new BorderPane();
	 pane.setCenter(listView);

	 // Set up a small list before showing it in the ListView
	 observableList.add("Kim");
	 observableList.add("Chris");
	 observableList.add("Casey");
	 observableList.remove("Chris");

	 // TODO 2: Add two buttons to the top of the BorderPane
	 addButton = new Button("   Add   ");
	 removeButton = new Button("Remove");
	 GridPane topGrid = new GridPane();
	 topGrid.add(addButton, 0, 0);
	 topGrid.add(removeButton, 1, 0);
	 topGrid.setHgap(25); // horizontal gap in pixels => that's what you are asking for
	 topGrid.setVgap(10); // vertical gap in pixels
	 pane.setTop(topGrid);
	 BorderPane.setMargin(topGrid, new Insets(5, 20, 10, 20));

	 // Complete the private helper method below
	 registerButtonHandlers();

	 // The usual code needed to start an app
	 Scene scene = new Scene(pane, 200, 400);
	 stage.setScene(scene);
	 stage.show();
  }

  // Register handler code for the addButton and removeButton
  private void registerButtonHandlers() {

	 ButtonListener handler = new ButtonListener();

	 // TODO 3: When the user clicks the addButton, ask for a new name
	 // with a TextInputDialog.  Don't add the name if the length < 2
	 // "JT" is okay "", "I" is not okay
	 addButton.setOnAction(handler);


	 // TODO 4: When the user clicks the removeButton, 
	 // remove the name that is selected in the ListView
	 listView.getSelectionModel().getSelectedIndex();
	 removeButton.setOnAction(handler);

  }

  private class ButtonListener implements EventHandler<ActionEvent>{

	 @Override
	 public void handle(ActionEvent arg) {
		Button buttonClicked = (Button) arg.getSource();
		if(buttonClicked.getText().equals("   Add   ")){
		  addNewUser();	
		}

		if(buttonClicked.getText().equals("Remove")){
		  removeUser();
		}

	 }
  }
  
  
  private void removeUser() {
	 if(listView.getSelectionModel().getSelectedIndex() == -1) {
		return;
	 }
	 observableList.remove(listView.getSelectionModel().getSelectedIndex());
  }
  
  private void addNewUser() {

	 // Create a custom dialog with two input fields
	 Dialog<String> dialog = new Dialog<>();
	 dialog.setTitle("Adding new Name");
	 dialog.setHeaderText("Enter the text");

	 // Set the button types
	 ButtonType loginButtonType = new ButtonType("Add new name", ButtonData.OK_DONE);
	 dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);
	 
	 // Create the Account Name and password labels and fields
	 GridPane grid = new GridPane();
	 grid.setHgap(10);
	 grid.setVgap(10);
	 grid.setPadding(new Insets(15, 10, 5, 10));

	 TextField username = new TextField();
	 username.setPromptText("Name length > 1");

	 grid.add(new Label("Name:"), 0, 0);
	 grid.add(username, 1, 0);

	 dialog.getDialogPane().setContent(grid);

	 // Convert the result to a username-password-pair when the Add user button is
	 // clicked.
	 // This is lambda instead of an instance of a new event handler: shorter code.
	 dialog.setResultConverter(dialogButton -> {
		if (dialogButton == loginButtonType) {
		  return (username.getText());
		}
		return null;
	 });
	 
	 Optional<String> result = dialog.showAndWait();

	 result.ifPresent(usernamePassword -> {
		if(username.getText().length() < 2) {
		}
		else {
		  observableList.add(username.getText());
		}
	 });
  }
  
}