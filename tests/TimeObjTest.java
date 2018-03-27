package tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import model.TimeObj;

public class TimeObjTest {

	@Test
	public void testTimeObj1() {
		TimeObj to = new TimeObj(3600);
		assertFalse(to.canSubtractTimeBySeconds(3601));
		assertTrue(to.canSubtractTimeBySeconds(10));
		assertTrue(to.getMinutes() == 0);
		assertTrue(to.entireTimeInSeconds() == 3600);
	}
	
	@Test
	public void testTimeObj2() {
		TimeObj to = new TimeObj(4,36,12);
		TimeObj to2 = new TimeObj(12,1,1);
		assertTrue(to.getHour() == 4);
		assertFalse(to.getMinutes() == 0);
		assertTrue(to2.getTimeAsString().equals("12:01:01"));
		assertTrue(to.getTimeAsString().equals("04:36:12"));
	}
	
	@Test
	public void testTimeObj3() {
		TimeObj to = new TimeObj(12,10,49);
		assertTrue(to.getSeconds() == 49);
		to.subtractTimeBySeconds(19);
		assertFalse(to.getSeconds() == 49);
		to.subtractTimeBySeconds(100);
		to.subtractTimeByMinutes(10002022);
		to.subtractTimeByMinutes(1);
		assertTrue(to.getMinutes() == 7);
		to.subtractTimeByHours(9999);
		to.subtractTimeByHours(4);
		assertTrue(to.getHour() == 8);
		to.subtractTimeBySeconds(999999999);
	}
}
