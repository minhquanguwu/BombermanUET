package uet.oop.bomberman.Scenes;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import uet.oop.bomberman.graphics.Sprite;

import java.util.HashSet;
import java.util.Set;

public abstract class GeneralScene extends Scene {
    public static final int WIDTH = 31;
    public static final int HEIGHT = 13;


    protected GraphicsContext gc;
    protected StackPane root = new StackPane();

    public GeneralScene() {
        super(new StackPane(), Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);

    }

    public abstract void draw();
}
