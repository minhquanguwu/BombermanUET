package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import uet.oop.bomberman.Scenes.GeneralScene;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.movingEntities.Bomber;
import uet.oop.bomberman.graphics.Sprite;


import java.util.ArrayList;
import java.util.List;

public class BombermanGame extends GeneralScene {

    public static final int WIDTH = 31;
    public static final int HEIGHT = 13;

    private GraphicsContext gc;
    private Canvas canvas;
    public static List<Entity> entities = new ArrayList<>();
    public static List<Entity> enemies = new ArrayList<>();
    public static List<Entity> staticObject = new ArrayList<>();
    public BombermanGame() {
        // Tao Canvas
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        gc = canvas.getGraphicsContext2D();

        // Tao root container
        Group root = new Group();
        root.getChildren().add(canvas);

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

}
