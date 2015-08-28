/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tetraworld;

import java.io.File;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.BooleanControl;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.SourceDataLine;

/**
 * Classe permettant de gerer les sons 
 * @author Gaetan
 */
public class Sound
{
        /** File contenant le fichier son*/
        File sound;
        /** String contenant le nom du fichier */
         String s;
         /** Booléen servant pour la lecture en boucle du son */
        boolean looped_forever; 
        /** 
         * Constructeur d'un Sound
         * @param s nom du fichier
         * @param loop true si le son doit être lu en boucle
         */
        public Sound(String s, boolean loop){
            this.s=s;
            looped_forever=loop;
            
            sound = new File(s);
            if(sound == null)
                System.err.println("Could not load the sound file");
        }
        /**
         * Méthode activant la lecture du Sound
         */
        public void play(){
            new Thread(play).start();
        }

       
	/**
         * Méthode intervenant lros du départ de Thread de lecture du son
         * Un certain nombres de paramètres sont paramétrables comme le volume ou la balance gauche droite
         * Dans un soucis de simplicité, l'ensemble de ces valeurs sont les mêmes pour tout les sons du jeu
         */
        
	final Runnable play = new Runnable()
	{
                boolean muted = false; 
                float volume = 70.0f; // This is the volume that goes from 0 to 100
                float pan = 0.0f; // The balance between the speakers 0 is both sides and it goes from -1 to 1
                double seconds = 0.0d; // The amount of seconds to wait before the sound starts playing
                 // It will keep looping forever if this is true
                int loop_times = 0; // Set the amount of extra times you want the sound to loop (you don't need to have looped_forever set to true)
                int loops_done = 0; // When the program is running this is counting the times the sound has looped so it knows when to stop

		public void run()
		{
			try
			{
				// Check if the audio file is a .wav file
				if (sound.getName().toLowerCase().contains(".wav"))
				{
					AudioInputStream stream = AudioSystem.getAudioInputStream(sound);
					
					AudioFormat format = stream.getFormat();
					
					if (format.getEncoding() != AudioFormat.Encoding.PCM_SIGNED)
					{
						format = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED,
								format.getSampleRate(),
								format.getSampleSizeInBits() * 2,
								format.getChannels(),
								format.getFrameSize() * 2,
								format.getFrameRate(),
								true);
						
						stream = AudioSystem.getAudioInputStream(format, stream);
					}
					
					SourceDataLine.Info info = new DataLine.Info(
							SourceDataLine.class,
							stream.getFormat(),
							(int) (stream.getFrameLength() * format.getFrameSize()));
					
					SourceDataLine line = (SourceDataLine) AudioSystem.getLine(info);
					line.open(stream.getFormat());
					line.start();
					
					// Set Volume
					FloatControl volume_control = (FloatControl) line.getControl(FloatControl.Type.MASTER_GAIN);
					volume_control.setValue((float) (Math.log(volume / 100.0f) / Math.log(10.0f) * 20.0f));
					
					// Mute
					BooleanControl mute_control = (BooleanControl) line.getControl(BooleanControl.Type.MUTE);
					mute_control.setValue(muted);
					
					FloatControl pan_control = (FloatControl) line.getControl(FloatControl.Type.PAN);
					pan_control.setValue(pan);
					
					long last_update = System.currentTimeMillis();
					double since_last_update = (System.currentTimeMillis() - last_update) / 1000.0d;
					
					// Wait the amount of seconds set before continuing
					while (since_last_update < seconds)
					{
						since_last_update = (System.currentTimeMillis() - last_update) / 1000.0d;
					}
					
					System.out.println("Lecture");
					
					int num_read = 0;
					byte[] buf = new byte[line.getBufferSize()];
					
					while ((num_read = stream.read(buf, 0, buf.length)) >= 0)
					{
						int offset = 0;
						
						while (offset < num_read)
						{
							offset += line.write(buf, offset, num_read - offset);
						}
					}
					
					line.drain();
					line.stop();
					
					if (looped_forever)
					{
						new Thread(play).start();
					}
					else if (loops_done < loop_times)
					{
						loops_done++;
						new Thread(play).start();
					}
				}
			}
			catch (Exception ex) { ex.printStackTrace(); }
		}
	};
}
