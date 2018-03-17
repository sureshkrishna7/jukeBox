package model;


public class Player {

  private String ID;
  private String password;
  //private int numberOfSongChoices;
  private boolean admin;
  private TimeObj remainingSongTime; //1500 minutes of player song time
  private Adjuster numberOfSongChoices;
  
  public Player(String id, String password) {
	 this.ID = id;
	 this.password = password;
	 this.admin = false;
	 numberOfSongChoices = new Adjuster(3);
	 remainingSongTime = new TimeObj(25, 0, 0);
  }
  
  public Player(String id, String password, boolean admin) {
	 this.ID = id;
	 this.password = password;
	 this.admin = admin;
	 numberOfSongChoices = new Adjuster(3);
	 remainingSongTime = new TimeObj(25, 0, 0);
  }
  
  public boolean useSong() {
	  return numberOfSongChoices.use();
  }
  
  public int remainingSongs() {
	 return numberOfSongChoices.maxTimesForUse();
  }
  
  public int songsPlayed() {
	 return numberOfSongChoices.timesUsed();
  }
  
  public boolean canPlaySong() {
	 return numberOfSongChoices.canUse();
  }
  
  public TimeObj getTimeObj() {
	 return remainingSongTime;
  }
  
  public String getName() {
	  return this.ID;
  }
  
  public boolean checkCredential(String name, String pass) {
	
	 if(name.equals(ID)) {
		if(pass.equals(password)) {
		  return true;
		}
	 }
	 return false;
  }
  
}// end Player
