package tests;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

import model.Song;
import model.SongCollection;
import model.TimeObj;

public class SongCollectionTest {

  @Test
  public void testSongCollectionList() {
	 SongCollection sg = new SongCollection();
	 sg.addSong(new Song("Pokemon", new TimeObj(0, 0, 5), "Pikachu", "Poke.mp3"));
	 sg.defaultSongCollection();
	 
	 for (Song s : sg.getSongCollection()) {
		System.out.printf(" "+s.getTitle());
	 }
	 System.out.printf("\n");
	 
	 assertNull(sg.getSong("Blah"));
	 assertTrue(sg.getSong("Pokemon").getTitle().equals("Pokemon"));
	 assertTrue(sg.getSong("Loping Sting").getTitle().equals("Loping Sting"));
	 
  }
}
