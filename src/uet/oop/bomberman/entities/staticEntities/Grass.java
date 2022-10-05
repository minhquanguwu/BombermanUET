package uet.oop.bomberman.entities.staticEntities;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.StaticEntity;

public class Grass extends StaticEntity {

    public Grass(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public boolean collide(Entity e) {
        return true;
    }

    @Override
    public void update() {

    }
}
