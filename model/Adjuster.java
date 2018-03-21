package model;

import java.time.LocalDate;
/*
 * Class: Adjuster
 * Authors: Suresh Krishna and Andrew Lane
 * Purpose: The class is a that will keep track of song choices, an instance of it will be present in the Player class.
 */
public class Adjuster {

  private int maxUses;
  private int countUsed;
  private LocalDate today;
  
  /*
   * Adjuster(int) -- constructor. Sets int param to maxUses. Sets countUsed to zero and sets the
   * date to today, the day of construction.
   */
  public Adjuster(int timesToUse) {
	 this.maxUses = timesToUse;
	 //originalTimesToUse = timesToUse1;
	 this.countUsed = 0;

	 //Current Date
	 today = LocalDate.now();
  }

  /*
   * maxTimesForUse() -- getter for maxUses
   */
  public int maxTimesForUse() {
	 return maxUses;
  }
  
  public LocalDate getInitDate() {
	  return today;
  }

  /*
   * canUse() -- returns boolean false if maxUses has been reached, true otherwise
   */
  public boolean canUse() {
	 if(countUsed == maxUses) return false;
	 return true;
  }

  /*
   * use() -- checks the date, if it's a day or more past construction, then countUsed is set to 1
   * and the new date object is set to the current date. Checks if canUse, increments countUsed if true
   * and returns true, if not, it returns false.
   */
  public boolean use() {
	 
	  if(!today.isEqual(LocalDate.now())) {
		  countUsed = 0;
		  today = LocalDate.now();
	  }
	  if(canUse()) {
		  countUsed++;
		  return true;
	  }
	  return false;
  }

  /*
   * getCountUsed() -- getter for countUsed variable
   */
  public int getCountUsed() {
	 return countUsed;
  }

  /*
   * pretendItIsTomorrow() -- this method sets the date to one day in the future and resets the number
   * of uses, used for testing purposes.
   */
  public void pretendItIsTomorrow() {
	 today = today.plusDays(1);
	 countUsed = 0;
  }
}
