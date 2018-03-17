package model;

// Added to allow package model to exist on GitHub
public class Song {
  
  private String title;
  private TimeObj songLength;
  private String artist;
  private String songFile;
  private Adjuster allowedPlays;
  
  //Constructor one with 4 parameters
  public Song(String tit, TimeObj len, String art, String file) {
	 this.title = tit;
	 this.songLength = len;
	 this.artist = art;
	 this.songFile = "songfiles/"+file;
	 this.allowedPlays = new Adjuster(3);
  }
  
  public boolean useSongToday() {
	 return allowedPlays.use();
  }
  
  public String getSongTitle() {
	 return title;
  }
  
  public String getSongLength() {
	 return songLength.getTimeAsString();
  }
  
  public int getSongLengthSec() {
	 return songLength.entireTimeInSeconds();
  }
  
  public String getSongArtist() {
	 return artist;
  }
  
  public String getSongFile() {
	 return songFile;
  }
 
}