package model;

import java.io.File;
import java.net.URI;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

import controller_view.JukeBoxIter2;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class SongCollection {

  private HashMap<String, Song> songCollection;
  private ObservableList<Song> songQueue;
  private boolean jukeBoxStartQueue;
  private Song currentSong;
  private MediaPlayer mediaPlayer;
  private Media media;

  public SongCollection() {
	 songCollection = new HashMap<>();
	 songQueue = FXCollections.observableArrayList();
	 jukeBoxStartQueue = true;
	 dummySongs();
  }

  private void dummySongs() {
	 Song a = new Song("Pokemon Capture", new TimeObj(0, 0, 5), "Pikachu", "Capture.mp3");
	 Song b = new Song("Danse Macabre", new TimeObj(0, 0, 34), "Kevin MacLeod", "DanseMacabreViolinHook.mp3");
	 Song c = new Song("Determined Tumbao", new TimeObj(0, 0,20), "FreePlay Music", "DeterminedTumbao.mp3");
	 Song d = new Song("Loping Sting", new TimeObj(0, 0,5), "Kevin MacLeod", "LopingSting.mp3");
	 Song e = new Song("Swing Cheese", new TimeObj(0, 0,15), "FreePlay Music", "SwingCheese.mp3");
	 Song f = new Song("The Curtain Rises", new TimeObj(0, 0,28), "Kevin MacLeod", "TheCurtainRises.mp3");
	 Song g = new Song("Untameable Fire", new TimeObj(0, 4, 42), "Pierre Langer", "UntameableFire.mp3");
	 Song h = new Song("Is it Love", new TimeObj(0, 0, 2), "3LAU", "IsItLove.mp3");
	 Song i = new Song("Ice and Fire", new TimeObj(0, 0, 2), "Unknown", "IceFire.mp3");

	 songCollection.put("a", a);
	 songCollection.put("b", b);
	 songCollection.put("c", c);
	 songCollection.put("d", d);
	 songCollection.put("e", e);
	 songCollection.put("f", f);
	 songCollection.put("g", g);
	 songCollection.put("h", h);
	 songCollection.put("i", i);
  }

  public HashMap<String, Song> getSongCollection() {
	 return this.songCollection;
  }

  public void addSongtoQueue(Song song1) {	

	 songQueue.add(song1);

	 /*String path = song1.getSongFile();
	 System.out.println("Song path = "+path);
	 // Need a File and URI object so the path works on all OSs
	 File file = new File(path);
	 URI uri = file.toURI();
	 // Play one mp3 and and have code run when the song ends
	 media = new Media(uri.toString());
	 mediaPlayer = new MediaPlayer(media);*/

	 //System.out.println("SongQueue = "+songQueue.toString());
	 Song firstSong = null;
	 if(jukeBoxStartQueue) {
		firstSong = songQueue.remove(0);
		System.out.println("SongQueue 1= "+songQueue.toString());
		setCurrentSong(firstSong);
		playSong(currentSong);
		jukeBoxStartQueue = false;
	 }
	 else {
		setCurrentSong(song1);
	 }
	 return;
  }

  private void playSong(Song song) {
	 // TODO Auto-generated method stub
	 //mediaPlayer.setOnEndOfMedia(null);

	 mediaPlayer.play(); 

	 mediaPlayer.setOnEndOfMedia(new Runnable() {
		@Override
		public void run() {
		  JukeBoxIter2.removeSongQueueTitle();
		  mediaPlayer.stop();
		  mediaPlayer = new MediaPlayer(media);
		  if (songQueue.size() > 0) {
			 //Plays the subsequent song files
			 Song nextSong = songQueue.remove(0);
			 System.out.println("SongQueue 2= "+songQueue.toString());
			 setCurrentSong(nextSong);
			 playSong(currentSong);
		  }
		  return;
		}
	 });
  }

  public ObservableList<Song> getSongQueue() {
	 return songQueue;
  }

  public void setCurrentSong(Song newSong) {
	 String path = newSong.getSongFile();
	 System.out.println("Song path = "+path);
	 // Need a File and URI object so the path works on all OSs
	 File file = new File(path);
	 URI uri = file.toURI();
	 // Play one mp3 and and have code run when the song ends
	 media = new Media(uri.toString());
	 mediaPlayer = new MediaPlayer(media);
	 currentSong = newSong;
  }

  public void removeSongFromQueue() {
	 songQueue.remove(0);
  }
}
