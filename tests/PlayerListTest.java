package tests;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import model.Player;
import model.PlayerList;


public class PlayerListTest {

  @Test
	public void testPlayerList() {
		PlayerList playerList = new PlayerList();
		playerList.setUpDefault();
		Player p = new Player("Suresh", "1", false);
		playerList.addPlayer(p);
		
		assertTrue(playerList.getPlayer("Suresh").getName().equals("Suresh"));
		assertNull(playerList.getPlayer("Sam"));
		
		for(Player pl : playerList.getList()) {
		  System.out.printf(" "+pl.getName());
		}
		System.out.printf("\n");
		
		for(String nam : playerList.getIdList()) {
		  System.out.printf(" "+nam);
		}
		System.out.printf("\n");
		
	}
}
