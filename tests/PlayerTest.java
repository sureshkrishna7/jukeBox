package tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.time.LocalDate;
import org.junit.Test;
import model.Player;

public class PlayerTest {

	@Test
	public void testAdjusterGetters() {
		Player p = new Player("abc", "123");
		assertTrue(p.getAdjuster().maxTimesForUse() == 3);
		assertTrue(p.getAdjuster().getCountUsed() == 0);
	}

	@Test
	public void testAdjusterMaxUsesMet() {
		Player p = new Player("abc", "123");
		p.useSong();
		p.useSong();
		p.useSong();
		assertFalse(p.getAdjuster().canUse());
	}

	@Test
	public void testAdjusterPretendItIsTomorrow1() {
		Player p = new Player("abc", "123");
		p.useSong();
		p.getAdjuster().pretendItIsTomorrow();
		assertTrue(p.getAdjuster().getCountUsed() == 0);
	}

	@Test
	public void testAdjusterPretendItIsTomorrow2() {
		Player p = new Player("abc", "123");
		p.useSong();
		p.useSong();
		p.useSong();
		assertFalse(p.getAdjuster().canUse());
		p.getAdjuster().pretendItIsTomorrow();
		assertTrue(p.getAdjuster().canUse());
	}

	@Test
	public void testAdjusterPretendItIsTomorrow3() {
		Player p = new Player("abc", "123");
		p.getAdjuster().pretendItIsTomorrow();
		assertTrue(p.getAdjuster().getInitDate().getDayOfYear() == LocalDate.now().plusDays(1).getDayOfYear());
	}

	@Test
	public void testPlayerIsAdmin() {
		Player p = new Player("abc", "123");
		assertFalse(p.isAdmin());
		Player p2 = new Player("Gravy", "bluebooks", true);
		assertTrue(p2.isAdmin());
	}

	@Test
	public void testSongCollection1() {
		Player p = new Player("eggs", "beni");
		assertTrue(p.time().getHour() == 25);
	}
}