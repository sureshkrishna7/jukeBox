package model;

import java.time.LocalDate;

public class Adjuster {

  //TODO: Add any needed instance variables

  private int timesToUse;
  private int countUsed;
  private LocalDate today;


  private int originalTimesToUse;

  /**
   * How often this object will allow its use.
   * 
   * @return The maximum number of usages (how often use can b called
   */
  public Adjuster(int timesToUse1) {
	 // TODO: Complete this method
	 timesToUse = timesToUse1;
	 originalTimesToUse = timesToUse1;
	 countUsed = 0;

	 //Current Date
	 today = LocalDate.now();
	 //System.out.println("Current Date="+today);
  }

  /**
   * Getter to let users now how often use() can be called today.
   * @return The number of times use() can be called in a a calendar date
   */
  public int maxTimesForUse() {
	 // TODO: Complete this method
	 return timesToUse;
  }

  /**
   * Use this to know if the max usage times has been reached
   * 
   * @return True if use can be called again
   */
  public boolean canUse() {
	 // TODO: Complete this method
	 return (timesToUse > 0); 
  }

  /**
   * Use this object once if possible.
   * 
   * @return true if the max usage has not been reached and record a usage on
   *         the current date. Return false if the max usages has been reached
   */
  public boolean use() {
	 // TODO: Complete this method
	 // 
	 if(((timesToUse - 1) >= 0) && today.isEqual(LocalDate.now())) {
		timesToUse = timesToUse - 1;
		countUsed = countUsed + 1;
		return true;
	 }
	 return false; 
  }

  /**
   * Check to see How often has use() been called successfully
   * 
   * @return How often use() has been called.
   */
  public int timesUsed() {
	 // TODO: Complete this method
	 return countUsed;
  }

  /**
   *  Add this method for easy testing, or wait until midnight.
   *  Do NOT change your system clock!
   */
  public void pretendItIsTomorrow() {
	 // TODO: Complete this method
	 timesToUse = originalTimesToUse;
	 today = LocalDate.now();
	 countUsed = 0;
  }
}
