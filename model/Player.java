package model;

public class Player {

  private String ID;
  private String name;
  private int numberOfSongChoices;
  
  public Player(String id, String user, int choices) {
	 this.ID = id;
	 this.name = user;
	 this.numberOfSongChoices = choices;
  }
  
}
