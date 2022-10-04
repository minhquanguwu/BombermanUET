package uet.oop.bomberman.entities.movingEntities;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.entities.bomb.Bomb;
import uet.oop.bomberman.graphics.Sprite;

import java.awt.*;
import java.util.List;


public class Bomber extends Ally {
    private int STEP = 4;

    public int STATUS = -1;
    public int BombCount = 1;
    public int timeBetweenBomb = 0;
    public Bomber(int x, int y, Image img) {
        super( x, y, img);
    }

    @Override
    public boolean collide(Entity e) {
        if (e instanceof StaticEntity) {
            return e.collide(e);
        }
        return false;
    }

    @Override
    protected void calculateMove() {

    }

    @Override
    protected void move() {

    }

    @Override
    protected boolean canMove(List<Entity> stillObjects, int x, int y) {
        boolean temp = true;
        for(Entity check : stillObjects) {
            Rectangle rectObject = new Rectangle(check.getX(), check.getY(), Sprite.SCALED_SIZE, Sprite.SCALED_SIZE);
            Rectangle rectBomber = new Rectangle(x, y, 24, Sprite.SCALED_SIZE);
            if (rectBomber.intersects(rectObject)) {
                temp = check.collide(check);
                if (temp == false) return temp;
            }
        }
        return true;
    }

    private void setX(int x) {
        this.x = x;
    }
    private void setY(int y) {
        this.y = y;
    }
    @Override
    public void update() {
        animate();
        timeBetweenBomb--;
        switch (STATUS) {
            case 0: {
                if (canMove(BombermanGame.stillObjects, x, y - STEP)) {
                    y -= STEP;
                }
                Image temp = Sprite.movingSprite(Sprite.player_up, Sprite.player_up_1, Sprite.player_up_2, animate, 50).getFxImage();
                setImg(temp);
                break;
            }
            case 1: {
                if (canMove(BombermanGame.stillObjects, x, y + STEP)) {
                    y += STEP;
                }
                Image temp = Sprite.movingSprite(Sprite.player_down, Sprite.player_down_1, Sprite.player_down_2, animate, 50).getFxImage();
                setImg(temp);
                break;
            }
            case 2: {
                if (canMove(BombermanGame.stillObjects, x - STEP, y)) {
                    x -= STEP;
                }
                Image temp = Sprite.movingSprite(Sprite.player_left, Sprite.player_left_1, Sprite.player_left_2, animate, 50).getFxImage();
                setImg(temp);
                break;
            }
            case 3: {
                if (canMove(BombermanGame.stillObjects, x + STEP, y)) {
                    x += STEP;
                }
                Image temp = Sprite.movingSprite(Sprite.player_right, Sprite.player_right_1, Sprite.player_right_2, animate, 50).getFxImage();
                setImg(temp);
                break;
            }
            case 4: {
                if (this.detectPlaceBomb()) {
                    this.placeBomb();
                }
                break;
            }
            default:
                break;
        }
        STATUS = -1;
    }

    protected boolean detectPlaceBomb() {
        if (timeBetweenBomb <= 0) {
            return true;
        } else return false;
    }

    protected void placeBomb() {
        Entity object = new Bomb(this.getXUnit(), this.getYUnit(), Sprite.bomb.getFxImage());
        BombermanGame.stillObjects.add(object);
        timeBetweenBomb = 50;
    }

}
