package model;

import java.util.ArrayList;

public class PlayerList {
	
	ArrayList<Player> listOfPlayers;
	
	/*
	 * PlayerList() -- constructor for the class, instantiates ArrayList of Player objects
	 * of size 5, hard codes Players.
	 */
	public PlayerList() {
		listOfPlayers = new ArrayList<Player>(5);
		listOfPlayers.add(new Player("Chris", "1", 3));
		listOfPlayers.add(new Player("Devon", "22", 3));
		listOfPlayers.add(new Player("River", "333", 3));
		listOfPlayers.add(new Player("Ryan", "4444", 3));
		listOfPlayers.add(new Player("Merlin", "7777777", 3));
		
	}// end constructor
	
}
