package model;

public class Player {

  private String ID;
  private String password;
  //private int numberOfSongChoices;
  private boolean admin;
  private Adjuster numberOfSongChoices;
  
  public Player(String id, String password, boolean admin) {
	 this.ID = id;
	 this.password = password;
	 this.admin = admin;
	 numberOfSongChoices = new Adjuster(3);
  }
  
  public boolean useASong() {
	  return numberOfSongChoices.use();
  }
  
  public int howManySongsLeft() {
	 return numberOfSongChoices.maxTimesForUse();
  }
  
  public int howManySongsPlayed() {
	 return numberOfSongChoices.timesUsed();
  }
  
  public boolean canPlayASong() {
	 return numberOfSongChoices.canUse();
  }
  
  public String getName() {
	  return this.ID;
  }
  
  public boolean checkPassword(String name, String pass) {
	
	 if(name.equals(ID)) {
		if(pass.equals(password)) {
		  return true;
		}
	 }
	 return false;
  }
  
}// end Player
