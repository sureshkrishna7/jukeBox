package model;

import javafx.scene.control.TableColumn;
import java.util.Map;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class SongViewer extends TableView<Song> {

	TableColumn<Song, String> playCountCol;
	TableColumn<Song, String> titleCol;
	TableColumn<Song, String> artistCol;
	TableColumn<Song, String> timeCol;
	
	@SuppressWarnings("unchecked")
	public SongViewer() {
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
	
	/*public void updatePlayCount() {
		playCountCol.setCellValueFactory(new PropertyValueFactory<Song, String>("playCount"));
		this.getColumns().addAll(playCountCol, titleCol, artistCol, timeCol);
	}*/
}
