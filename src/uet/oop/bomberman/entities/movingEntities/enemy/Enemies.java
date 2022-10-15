package uet.oop.bomberman.entities.movingEntities.enemy;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.AnimatedEntity;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

import java.awt.*;

public class Enemies extends AnimatedEntity {

    int timeDestroy = 100;
    boolean _isDestroy = false;
    protected int STEP;
    protected int Status;

    public Enemies(int x, int y, Image image) {
        super(x, y, image);
        STEP = 1;
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
        boolean temp = true;
        for(Entity check : BombermanGame.staticObject) {
            Rectangle rectObject = new Rectangle(check.getX(), check.getY(), Sprite.SCALED_SIZE, Sprite.SCALED_SIZE);
            Rectangle rectBomber = new Rectangle(x, y, Sprite.SCALED_SIZE, Sprite.SCALED_SIZE);
            if (rectBomber.intersects(rectObject)) {
                temp = check.collide(this);
                if (temp == false) return temp;
            }
        }
        return true;
    }

    public void destroy() {
    }
    public void isDestroy() {
        _isDestroy = true;
    }
}
