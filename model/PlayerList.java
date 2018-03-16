package model;

import java.util.ArrayList;

public class PlayerList {
	
	ArrayList<Player> listOfPlayers;
	ArrayList<String> idList;
	
	/*
	 * PlayerList() -- constructor for the class, instantiates ArrayList of Player objects
	 * of size 5, hard codes Players.
	 */
	public PlayerList() {
		listOfPlayers = new ArrayList<Player>(5);
		idList = new ArrayList<String>(5);
		listOfPlayers.add(new Player("Chris", "1", false));
		listOfPlayers.add(new Player("Devon", "22", false));
		listOfPlayers.add(new Player("River", "333", false));
		listOfPlayers.add(new Player("Ryan", "4444", false));
		listOfPlayers.add(new Player("Merlin", "7777777", true));
		for(Player p: listOfPlayers) {
			idList.add(p.getName());
		}
	}// end constructor
	
	public Player getPlayer(String id) {
		for(Player p: listOfPlayers) {
			if(p.getName().equals(id)) {
				return p;
			}
		}
		return null;
	}
	
	public ArrayList<Player> getList(){
		return this.listOfPlayers;
	}
	
	public ArrayList<String> getIdList(){
		
		return idList;
	}
	
	
	
}// end PlayerList
