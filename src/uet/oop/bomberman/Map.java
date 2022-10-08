package uet.oop.bomberman;

import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.items.BombItem;
import uet.oop.bomberman.entities.items.FlameItem;
import uet.oop.bomberman.entities.items.SpeedItem;
import uet.oop.bomberman.entities.movingEntities.Balloon;
import uet.oop.bomberman.entities.movingEntities.Doll;
import uet.oop.bomberman.entities.staticEntities.Brick;
import uet.oop.bomberman.entities.staticEntities.Grass;
import uet.oop.bomberman.entities.staticEntities.Wall;
import uet.oop.bomberman.graphics.Sprite;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import static uet.oop.bomberman.BombermanGame.enemies;
import static uet.oop.bomberman.BombermanGame.staticObject;

public class Map {
    public Map() {
        createMapFromFile();
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
                        staticObject.add(object);
                        break;
                    }
                    case '1': {
                        Entity object1  = new Grass(i, j, Sprite.grass.getFxImage());
                        staticObject.add(object1);
                        Entity object  = new Balloon(i, j, Sprite.balloom_right1.getFxImage());
                        enemies.add(object);
                        break;
                    }
                    case '2': {
                        Entity object1  = new Grass(i, j, Sprite.grass.getFxImage());
                        staticObject.add(object1);
                        Entity object  = new Doll(i, j, Sprite.doll_right3.getFxImage());
                        enemies.add(object);
                        break;
                    }
                    case '*': {
                        Entity object1  = new Grass(i, j, Sprite.grass.getFxImage());
                        staticObject.add(object1);
                        Entity object  = new Brick(i, j, Sprite.brick.getFxImage());
                        staticObject.add(object);
                        break;
                    }
                    case 'f': {
                        Entity object  = new Grass(i, j, Sprite.grass.getFxImage());
                        staticObject.add(object);
                        object  = new FlameItem(i, j, Sprite.powerup_flames.getFxImage());
                        staticObject.add(object);
                        object = new Brick(i, j, Sprite.brick.getFxImage(), true);
                        staticObject.add(object);
                        break;
                    }
                    case 's': {
                        Entity object  = new Grass(i, j, Sprite.grass.getFxImage());
                        staticObject.add(object);
                        object  = new SpeedItem(i, j, Sprite.powerup_speed.getFxImage());
                        staticObject.add(object);
                        object  = new Brick(i, j, Sprite.brick.getFxImage(), true);
                        staticObject.add(object);
                        break;
                    }
                    case 'b': {
                        Entity object = new Grass(i, j, Sprite.grass.getFxImage());
                        staticObject.add(object);
                        object  = new BombItem(i, j, Sprite.powerup_bombs.getFxImage());
                        staticObject.add(object);
                        object  = new Brick(i, j, Sprite.brick.getFxImage(), true);
                        staticObject.add(object);
                        break;
                    }
                    default: {
                        Entity object  = new Grass(i, j, Sprite.grass.getFxImage());
                        staticObject.add(object);
                        break;
                    }
                }
            }
        }
    }
}
