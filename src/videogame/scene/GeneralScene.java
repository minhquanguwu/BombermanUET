package videogame.scene;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;

import java.util.HashSet;
import java.util.Set;

public abstract class GeneralScene extends Scene {
    public static final int GAME_WIDTH = 816;
    public static final int GAME_HEIGHT = 480;

    private StackPane root = new StackPane();
    protected GraphicsContext gc;
    protected Set<KeyCode> activeKeys;
    protected Set<KeyCode> releasedKeys;

    public GeneralScene() {
        super(new StackPane(), GAME_WIDTH, GAME_HEIGHT);
        // Change scene's root
        root = new StackPane();
        this.setRoot(root);

        //Init Canvas and gc
        Canvas canvas = new Canvas(GAME_WIDTH, GAME_HEIGHT);
        root.getChildren().add(canvas);
        gc = canvas.getGraphicsContext2D();

        //Init KeyCode
        activeKeys = new HashSet<>();
        releasedKeys = new HashSet<>();
        this.setOnKeyPressed(e -> {
            //activeKeys.clear();
            activeKeys.add(e.getCode());
        });
//        this.setOnKeyPressed(e -> {
//            activeKeys.remove(e.getCode());
//            releasedKeys.add(e.getCode());
//        });
    }

    public abstract void draw();
}
