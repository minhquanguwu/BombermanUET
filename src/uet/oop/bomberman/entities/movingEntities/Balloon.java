package uet.oop.bomberman.entities.movingEntities;


import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;

public class Balloon extends Entity {
    public Balloon(int x, int y, Image image) {
        super(x, y, image);
    }

    @Override
    public void update() {
        y++;
    }
}
