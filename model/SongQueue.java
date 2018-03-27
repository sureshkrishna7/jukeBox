package model;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.Serializable;
import java.net.URI;
import java.util.ArrayList;

import javax.swing.AbstractAction;

import controller_view.JukeBoxIter2;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class SongQueue implements Serializable{
  private ArrayList<Song> songQueue;
  private boolean jukeBoxStartQueue;
  private Song currentSong;
  private MediaPlayer mediaPlayer;
  private Media media;

  public SongQueue() {
	 songQueue = new ArrayList<Song>(9);
	 jukeBoxStartQueue = true;
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
	 //else {
		//setCurrentSong(song1);
	 //}
	 return;
  }

  private void playSong(Song song) {
	 // TODO Auto-generated method stub

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
		  //return;
		}
	 });
  }

  public ArrayList<Song> getSongQueue() {
	 return songQueue;
  }
  
  public MediaPlayer getPlayer() {
	  return mediaPlayer;
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
