package game1.utils;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import game1.Handler;


public class SoundPlayer {
	private Handler handler;
	public Clip laser_fire, nsit_die, guard_die, back;
	public SoundPlayer(Handler handler) {
		// TODO Auto-generated constructor stub
		this.handler = handler;
	}
	public void player(String name) {
		try {
	        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(this.getClass().getResource("/sounds/"+name));
	        Clip clip = AudioSystem.getClip();
	        clip.open(audioInputStream);
	        clip.start();
	        // If you want the sound to loop infinitely, then put: clip.loop(Clip.LOOP_CONTINUOUSLY); 
	        // If you want to stop the sound, then use clip.stop();
	    } catch (Exception ex) {
	        ex.printStackTrace();
	    }
	}
}
