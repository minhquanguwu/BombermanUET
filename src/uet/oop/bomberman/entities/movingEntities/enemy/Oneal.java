package uet.oop.bomberman.entities.movingEntities.enemy;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

public class Oneal extends Enemies{
    private int timeEffect = 0;
    public Oneal(int x, int y, Image image) {
        super(x, y, image);
    }
    @Override
    public void update() {
        animate();
        timeEffect--;
        if (timeEffect < - 7500) timeEffect = 0;
        if (timeEffect <= 0) STEP = 1;
        if (_isDestroy) {
            destroy();
            return;
        }
        Image temp = null;
        switch (Status) {
            case 0: {
                if (canMove(x, y - STEP)) {
                    y -= STEP;
                    temp = Sprite.movingSprite(Sprite.oneal_right1,Sprite.oneal_right2, Sprite.oneal_right3, animate, 50).getFxImage();
                } else {
                    timeEffect = 25;
                    STEP = 2;
                    Status = 2;
                }
                break;
            }
            case 1: {
                if (canMove(x, y + STEP)) {
                    y += STEP;
                    temp = Sprite.movingSprite(Sprite.oneal_left1,Sprite.oneal_left2, Sprite.oneal_left3, animate, 50).getFxImage();
                } else {
                    timeEffect = 25;
                    STEP = 2;
                    Status = 3;
                }
                break;
            }
            case 2: {
                if (canMove(x - STEP, y)) {
                    x -= STEP;
                    temp = Sprite.movingSprite(Sprite.oneal_left1,Sprite.oneal_left2, Sprite.oneal_left3, animate, 50).getFxImage();
                } else {
                    timeEffect = 25;
                    STEP = 2;
                    Status = 1;
                }
                break;
            }
            case 3: {
                if (canMove(x + STEP, y)) {
                    x += STEP;
                    temp = Sprite.movingSprite(Sprite.oneal_right1,Sprite.oneal_right2, Sprite.oneal_right3, animate,  50).getFxImage();
                } else {
                    timeEffect = 100;
                    STEP = 2;
                    Status = 0;
                }
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
        Image temp = Sprite.movingSprite(Sprite.oneal_dead, Sprite.mob_dead1, Sprite.mob_dead2, animate, 20).getFxImage();
        setImg(temp);
        if (timeDestroy < 0) this.Remove();
    }
}
