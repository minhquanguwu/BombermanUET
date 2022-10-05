package uet.oop.bomberman.entities.movingEntities;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.AnimatedEntity;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

import java.awt.*;


public class Balloon extends Enemies {
    public Balloon(int x, int y, Image image) {
        super(x, y, image);
        this.Status = 3;
    }

    @Override
    public boolean collide(Entity e) {
        return false;
    }
    @Override
    public void update() {
        animate();
        if (_isDestroy) {
            destroy();
            return;
        }
        Image temp = null;
        switch (Status) {
            case 2: {
                if (canMove(x - STEP, y)) {
                    x -= STEP;
                    temp = Sprite.movingSprite(Sprite.balloom_left1,Sprite.balloom_left1, Sprite.balloom_left2, animate, 50).getFxImage();
                } else Status = 3;
                break;
            }
            case 3: {
                if (canMove(x + STEP, y)) {
                    x += STEP;
                    temp = Sprite.movingSprite(Sprite.balloom_right1,Sprite.balloom_right2, Sprite.balloom_right3, animate, 50).getFxImage();
                } else Status = 2;
                break;
            }
        }
        setImg(temp);
    }

    @Override
    protected boolean canMove(int x, int y) {
        boolean temp = true;
        for(Entity check : BombermanGame.stillObjects) {
            Rectangle rectObject = new Rectangle(check.getX(), check.getY(), Sprite.SCALED_SIZE, Sprite.SCALED_SIZE);
            Rectangle rectBomber = new Rectangle(x, y, Sprite.SCALED_SIZE, Sprite.SCALED_SIZE);
            if (rectBomber.intersects(rectObject)) {
                temp = check.collide(check);
                if (temp == false) return temp;
            }
        }
        return true;
    }

    @Override
    public void destroy() {
        timeDestroy--;
        Image temp = Sprite.movingSprite(Sprite.balloom_dead, Sprite.balloom_right3, animate, 20).getFxImage();
        setImg(temp);
        if (timeDestroy < 0) this.Remove();
    }
}
