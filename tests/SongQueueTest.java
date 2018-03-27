package tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

import model.SongCollection;
import model.SongQueue;

public class SongQueueTest {

  @Test
	public void testSongQueue() {
		SongQueue songQueue = new SongQueue();
		SongCollection songCollection = new SongCollection();
		songQueue.addSongtoQueue(songCollection.getSong("Pokemon Capture"));
		songQueue.getSongQueue();
  }
}
