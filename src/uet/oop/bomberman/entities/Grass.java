package uet.oop.bomberman.entities;

import javafx.scene.image.Image;

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
