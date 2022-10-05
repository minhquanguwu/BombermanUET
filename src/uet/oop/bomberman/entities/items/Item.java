package uet.oop.bomberman.entities.items;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;


public class Item extends Entity {

    public boolean hide = true;

    public Item(int x, int y, Image img) {
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
