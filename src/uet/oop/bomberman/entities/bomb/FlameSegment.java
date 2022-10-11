package uet.oop.bomberman.entities.bomb;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.AnimatedEntity;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.movingEntities.Bomber;
import uet.oop.bomberman.entities.movingEntities.enemy.Enemies;
import uet.oop.bomberman.graphics.Sprite;

import java.util.List;

public class FlameSegment extends AnimatedEntity {

    protected boolean end;
    protected int status;
    @Override
    public boolean collide(Entity e) {
        if (e instanceof FlameSegment) return false;
        return true;
    }

    @Override
    public void update() {
        animate();
        Image temp = null;
        switch (status) {
            case 0: {
                if (end == true) {
                    temp = Sprite.movingSprite(Sprite.explosion_vertical_top_last, Sprite.explosion_vertical_top_last1, Sprite.explosion_vertical_top_last2, animate, 50).getFxImage();
                } else {
                    temp = Sprite.movingSprite(Sprite.explosion_vertical, Sprite.explosion_vertical1, Sprite.explosion_vertical2, animate, 50).getFxImage();
                }
                checkEnemy();
                checkBomber();
                break;
            }
            case 1: {
                if (end == true) {
                    temp = Sprite.movingSprite(Sprite.explosion_vertical_down_last, Sprite.explosion_vertical_down_last1, Sprite.explosion_vertical_down_last2, animate, 50).getFxImage();
                } else {
                    temp = Sprite.movingSprite(Sprite.explosion_vertical, Sprite.explosion_vertical1, Sprite.explosion_vertical2, animate, 50).getFxImage();
                }
                checkEnemy();
                checkBomber();
                break;
            }
            case 2: {
                if (end == true) {
                    temp = Sprite.movingSprite(Sprite.explosion_horizontal_left_last, Sprite.explosion_horizontal_left_last1, Sprite.explosion_horizontal_left_last2, animate, 50).getFxImage();
                } else {
                    temp = Sprite.movingSprite(Sprite.explosion_horizontal, Sprite.explosion_horizontal1, Sprite.explosion_horizontal2, animate, 50).getFxImage();
                }
                checkEnemy();
                checkBomber();
                break;
            }
            case 3: {
                if (end == true) {
                    temp = Sprite.movingSprite(Sprite.explosion_horizontal_right_last, Sprite.explosion_horizontal_right_last1, Sprite.explosion_horizontal_right_last2, animate,50).getFxImage();
                } else {
                    temp = Sprite.movingSprite(Sprite.explosion_horizontal, Sprite.explosion_horizontal1, Sprite.explosion_horizontal2, animate, 50).getFxImage();
                }
                checkEnemy();
                checkBomber();
                break;
            }
        }
        setImg(temp);
    }

    public FlameSegment(int x, int y, Image img) {
        super(x, y , img);
    }


    public FlameSegment(int x, int y, int Status, boolean end) {
        this.x = x * Sprite.SCALED_SIZE;
        this.y = y * Sprite.SCALED_SIZE;
        this.end = end;
        this.status = Status;
        Image temp = null;
        switch (status) {
            case 0: {
                if (end == true) {
                   temp = Sprite.explosion_vertical_top_last2.getFxImage();
                } else {
                    temp = Sprite.explosion_vertical2.getFxImage();
                }
                break;
            }
            case 1: {
                if (end == true) {
                    temp = Sprite.explosion_vertical_down_last2.getFxImage();
                } else {
                    temp = Sprite.explosion_vertical2.getFxImage();
                }
                break;
            }
            case 2: {
                if (end == true) {
                    temp = Sprite.explosion_horizontal_left_last2.getFxImage();
                } else {
                    temp = Sprite.explosion_horizontal2.getFxImage();
                }
                break;
            }
            case 3: {
                if (end == true) {
                    temp = Sprite.explosion_horizontal_right_last2.getFxImage();
                } else {
                    temp = Sprite.explosion_horizontal2.getFxImage();
                }
                break;
            }
        }
        setImg(temp);
    }
    private void checkEnemy() {
        List<Entity> CheckList = BombermanGame.FindList(this.getXUnit(), this.getYUnit(), BombermanGame.enemies);
        for (Entity check : CheckList) {
            if (check instanceof Enemies) {
                ((Enemies) check).isDestroy();
            }
        }
    }

    private void checkBomber() {
        List<Entity> CheckList = BombermanGame.FindList(this.getXUnit(), this.getYUnit(), BombermanGame.entities);
        for (Entity check : CheckList) {
            if (check instanceof Bomber) {
                ((Bomber) check).isDestroy();
            }
        }
    }
}
