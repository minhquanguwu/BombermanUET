package uet.oop.bomberman.entities.movingEntities.enemy;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

public class Balloon extends Enemies {

    public Balloon(int x, int y, Image image) {
        super(x, y, image);
        this.Status = 3;
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
    public void destroy() {
        timeDestroy--;
        Image temp = Sprite.movingSprite(Sprite.balloom_dead, Sprite.mob_dead1, Sprite.mob_dead2, animate, 20).getFxImage();
        setImg(temp);
        if (timeDestroy < 0) {
            this.Remove();
        }
    }
}
