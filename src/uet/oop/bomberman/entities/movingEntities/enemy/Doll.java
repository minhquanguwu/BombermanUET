package uet.oop.bomberman.entities.movingEntities.enemy;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

public class Doll extends Enemies {

    public Doll(int x, int y, Image image) {
        super(x, y, image);
        this.Status = 1;
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
            case 0: {
                if (canMove(x, y - STEP)) {
                    y -= STEP;
                    temp = Sprite.movingSprite(Sprite.doll_right1,Sprite.doll_right2, Sprite.doll_right3, animate, 50).getFxImage();
                } else Status = 3;
                break;
            }
            case 1: {
                if (canMove(x, y + STEP)) {
                    y += STEP;
                    temp = Sprite.movingSprite(Sprite.doll_left1,Sprite.doll_left2, Sprite.doll_left3, animate, 50).getFxImage();
                } else Status = 2;
                break;
            }
            case 2: {
                if (canMove(x - STEP, y)) {
                    x -= STEP;
                    temp = Sprite.movingSprite(Sprite.doll_left1,Sprite.doll_left2, Sprite.doll_left3, animate, 50).getFxImage();
                } else Status = 0;
                break;
            }
            case 3: {
                if (canMove(x + STEP, y)) {
                    x += STEP;
                    temp = Sprite.movingSprite(Sprite.doll_right1,Sprite.doll_right2, Sprite.doll_right3, animate, 50).getFxImage();
                } else Status = 1;
                break;
            }
            default: {
                temp = this.img;
                break;
            }
        }
        setImg(temp);
    }

    @Override
    public void destroy() {
        timeDestroy--;
        Image temp = Sprite.movingSprite(Sprite.doll_dead, Sprite.mob_dead1, Sprite.mob_dead2, animate, 20).getFxImage();
        setImg(temp);
        if (timeDestroy < 0) this.Remove();
    }
}
