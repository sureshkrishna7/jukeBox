package model;

import java.io.File;
import java.io.IOException;
import java.sql.Time;
import java.util.Map;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;

// Added to allow package model to exist on GitHub
public class Song {
  
  private String title;
  private Time songLength;
  private String artist;
  private File songFile;
  
  //Constructor one with 4 parameters
  public Song(String tit, Long len, String art, String file) {
	 this.title = tit;
	 this.songLength.setTime(len);
	 this.artist = art;
	 this.songFile = new File("./songfiles"+file);
  }
  
  //constructor two with 3 parameters
  public Song(String tit, String art, String file) {
	 this.title = tit;
	 this.artist = art;
	 this.songFile = new File("./songfiles"+file);
	 
	 try {
		getDurationWithMp3Spi(songFile);
	 } catch (UnsupportedAudioFileException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	 } catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	 }
  }
  
  public String getSongTitle() {
	 return title;
  }
  
  public Time getSongLength() {
	 return songLength;
  }
  
  public String getSongArtist() {
	 return artist;
  }
  
  public File getSongFile() {
	 return songFile;
  }
  
  //gets the time of the song
  private void getDurationWithMp3Spi(File file) throws UnsupportedAudioFileException, IOException {

    AudioFileFormat fileFormat = AudioSystem.getAudioFileFormat(file);
    if (fileFormat instanceof AudioFileFormat) {
        Map<?, ?> properties = ((AudioFileFormat) fileFormat).properties();
        String key = "duration";
        Long microseconds = (Long) properties.get(key);
        int mili = (int) (microseconds / 1000);
        int sec = (mili / 1000) % 60;
        int min = (mili / 1000) / 60;
        System.out.println("time = " + min + ":" + sec);
        songLength.setTime(microseconds);
    } else {
        throw new UnsupportedAudioFileException();
    }

}
}