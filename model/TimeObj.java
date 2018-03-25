package model;

import java.io.Serializable;

public class TimeObj implements Serializable {

  private int hour;
  private int min;
  private int sec;
  private int timeInSeconds;

  //constructor with hour min and sec parameters
  public TimeObj(int hour, int min, int sec) {
	 /*
	  * hour from 0 to 23 but no check on it except to see if it's non negative
	  * min from 0 to 59
	  * sec from 0 to 60
	  */
	 if( (min >= 0 && min <= 59) && (sec >= 0 && sec <= 60) && (hour >= 0)) {
		this.hour = hour;
		this.min = min;
		this.sec = sec;
		this.timeInSeconds = convertTimeToSeconds(hour, min, sec);
	 }
  }

  //constructor with only seconds parameter
  public TimeObj(int sec) {
	 /*
	  * hour from 0 to 23 but no check on it except to see if it's non negative
	  * min from 0 to 59
	  * sec from 0 to 60
	  */
	 if(sec >= 0) {
		int time[] = convertSecondsToTime(sec);
		this.hour = time[0];
		this.min = time[1];
		this.sec = time[2];
		this.timeInSeconds = sec;
	 }
  }

  // Determines if a user can play song with his remaining time
  public boolean canSubtractTimeBySeconds(int seconds) {
	 if(timeInSeconds >= seconds) {
		return true;
	 }
	 return false;
  }

  public int getHour() {
	 return this.hour;
  }

  public int getMinutes() {
	 return this.min;
  }

  public int getSeconds() {
	 return this.sec;
  }

  public int entireTimeInSeconds() {
	 return this.timeInSeconds;
  }

  public String getTimeAsString() {
	 String temp = "";

	 String hourStr = (hour < 10 ? "0" : "") + hour;
	 String minStr = (min < 10 ? "0" : "") + min;
	 String secStr = (sec < 10 ? "0" : "") + sec;

	 temp = ""+hourStr+":"+minStr+":"+secStr;
	 return temp;
  }

  public void subtractTimeBySeconds(int seconds) {
	 if(canSubtractTimeBySeconds(seconds)) {
		timeInSeconds = timeInSeconds - seconds;
		updateTime(timeInSeconds);
	 }
  }

  public void subtractTimeByMinutes(int min) {
	 if(canSubtractTimeBySeconds(min*60)) {
		timeInSeconds = timeInSeconds - (min*60);
		updateTime(timeInSeconds);
	 }
  }

  public void subtractTimeByHours(int hour) {
	 if(canSubtractTimeBySeconds(hour*3600)) {
		timeInSeconds = timeInSeconds - (hour*3600);
		updateTime(timeInSeconds);
	 }
  }

  private void updateTime(int timeInSeconds) {
	 // TODO Auto-generated method stub
	 int time[] = convertSecondsToTime(timeInSeconds);

	 this.hour = time[0];
	 this.min = time[1];
	 this.sec = time[2];
	 this.timeInSeconds = timeInSeconds;
  }

  public int convertTimeToSeconds(int hour, int minutes, int seconds) {

	 return (hour*3600)+(minutes*60)+(seconds);
  }

  public int[] convertSecondsToTime(int seconds) {

	 int hour = seconds / 3600;
	 int remainingSec = seconds % 3600;
	 int min = remainingSec / 60;
	 remainingSec = remainingSec % 60;
	 int sec = remainingSec; 				// Hopefully remainingSec here is less than or equal to 60

	 int time[] = new int[3];
	 time[0] = hour;
	 time[1] = min;
	 time[2] = sec;

	 return time;
  }

}
