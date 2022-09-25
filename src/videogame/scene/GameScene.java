package videogame.scene;

import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import videogame.BomberGame;
import videogame.sprite.MainCharacter;

import java.nio.file.Files;
import java.nio.file.Paths;

public class GameScene extends GeneralScene {
    public static final String BACKGROUND_IMAGE = "assets/background.png";
    private Image background;
    private MainCharacter bear;

    public GameScene() {
        super();
        try {
            background = new Image(Files.newInputStream(Paths.get(BACKGROUND_IMAGE)));
            bear = new MainCharacter();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void draw() {
        activeKeys.clear();
        bear.moveTo(380,375);
        new AnimationTimer() {
            @Override
            public void handle(long currentNanoTime) {
                // Background
                gc.setFill(Color.BLACK);
                gc.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);

                gc.drawImage(background, 0, 0);
                bear.draw(gc);

                if (activeKeys.contains(KeyCode.SPACE)) {
                    this.stop();
                    BomberGame.setScene(BomberGame.End_Scene);
                } else if (activeKeys.contains(KeyCode.ESCAPE)) {
                    this.stop();
                    BomberGame.setScene(BomberGame.Welcome_Scene);
                } else if (activeKeys.contains(KeyCode.LEFT)) {
                    bear.move(MainCharacter.LEFT);
                } else if (activeKeys.contains(KeyCode.RIGHT)) {
                    bear.move(MainCharacter.RIGHT);
                } else if (activeKeys.contains(KeyCode.UP)) {
                    bear.move(MainCharacter.UP);
                } else if (activeKeys.contains(KeyCode.DOWN)) {
                    bear.move(MainCharacter.DOWN);
                }

                activeKeys.clear();
            }
        }.start();
    }
}
