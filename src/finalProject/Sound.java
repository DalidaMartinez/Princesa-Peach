package finalProject;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {

	private Clip clip;

	public Sound(String path){
		try{

			AudioInputStream audio = AudioSystem.getAudioInputStream(getClass().getResource(path));

			AudioFormat base = audio.getFormat();

			AudioFormat decode = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED,base.getSampleRate(),
					16,base.getChannels(),base.getChannels() *2,base.getSampleRate(),false);

			AudioInputStream audioIn = AudioSystem.getAudioInputStream(decode, audio);


			clip = AudioSystem.getClip();
			clip.open(audio);
			
			
		}catch(Exception e){
			e.printStackTrace();

		}



	}
	public void play(){
		//allows clip to start at the beginning
		clip.setFramePosition(0);
		clip.start();
		stop();
		
	}
	
	public void playBG(){
	
			clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	
	public void stop(){
		if(clip.isRunning())
			clip.stop();

	}

	public void close(){
		stop();
		clip.close();

	}
	

}
