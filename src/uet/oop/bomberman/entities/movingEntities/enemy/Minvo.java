package uet.oop.bomberman.entities.movingEntities.enemy;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.bomb.Bomb;
import uet.oop.bomberman.entities.movingEntities.enemy.BFS.BFS;
import uet.oop.bomberman.entities.staticEntities.Brick;
import uet.oop.bomberman.entities.staticEntities.Wall;
import uet.oop.bomberman.graphics.Sprite;

import java.util.Random;

public class Minvo extends Enemies{
    private int timeEffect = 32;
    public Minvo(int x, int y, Image image) {
        super(x, y, image);
        STEP = 1;
    }

    @Override
    public void update() {
        animate();
        if (_isDestroy) {
            destroy();
            return;
        }
        Image temp = null;
        Entity bomber = BombermanGame.entities.get(0);

        // BFS tim duong di ngan nhat den Bomber
        if (this.isSafe()) {
            int tempX = this.getXUnit(), tempY = this.getYUnit();
            BFS test = new BFS(13, 31, tempY, tempX, bomber.getYUnit(), bomber.getXUnit(), this.maps());
            if (test.getDirection().length() >= 1) {
                char status = test.getDirection().charAt(test.getDirection().length() - 1);
                if (status == 'U') Status = 0;
                else if (status == 'D') Status = 1;
                else if (status == 'L') Status = 2;
                else if (status == 'R') Status = 3;
            }
        }

        switch (Status) {
            case 0: {
                if (canMove(x, y - STEP)) {
                    y -= STEP;
                    temp = Sprite.movingSprite(Sprite.minvo_right1,Sprite.minvo_right2, Sprite.minvo_right3, animate, 50).getFxImage();
                } else Status = 3;
                break;
            }
            case 1: {
                if (canMove(x, y + STEP)) {
                    y += STEP;
                    temp = Sprite.movingSprite(Sprite.minvo_left1,Sprite.minvo_left2, Sprite.minvo_left3, animate, 50).getFxImage();
                } else Status = 2;
                break;
            }
            case 2: {
                if (canMove(x - STEP, y)) {
                    x -= STEP;
                    temp = Sprite.movingSprite(Sprite.minvo_left1,Sprite.minvo_left2, Sprite.minvo_left3, animate, 50).getFxImage();
                } else Status = 0;
                break;
            }
            case 3: {
                if (canMove(x + STEP, y)) {
                    x += STEP;
                    temp = Sprite.movingSprite(Sprite.minvo_right1,Sprite.minvo_right2, Sprite.minvo_right3, animate, 50).getFxImage();
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

    // Tao ma tran danh dau o co the di duoc
    private int[][] maps() {
        int[][] temp = new int[13][31];
        for(Entity check : BombermanGame.staticObject) {
            if (check instanceof Wall || check instanceof Brick || check instanceof Bomb) {
                temp[check.getYUnit()][check.getXUnit()] = 1;
            }
        }

        return temp;
    }

    // BFS chi chay khi toa do Unit cua Minvo la so nguyen
    private boolean isSafe() {
        return (this.getX() % Sprite.SCALED_SIZE == 0 && this.getY() % Sprite.SCALED_SIZE == 0);
    }
}
