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
	 Song a = new Song("Pokemon Capture", new timeObj(0, 0, 5), "Pikachu", "Capture.mp3");
	 Song b = new Song("Danse Macabre", new timeObj(0, 0, 34), "Kevin MacLeod", "DanseMacabreViolinHook.mp3");
	 Song c = new Song("Determined Tumbao", new timeObj(0, 0,20), "FreePlay Music", "DeterminedTumbao.mp3");
	 Song d = new Song("Loping Sting", new timeObj(0, 0,5), "Kevin MacLeod", "LopingSting.mp3");
	 Song e = new Song("Swing Cheese", new timeObj(0, 0,15), "FreePlay Music", "SwingCheese.mp3");
	 Song f = new Song("The Curtain Rises", new timeObj(0, 0,28), "Kevin MacLeod", "TheCurtainRises.mp3");
	 Song g = new Song("Untameable Fire", new timeObj(0, 4, 42), "Pierre Langer", "UntameableFire.mp3");
	 
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
   
  public class timeObj {
	 
	 private int hour;
	 private int min;
	 private int sec;
	 
	 public timeObj(int hour, int min, int sec) {
		/*
		 * hour from 0 to 23
		 * min from 0 to 59
		 * sec from 0 to 59
		 */
		this.hour = hour;
		this.min = min;
		this.sec = sec;
	 }
	 
	 public int getHour() {
		return this.hour;
	 }
	 
	 public int getMin() {
		return this.min;
	 }
	 
	 public int getSec() {
		return this.sec;
	 }
	 
	 public String getTimeString() {
		String temp = "";
		
		String hourStr = (hour < 10 ? "0" : "") + hour;
		String minStr = (min < 10 ? "0" : "") + min;
		String secStr = (sec < 10 ? "0" : "") + sec;
		
		temp = ""+hourStr+":"+minStr+":"+secStr;
		return temp;
	 }
  }
  
}
