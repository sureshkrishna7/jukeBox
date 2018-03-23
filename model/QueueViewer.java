package model;

import java.util.Queue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class QueueViewer extends TableView<Song>{
	
	Queue<Song> queue;
	
	@SuppressWarnings("unchecked")
	public QueueViewer(Queue<Song> queue2) {
		this.queue = queue2;
		TableColumn<Song, String> songCol = new TableColumn<>("Queue");
		songCol.setCellValueFactory(new PropertyValueFactory<Song, String>("title"));
		this.getColumns().addAll(songCol);
		songCol.setPrefWidth(200);
		this.setMaxWidth(220);
		setObserverList();
	}//end constructor

	private void setObserverList() {
		ObservableList<Song> songs = FXCollections.observableArrayList();
		for(Song s : this.queue) {
			songs.add(s);
		}
		this.setItems(songs);
		this.getSelectionModel().select(0);
	}// end setObserverList

}
