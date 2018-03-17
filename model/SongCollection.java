package model;

import java.util.HashMap;

public class SongCollection {
  
  HashMap<String, Song> songCollection;
  
  public SongCollection() {
	 songCollection = new HashMap<>();
	 dummySongs();
  }
  
  private void dummySongs() {
	 Song a = new Song("Pokemon Capture", new TimeObj(0, 0, 1), "Pikachu", "Capture.mp3");
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
  
}
