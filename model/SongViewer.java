package model;

import javafx.scene.control.TableColumn;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class SongViewer extends TableView<Song> implements Serializable {

  private TableColumn<Song, String> playCountCol;
  private TableColumn<Song, String> titleCol;
  private TableColumn<Song, String> artistCol;
  private TableColumn<Song, String> timeCol;
  private SongCollection songList;
  private ObservableList<Song> songs;
  
  @SuppressWarnings("unchecked")
  public SongViewer(SongCollection songCollection) {
	 songList = songCollection;
	 songs = FXCollections.observableArrayList();
	 playCountCol = new TableColumn<>("Plays");
	 titleCol = new TableColumn<>("Title");
	 artistCol = new TableColumn<>("Artist");
	 timeCol = new TableColumn<>("Time");

	 playCountCol.setCellValueFactory(new PropertyValueFactory<Song, String>("playCount"));
	 titleCol.setCellValueFactory(new PropertyValueFactory<Song, String>("title"));
	 artistCol.setCellValueFactory(new PropertyValueFactory<Song, String>("artist"));
	 timeCol.setCellValueFactory(new PropertyValueFactory<Song, String>("songLength"));

	 this.getColumns().addAll(playCountCol, titleCol, artistCol, timeCol);

	 playCountCol.setPrefWidth(50);
	 titleCol.setPrefWidth(200);
	 artistCol.setPrefWidth(150);
	 timeCol.setPrefWidth(100);
	 this.setMaxWidth(520);

	 //System.out.println("Song List = "+songList.getSongCollection().toString());
	 for(Song entry : songList.getSongCollection()){
		songs.add(entry);
	 }
	 this.setItems(songs);
	 this.getSelectionModel().select(0);
  }
  

  public void updatePlayCount() {
	 songs.removeAll(songs);
	 for(Song entry : songList.getSongCollection()){
		songs.add(entry);
	 }
  }

}
