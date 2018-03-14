package model;

public class Player {

  private String ID;
  private String password;
  private int numberOfSongChoices;
  private boolean admin;
  private Adjuster adjuster;
  
  public Player(String id, String password, boolean admin) {
	 this.ID = id;
	 this.password = password;
	 this.numberOfSongChoices = 3;
	 this.admin = admin;
	 adjuster = new Adjuster(3);
  }
  
  private void decrementSongChoices() {
	  numberOfSongChoices--;
  }
  
  public String getName() {
	  return this.ID;
  }
  
}// end Player
