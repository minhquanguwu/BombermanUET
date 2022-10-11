package uet.oop.bomberman.entities.movingEntities.enemy;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

import java.util.Random;

public class Ghost extends Enemies{
    private int timeEffect = 0;

    public Ghost(int x, int y, Image image) {
        super(x, y, image);
    }

    @Override
    public void update() {
        animate();
        if (_isDestroy) {
            destroy();
            return;
        }
        Image temp = null;

        Random rand = new Random();
        int random = rand.nextInt(3) + 0;
        timeEffect--;
        if (timeEffect < - 200) {
            Status = random;
            timeEffect = 0;
        }
        switch (Status) {
            case 0: {
                if (canMove(x, y - STEP)) {
                    y -= STEP;
                    temp = Sprite.movingSprite(Sprite.ghost_right1,Sprite.ghost_right2, Sprite.ghost_right3, animate, 50).getFxImage();
                } else Status = 3;
                break;
            }
            case 1: {
                if (canMove(x, y + STEP)) {
                    y += STEP;
                    temp = Sprite.movingSprite(Sprite.ghost_left1,Sprite.ghost_left2, Sprite.ghost_left3, animate, 50).getFxImage();
                } else Status = 2;
                break;
            }
            case 2: {
                if (canMove(x - STEP, y)) {
                    x -= STEP;
                    temp = Sprite.movingSprite(Sprite.ghost_left1,Sprite.ghost_left2, Sprite.ghost_left3, animate, 50).getFxImage();
                } else Status = 0;
                break;
            }
            case 3: {
                if (canMove(x + STEP, y)) {
                    x += STEP;
                    temp = Sprite.movingSprite(Sprite.ghost_right1,Sprite.ghost_right2, Sprite.ghost_right3, animate, 50).getFxImage();
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
        Image temp = Sprite.movingSprite(Sprite.minvo_dead, Sprite.mob_dead1, Sprite.mob_dead2, animate, 20).getFxImage();
        setImg(temp);
        if (timeDestroy < 0) this.Remove();
    }
}
