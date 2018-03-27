package tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
//Added to allow package tests to exist on GitHub
import static org.junit.Assert.fail;

import org.junit.Test;

import model.Song;
import model.TimeObj;

public class SongTest {

  @Test
  public void testSong1() {
     Song s = new Song("Hello", new TimeObj(123), "art", "fff.txt");
     assertTrue(s.useSongToday());
     assertTrue(s.canUseSongToday());
     assertTrue(s.getPlayCount() == 1);
     assertFalse(s.getTitle().equals("Goodbye"));
  }
  
  @Test
  public void testSong2() {
	  Song s = new Song("Blue Blood", new TimeObj(4,1,2), "art", "ggg.txt");
	  Song s2 = new Song("Ack", new TimeObj(15), "art", "asdf.txt");
	  assertTrue(s.getSongLength().equals("04:01:02"));
	  assertTrue(s2.getSongLengthSec() == 15);
	  assertTrue(s2.getArtist().equals("art"));
	  assertFalse(s2.getSongFile().equals("1"));
  }
}