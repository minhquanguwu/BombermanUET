package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.entities.items.BombItem;
import uet.oop.bomberman.entities.items.FlameItem;
import uet.oop.bomberman.entities.items.SpeedItem;
import uet.oop.bomberman.entities.movingEntities.Balloon;
import uet.oop.bomberman.entities.movingEntities.Bomber;
import uet.oop.bomberman.entities.staticEntities.Brick;
import uet.oop.bomberman.entities.staticEntities.Grass;
import uet.oop.bomberman.entities.staticEntities.Wall;
import uet.oop.bomberman.graphics.Sprite;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class BombermanGame extends Application {
    
    public static final int WIDTH = 31;
    public static final int HEIGHT = 13;
    
    private GraphicsContext gc;
    private Canvas canvas;
    public static List<Entity> entities = new ArrayList<>();

    public static List<Entity> enemies = new ArrayList<>();
    public static List<Entity> stillObjects = new ArrayList<>();
    public static List<Entity> bombsObjects = new ArrayList<>();


    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    @Override
    public void start(Stage stage) {
        // Tao Canvas
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        gc = canvas.getGraphicsContext2D();

        // Tao root container
        Group root = new Group();
        root.getChildren().add(canvas);

        // Tao scene
        Scene scene = new Scene(root);

        // Them scene vao stage
        stage.setScene(scene);
        stage.show();

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

        createMapFromFile();

        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
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



    public void createMapFromFile() {
        int level = 1;
        List<String> list = new ArrayList<>();
        try {
            FileReader fr = new FileReader("res\\levels\\Level" + level + ".txt");//doc tep luu map
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();
            while (!line.equals("")) {
                list.add(line);
                line = br.readLine();
                //doc file txt luu vao list
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        String[] arrays = list.get(0).trim().split(" ");
        int _level = Integer.parseInt(arrays[0]);
        int _height = Integer.parseInt(arrays[1]);
        int _width = Integer.parseInt(arrays[2]);
        char[][] _map = new char[_height][_width];
        for (int i = 0; i < _height; i++) {
            for (int j = 0; j < _width; j++) {
                _map[i][j] = list.get(i + 1).charAt(j);
            }
        }
        for (int i = 0; i < 31; i++) {
            for (int j = 0; j < 13; j++) {
                char character = _map[j][i];
                switch (character) {
                    case '#': {
                        Entity object = new Wall(i, j, Sprite.wall.getFxImage());
                        stillObjects.add(object);
                        break;
                    }
                    case '1': {
                        Entity object1  = new Grass(i, j, Sprite.grass.getFxImage());
                        stillObjects.add(object1);
                        Entity object  = new Balloon(i, j, Sprite.balloom_right1.getFxImage());
                        enemies.add(object);
                        break;
                    }
                    case '*': {
                        Entity object1  = new Grass(i, j, Sprite.grass.getFxImage());
                        stillObjects.add(object1);
                        Entity object  = new Brick(i, j, Sprite.brick.getFxImage());
                        stillObjects.add(object);
                        break;
                    }
                    case 'f': {
                        Entity object  = new Grass(i, j, Sprite.grass.getFxImage());
                        stillObjects.add(object);
                        object  = new FlameItem(i, j, Sprite.powerup_flames.getFxImage());
                        stillObjects.add(object);
                        object = new Brick(i, j, Sprite.brick.getFxImage(), true);
                        stillObjects.add(object);
                        break;
                    }
                    case 's': {
                        Entity object  = new Grass(i, j, Sprite.grass.getFxImage());
                        stillObjects.add(object);
                        object  = new SpeedItem(i, j, Sprite.powerup_speed.getFxImage());
                        stillObjects.add(object);
                        object  = new Brick(i, j, Sprite.brick.getFxImage(), true);
                        stillObjects.add(object);
                        break;
                    }
                    case 'b': {
                        Entity object = new Grass(i, j, Sprite.grass.getFxImage());
                        stillObjects.add(object);
                        object  = new BombItem(i, j, Sprite.powerup_bombs.getFxImage());
                        stillObjects.add(object);
                        object  = new Brick(i, j, Sprite.brick.getFxImage(), true);
                        stillObjects.add(object);
                        break;
                    }
                    default: {
                        Entity object  = new Grass(i, j, Sprite.grass.getFxImage());
                        stillObjects.add(object);
                        break;
                    }
                }
            }
        }
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
        for (int i = 0; i < stillObjects.size(); i++) {
            if (stillObjects.get(i).removed) {
                stillObjects.remove(i);
                continue;
            }
            stillObjects.get(i).update();
        }
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        stillObjects.forEach(g -> g.render(gc));
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
}