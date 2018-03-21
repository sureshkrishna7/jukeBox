package model;

import javafx.scene.control.TableColumn;
import java.util.Map;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class SongViewer extends TableView<Song> {

	@SuppressWarnings("unchecked")
	public SongViewer() {
		TableColumn<Song, String> playCountCol = new TableColumn<>("Plays");
		TableColumn<Song, String> titleCol = new TableColumn<>("Title");
		TableColumn<Song, String> artistCol = new TableColumn<>("Artist");
		TableColumn<Song, String> timeCol = new TableColumn<>("Time");
		
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

	    setObserverList();
	}

	public void setObserverList() {
		SongCollection songList = new SongCollection();
		ObservableList<Song> songs = FXCollections.observableArrayList();
		for(Map.Entry<String, Song> entry : songList.getSongCollection().entrySet()){
			songs.add(entry.getValue());
		}
		this.setItems(songs);
		this.getSelectionModel().select(0);
	}
}
