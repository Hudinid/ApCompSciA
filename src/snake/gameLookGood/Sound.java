package snake.gameLookGood;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {

    String soundFile = "";
    SoundEffect se = new SoundEffect();

    public static void main(String[] args) {

        new Sound("");
    }

    public Sound(String sound) {

        this.soundFile = sound;
        se.setFile(sound);
        se.play(); 
    }
    public class SoundEffect {

        Clip clip;

        public void setFile(String soundFileName) {

            try {
                File file = new File(soundFileName);
                AudioInputStream sound = AudioSystem.getAudioInputStream(file);
                clip = AudioSystem.getClip();
                clip.open(sound);
            }
            catch (Exception e) {

            }
        }

        public void play() {
            clip.start();
        }
    }
}
