package videogame.sprite;

import javafx.scene.image.Image;

import java.nio.file.Files;
import java.nio.file.Paths;

public class MainCharacter extends AnimatedSprite {
    public static final int MAIN_CHARACTER_WIDTH = 48;
    public static final int MAIN_CHARACTER_HEIGHT = 48;
    public static final int STEP = 4;

    public static final String IMAGE_PATH = "assets/sprites.png";

    public MainCharacter() {
        super(MAIN_CHARACTER_WIDTH, MAIN_CHARACTER_HEIGHT);
        try {
            spriteImage = new Image(Files.newInputStream(Paths.get(IMAGE_PATH)));
        } catch (Exception e) {
            e.printStackTrace();
        }

        spriteXCoordinates[RIGHT] = new int[] {336, 288, 384, 288};
        spriteYCoordinates[RIGHT] = new int[] {0, 0, 0, 0};
        spriteXCoordinates[LEFT] = new int[] {480, 432, 528, 432};
        spriteYCoordinates[LEFT] = new int[] {0, 0, 0, 0};
        spriteXCoordinates[UP] = new int[] {48, 0, 96, 0};
        spriteYCoordinates[UP] = new int[] {0, 0, 0, 0};
        spriteXCoordinates[DOWN] = new int[] {192, 144, 240, 144};
        spriteYCoordinates[DOWN] = new int[] {0, 0, 0, 0};

        updateSpriteCoordinates();
    }

    public void move(int movement) {
        int newX = x;
        int newY = y;
        if (movement == LEFT)
            newX -= STEP;
        else if (movement == RIGHT)
            newX += STEP;
        else if (movement == UP)
            newY -= STEP;
        else if (movement == DOWN)
            newY += STEP;
        moveTo(newX, newY);
        animate(movement);
    }
}
