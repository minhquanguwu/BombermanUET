package videogame.scene;

import javafx.animation.AnimationTimer;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import videogame.BomberGame;

public class EndScene extends GeneralScene{
    public EndScene() {
        super();
    }
    private void showGameMessage() {
        Font myFont = Font.font("Arial", FontWeight.NORMAL, 32);
        gc.setFont(myFont);
        gc.setFill(Color.BLUE);
        gc.fillText("GAME END", 325, 275);
    }
    @Override
    public void draw() {
        activeKeys.clear();
        new AnimationTimer() {
            @Override
            public void handle(long currentNanoTime) {
                // Background
                gc.setFill(Color.BLACK);
                gc.fillRect(0,0, GAME_WIDTH, GAME_HEIGHT);

                showGameMessage();

                if(activeKeys.contains(KeyCode.SPACE)) {
                    this.stop();
                    BomberGame.setScene(BomberGame.Welcome_Scene);
                }
            }
        }.start();
    }
}
