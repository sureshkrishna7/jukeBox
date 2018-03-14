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
  
  private boolean useASong() {
	  return numberOfSongChoices.use();
  }
  
  private int howManySongsLeft() {
	 return numberOfSongChoices.maxTimesForUse();
  }
  
  private int howManySongsPlayed() {
	 return numberOfSongChoices.timesUsed();
  }
  
  private boolean canPlayASong() {
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
