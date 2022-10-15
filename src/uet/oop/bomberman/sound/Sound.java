package uet.oop.bomberman.sound;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public class Sound {
    MediaPlayer mediaPlayer;
    String[] soundFile = new String[5];

    public Sound() {
        soundFile[0] = getClass().getResource("/sounds/music/title_screen.mp3").toExternalForm();
        soundFile[1] = getClass().getResource("/sounds/music/stage_theme.mp3").toExternalForm();
        soundFile[2] = getClass().getResource("/sounds/placed_bomb.wav").toExternalForm();
        soundFile[3] = getClass().getResource("/sounds/bomb_explored.wav").toExternalForm();
        soundFile[4] = getClass().getResource("/sounds/power_up.wav").toExternalForm();

    }
    public void setFileMedia(int i) {
        Media media = new Media(soundFile[i]);
        mediaPlayer = new MediaPlayer(media);
    }

    public void stop() {
        mediaPlayer.stop();
    }


    public void playMedia() {
        mediaPlayer.play();
    }

    public void playMediaLoop() {
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();
    }
}
