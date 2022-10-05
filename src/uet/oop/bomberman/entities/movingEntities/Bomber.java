package uet.oop.bomberman.entities.movingEntities;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.entities.bomb.Bomb;
import uet.oop.bomberman.entities.items.BombItem;
import uet.oop.bomberman.entities.items.FlameItem;
import uet.oop.bomberman.entities.items.Item;
import uet.oop.bomberman.entities.items.SpeedItem;
import uet.oop.bomberman.graphics.Sprite;

import java.awt.*;
import java.util.List;


public class Bomber extends Ally {
    private int STEP = 4;
    private boolean alive = true;

    public int STATUS = -1;
    public int MaxBomb = 1;
    public static int CountBomb = 0;
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
    protected boolean canMove(List<Entity> stillObjects, int x, int y) {
        boolean temp = true;
        for(Entity check : stillObjects) {
            Rectangle rectObject = new Rectangle(check.getX(), check.getY(), Sprite.SCALED_SIZE, Sprite.SCALED_SIZE);
            Rectangle rectBomber = new Rectangle(x, y, 24, Sprite.SCALED_SIZE);
            if (rectBomber.intersects(rectObject)) {
                if (check instanceof Item && !((Item) check).hide) {
                    if (check instanceof FlameItem) {
                        Bomb.flameSize++;
                    }
                    if (check instanceof SpeedItem) {
                        STEP += 4;
                    }
                    if (check instanceof BombItem) {
                        MaxBomb++;
                    }
                    check.Remove();
                }
                temp = check.collide(this);
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
        if (_isDestroy || isAlive() == false) {
            this.destroy();
            return;
        }
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
        if (timeBetweenBomb <= 0 && CountBomb < MaxBomb) {
            return true;
        } else return false;
    }

    protected void placeBomb() {
        Entity object = new Bomb(this.getXUnit(), this.getYUnit(), Sprite.bomb.getFxImage());
        BombermanGame.stillObjects.add(object);
        CountBomb++;
        timeBetweenBomb = 50;
    }

    @Override
    public void destroy() {
        timeDestroy--;
        Image temp = Sprite.movingSprite(Sprite.player_dead1, Sprite.player_dead2, Sprite.player_dead3, animate, 30).getFxImage();
        setImg(temp);
        if (timeDestroy < 0) this.Remove();
    }

    public boolean isAlive() {
        for(Entity check : BombermanGame.enemies) {
            Rectangle rectObject = new Rectangle(check.getX(), check.getY(), Sprite.SCALED_SIZE, Sprite.SCALED_SIZE);
            Rectangle rectBomber = new Rectangle(x, y, 24, Sprite.SCALED_SIZE);
            if (rectBomber.intersects(rectObject)) {
                if (check instanceof Enemies) {
                    alive = false;
                }
            }
        }
        return alive;
    }

    // cmt
}
