package model;

import java.util.LinkedList;
import java.util.List;

public class SongCollection {
  
  List<Song> songCollection;
  
  public SongCollection() {
	 songCollection = new LinkedList<Song>();
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
	 
	 songCollection.add(a);
	 songCollection.add(b);
	 songCollection.add(c);
	 songCollection.add(d);
	 songCollection.add(e);
	 songCollection.add(f);
	 songCollection.add(g);
  }
  
  public List<Song> getSongCollection() {
	 return this.songCollection;
  }
  
}
