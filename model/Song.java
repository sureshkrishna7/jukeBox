package model;

import java.io.Serializable;

// Added to allow package model to exist on GitHub
@SuppressWarnings("serial")
public class Song implements Serializable {
  
  private String title;
  private TimeObj songLength;
  private int playCount;
  private String artist;
  private String songFile;
  private Adjuster adjuster;
  
  //Constructor one with 4 parameters
  public Song(String tit, TimeObj len, String art, String file) {
	 this.title = tit;
	 this.songLength = len;
	 this.artist = art;
	 this.songFile = "songfiles/"+file;
	 this.adjuster = new Adjuster(3);
	 this.playCount = 0;
  }
  
  public boolean useSongToday() {
	 return adjuster.use();
  }
  
  public boolean canUseSongToday() {
	 return adjuster.canUse();
  }
  
  public int getPlayCount() {
	  playCount = adjuster.getCountUsed();
	  return playCount;
  }
  public String getTitle() {
	 return title;
  }
  
  public String getSongLength() {
	 return songLength.getTimeAsString();
  }
  
  public int getSongLengthSec() {
	 return songLength.entireTimeInSeconds();
  }
  
  public String getArtist() {
	 return artist;
  }
  
  public String getSongFile() {
	 return songFile;
  }
 
}