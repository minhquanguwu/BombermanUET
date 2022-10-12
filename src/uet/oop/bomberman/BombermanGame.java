package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import uet.oop.bomberman.Scenes.GeneralScene;
import uet.oop.bomberman.Scenes.WelcomeScene;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.movingEntities.Bomber;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.sound.Sound;
import uet.oop.bomberman.ui.Menu;


import java.util.ArrayList;
import java.util.List;

public class BombermanGame extends GeneralScene {

    public static final int WIDTH = 31;
    public static final int HEIGHT = 13;

    private static Sound sound1;
    private GraphicsContext gc;
    private Canvas canvas;
    public static List<Entity> entities = new ArrayList<>();
    public static List<Entity> enemies = new ArrayList<>();
    public static List<Entity> staticObject = new ArrayList<>();
    public BombermanGame() {
        //Sound
        sound1 = new Sound();
        playMusic(1);

        // Tao Canvas
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        gc = canvas.getGraphicsContext2D();


        // Tao root container
        root.getChildren().add(canvas);
        root.setBackground(new Background(new BackgroundFill(Color.YELLOWGREEN, CornerRadii.EMPTY, Insets.EMPTY)));

        // Tao scene
        this.setRoot(root);

        Entity bomberman = new Bomber(1, 1, Sprite.player_right.getFxImage());
        entities.add(bomberman);

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                render();
                update();
            }
        };
        timer.start();

        Map map = new Map();

        this.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case UP:
                {
                    ((Bomber) bomberman).STATUS = 0;
                    break;
                }
                case DOWN:
                {
                    ((Bomber) bomberman).STATUS = 1;
                    break;
                }
                case LEFT:
                {
                    ((Bomber) bomberman).STATUS = 2;
                    break;
                }
                case RIGHT:
                {
                    ((Bomber) bomberman).STATUS = 3;
                    break;
                }
                case SPACE:
                {
                    ((Bomber) bomberman).STATUS = 4;
                    break;
                }
//                case P:
//                {
//                    Game.setScene(Game.Welcome_Scene);
//                    break;
//                }
                default:
                    break;
            }
        });
    }


    public void update() {
        for (int i = 0; i < enemies.size(); i++) {
            if (enemies.get(i).removed) {
                enemies.remove(i);
                continue;
            }
            enemies.get(i).update();
        }
        for (int i = 0; i < entities.size(); i++) {
            if (entities.get(i).removed) {
                entities.remove(i);
                continue;
            }
            entities.get(i).update();
        }
        for (int i = 0; i < staticObject.size(); i++) {
            if (staticObject.get(i).removed) {
                staticObject.remove(i);
                continue;
            }
            staticObject.get(i).update();
        }
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        staticObject.forEach(g -> g.render(gc));
        enemies.forEach(g -> g.render(gc));
        entities.forEach(g -> g.render(gc));
    }

    public static List<Entity> FindList (int x, int y, List<Entity> CheckList) {
        List<Entity> ResultList = new ArrayList<>();
        for (Entity check : CheckList) {
            if (check.getXUnit() == x && check.getYUnit() == y)
                ResultList.add(check);
        }
        return ResultList;
    }
    @Override
    public void draw() {

    }

    public void playMusic(int i) {
        sound1.setFile(i);
        sound1.play();
        sound1.loop();
    }

    public static void stopMusic() {
        sound1.stop();
    }

    public static void playSoundEffect(int i) {
        sound1.setFile(i);
        sound1.play();
    }

}
