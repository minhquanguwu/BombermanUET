package uet.oop.bomberman.entities.movingEntities;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.AnimatedEntity;
import uet.oop.bomberman.entities.Entity;

import java.util.List;

public abstract class Ally extends AnimatedEntity {

    int timeDestroy = 100;
    boolean _isDestroy = false;

    public Ally(int x, int y, Image img) {
        super(x, y, img);
    }

    public void destroy() {
    }
    public void isDestroy() {
        _isDestroy = true;
    }
    protected abstract boolean canMove(List<Entity> stillObjects, int x, int y);
}
