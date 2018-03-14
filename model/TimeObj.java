package model;

public class TimeObj {

  private int hour;
  private int min;
  private int sec;

  public TimeObj(int hour, int min, int sec) {
	 /*
	  * hour from 0 to 23
	  * min from 0 to 59
	  * sec from 0 to 59
	  */
	 this.hour = hour;
	 this.min = min;
	 this.sec = sec;
  }

  public int getHour() {
	 return this.hour;
  }

  public int getMin() {
	 return this.min;
  }

  public int getSec() {
	 return this.sec;
  }

  public String getTimeString() {
	 String temp = "";

	 String hourStr = (hour < 10 ? "0" : "") + hour;
	 String minStr = (min < 10 ? "0" : "") + min;
	 String secStr = (sec < 10 ? "0" : "") + sec;

	 temp = ""+hourStr+":"+minStr+":"+secStr;
	 return temp;
  }

}
