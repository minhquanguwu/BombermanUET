package uet.oop.bomberman.Scenes;

import javafx.geometry.Insets;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.sound.Sound;
import uet.oop.bomberman.ui.Menu;
import uet.oop.bomberman.ui.MenuButton;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class WelcomeScene extends GeneralScene {
    protected GraphicsContext gc;
    private Canvas canvas;
    static Sound sound = new Sound();

    private Image img;
    public WelcomeScene() {
        //Sound
        playMusic(0);

        //draw MenuBoard
        this.draw();

        // Tao root container va add Button
        Menu menu = new Menu();
        StackPane root = new StackPane();
        root.getChildren().add(canvas);

        // Tao scene
        root.setPadding(new Insets(41, 355, 40, 356));
        root.getChildren().add(menu);
        root.setBackground(new Background(new BackgroundFill(Color.YELLOWGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
        this.setRoot(root);

    }

    @Override
    public void draw() {
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        gc = canvas.getGraphicsContext2D();
        gc.drawImage(new Image("/menu_background.png"), 355, 40);
    }

    public void playMusic(int i) {
        sound.setFile(i);
        sound.play();
        sound.loop();
    }

    public static void stopMusic() {
        sound.stop();
    }

}
