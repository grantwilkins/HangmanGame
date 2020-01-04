//Gallow.java
//Grant Wilkins
//May 2018
//The music class simply plays music casually in the background
//while one is playing. This begins once the Hangman object is constructed
//at runtime.
import java.io.IOException;
import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Music 
{
	//Once a music object is constructed in the Hangman object constructor, then the 
	//music will begin to play
	public Music()
	{
		try {
	         // Open an audio input stream.
	         URL url = this.getClass().getClassLoader().getResource("krabs.wav");
	         
	         AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
	         // Get a sound clip resource.
	         Clip clip = AudioSystem.getClip();
	         // Open audio clip and load samples from the audio input stream.
	         clip.open(audioIn);
	        	 clip.start();
	        	 clip.start();
	        	 clip.start();
	        	 clip.start();
	         
	      } catch (UnsupportedAudioFileException e) {
	         e.printStackTrace();
	      } catch (IOException e) {
	         e.printStackTrace();
	      } catch (LineUnavailableException e) {
	         e.printStackTrace();
	      }
	}
	
}


