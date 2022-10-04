package uet.oop.bomberman.entities.movingEntities;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.AnimatedEntity;
import uet.oop.bomberman.entities.Entity;

import java.util.List;

public abstract class Ally extends AnimatedEntity {

    public Ally(int x, int y, Image img) {
        super(x, y, img);
    }

    protected abstract void calculateMove();
    protected abstract void move();
    protected abstract boolean canMove(List<Entity> stillObjects, int x, int y);
}
