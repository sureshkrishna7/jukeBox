package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;

import org.junit.Test;

import model.Adjuster;

public class AdjusterTest {

  @Test
	public void testAdjusterStuff() {
	
	 Adjuster aj = new Adjuster(3);
	 
	 assertEquals(aj.maxTimesForUse(),3);
	 System.out.println(""+aj.getInitDate());
	 System.out.println(""+LocalDate.now());
	 
	 assertTrue(aj.canUse());
	 assertTrue(aj.use());
	 assertTrue(aj.use());
	 assertTrue(aj.use());
	 
	 assertFalse(aj.use());
	 assertFalse(aj.canUse());
	 
	 assertEquals(aj.getCountUsed(),3);
	 assertFalse(aj.use());
	 assertFalse(aj.use());
	 assertFalse(aj.use());
	 
	 aj.pretendItIsTomorrow();
	 assertEquals(aj.maxTimesForUse(),3);
	 assertEquals(aj.getCountUsed(),0);
	 System.out.println(""+aj.getInitDate());
	 System.out.println(""+LocalDate.now());
	 
	 assertFalse(aj.getInitDate().equals(LocalDate.now()));
	 
	 assertTrue(aj.canUse());
	 assertTrue(aj.use());
	 assertTrue(aj.use());
	 assertTrue(aj.use());
	 assertFalse(aj.use());
	 assertFalse(aj.canUse());
	 
	 aj.pretendItIsTomorrow();
	 assertTrue(aj.use());
	 assertTrue(aj.use());
	 assertTrue(aj.use());
	 assertFalse(aj.use());
	 assertFalse(aj.canUse());
	 
	 
  }
}
