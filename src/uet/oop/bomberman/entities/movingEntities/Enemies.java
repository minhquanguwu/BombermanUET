package uet.oop.bomberman.entities.movingEntities;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.AnimatedEntity;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

public class Enemies extends AnimatedEntity {

    int timeDestroy = 100;
    boolean _isDestroy = false;
    protected int STEP = 1;
    protected int Status;

    public Enemies(int x, int y, Image image) {
        super(x, y, image);
    }

    @Override
    public boolean collide(Entity e) {
        if (e instanceof Enemies) return true;
        return false;
    }

    @Override
    public void update() {

    }
    protected boolean canMove(int x, int y) {
        return true;
    }

    public void destroy() {
    }
    public void isDestroy() {
        _isDestroy = true;
    }
}
