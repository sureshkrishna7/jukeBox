package controller_view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
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
	 BorderPane all = new BorderPane();
    all.setCenter(new Label("A Functional Spike"));
    
    Scene scene = new Scene(all, 300, 100);
    primaryStage.setScene(scene);
    primaryStage.show();
  }

}
