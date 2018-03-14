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
	 Song a = new Song("Pokemon Capture", convertSecondsToMicro(5), "Pikachu", "Capture.mp3");
	 Song b = new Song("Danse Macabre", convertSecondsToMicro(34), "Kevin MacLeod", "DanseMacabreViolinHook.mp3");
	 Song c = new Song("Determined Tumbao", convertSecondsToMicro(20), "FreePlay Music", "DeterminedTumbao.mp3");
	 Song d = new Song("Loping Sting", convertSecondsToMicro(5), "Kevin MacLeod", "LopingSting.mp3");
	 Song e = new Song("Swing Cheese", convertSecondsToMicro(15), "FreePlay Music", "SwingCheese.mp3");
	 Song f = new Song("The Curtain Rises", convertSecondsToMicro(28), "Kevin MacLeod", "TheCurtainRises.mp3");
	 Song g = new Song("Untameable Fire", convertSecondsToMicro(4*60+42), "Pierre Langer", "UntameableFire.mp3");
	 
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
   
  private long convertSecondsToMicro(long sec) {
	 return sec * 1000000;
  }
}
