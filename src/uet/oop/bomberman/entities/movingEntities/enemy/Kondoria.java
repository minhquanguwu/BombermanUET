package uet.oop.bomberman.entities.movingEntities.enemy;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;

public class Kondoria extends Enemies{
    public Kondoria(int x, int y, Image image) {
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
        switch (Status) {
            case 0: {
                if (canMove(x, y - STEP)) {
                    y -= STEP;
                    temp = Sprite.movingSprite(Sprite.kondoria_right1,Sprite.kondoria_right2, Sprite.kondoria_right3, animate, 50).getFxImage();
                } else Status = 3;
                break;
            }
            case 1: {
                if (canMove(x, y + STEP)) {
                    y += STEP;
                    temp = Sprite.movingSprite(Sprite.kondoria_left1,Sprite.kondoria_left2, Sprite.kondoria_left3, animate, 50).getFxImage();
                } else Status = 2;
                break;
            }
            case 2: {
                if (canMove(x - STEP, y)) {
                    x -= STEP;
                    temp = Sprite.movingSprite(Sprite.kondoria_left1,Sprite.kondoria_left2, Sprite.kondoria_left3, animate, 50).getFxImage();
                } else Status = 0;
                break;
            }
            case 3: {
                if (canMove(x + STEP, y)) {
                    x += STEP;
                    temp = Sprite.movingSprite(Sprite.kondoria_right1,Sprite.kondoria_right2, Sprite.kondoria_right3, animate, 50).getFxImage();
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
        if (timeDestroy < 0) {
            Enemies doll = new Doll(this.getXUnit(), this.getYUnit(), Sprite.doll_right3.getFxImage());
            Enemies ballon = new Balloon(this.getXUnit(), this.getYUnit(), Sprite.balloom_right3.getFxImage());
            BombermanGame.enemies.add(doll);
            BombermanGame.enemies.add(ballon);
            this.Remove();
        }
    }
}
