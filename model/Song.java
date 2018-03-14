package model;

import java.io.File;


// Added to allow package model to exist on GitHub
public class Song {
  
  private String title;
  private TimeObj songLength;
  private String artist;
  private File songFile;
  
  //Constructor one with 4 parameters
  public Song(String tit, TimeObj len, String art, String file) {
	 this.title = tit;
	 this.songLength = len;
	 this.artist = art;
	 this.songFile = new File("./songfiles"+file);
  }
  
  public String getSongTitle() {
	 return title;
  }
  
  public String getSongLength() {
	 return songLength.getTimeString();
  }
  
  public String getSongArtist() {
	 return artist;
  }
  
  public File getSongFile() {
	 return songFile;
  }
 
}