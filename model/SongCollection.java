package model;

import java.io.Serializable;
import java.util.ArrayList;


@SuppressWarnings("serial")
public class SongCollection implements Serializable {

  private ArrayList<Song> songCollection;

  public SongCollection() {
	 songCollection = new ArrayList<Song>(9);
	 //defaultSongCollection();
  }
  
  public void addSong(Song song) {
	 // TODO Auto-generated method stub
	 songCollection.add(song);
  }

  public void defaultSongCollection() {
	 songCollection.add(new Song("Pokemon Capture", new TimeObj(0, 0, 5), "Pikachu", "Capture.mp3"));
	 songCollection.add(new Song("Danse Macabre", new TimeObj(0, 0, 34), "Kevin MacLeod", "DanseMacabreViolinHook.mp3"));
	 songCollection.add(new Song("Determined Tumbao", new TimeObj(0, 0,20), "FreePlay Music", "DeterminedTumbao.mp3"));
	 songCollection.add(new Song("Loping Sting", new TimeObj(0, 0,5), "Kevin MacLeod", "LopingSting.mp3"));
	 songCollection.add(new Song("Swing Cheese", new TimeObj(0, 0,15), "FreePlay Music", "SwingCheese.mp3"));
	 songCollection.add(new Song("The Curtain Rises", new TimeObj(0, 0,28), "Kevin MacLeod", "TheCurtainRises.mp3"));
	 songCollection.add(new Song("Untameable Fire", new TimeObj(0, 4, 42), "Pierre Langer", "UntameableFire.mp3"));
	 songCollection.add(new Song("Is it Love", new TimeObj(0, 0, 2), "3LAU", "IsItLove.mp3"));
	 songCollection.add(new Song("Ice and Fire", new TimeObj(0, 0, 2), "Unknown", "IceFire.mp3"));
  }

  public ArrayList<Song> getSongCollection() {
	 return this.songCollection;
  }

  public Song getSong(String songTitle) {
	 
	 for(Song entry : this.getSongCollection()){
		
		if(entry.getTitle().equalsIgnoreCase(songTitle)) {
		  return entry;
		}
	 }
	 return null;
  }
  
}
