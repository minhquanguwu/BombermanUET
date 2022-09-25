package videogame.scene;


import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import videogame.BomberGame;

public class WelcomeScene extends GeneralScene {
    public WelcomeScene() {
        super();
        showWelcomeMessage();
    }

    public void showWelcomeMessage() {
        Font myFont = Font.font("Arial", FontWeight.NORMAL, 32);
        gc.setFont(myFont);
        gc.setFill(Color.BLUE);
        gc.fillText("PRESS SPACE TO PLAY", 325, 275);
    }

    @Override
    public void draw() {
        activeKeys.clear();
        new AnimationTimer() {
            public void handle(long currentNanoTime) {
                // Background
                gc.setFill(Color.BLACK);
                gc.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);

                showWelcomeMessage();

                if (activeKeys.contains(KeyCode.SPACE)) {
                    this.stop();
                    BomberGame.setScene(BomberGame.Game_Scene);
                } else if (activeKeys.contains(KeyCode.ESCAPE)) {
                    this.stop();
                    BomberGame.exit();
                }
            }
        }.start();
    }
}
