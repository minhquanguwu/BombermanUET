package uet.oop.bomberman.entities.staticEntities;
import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.AnimatedEntity;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.items.Item;
import uet.oop.bomberman.entities.movingEntities.Bomber;
import uet.oop.bomberman.entities.movingEntities.enemy.Ghost;
import uet.oop.bomberman.entities.movingEntities.enemy.Minvo;
import uet.oop.bomberman.graphics.Sprite;

import java.util.List;

public class Brick extends AnimatedEntity {

    int timeDestroy = 30;
    boolean hideItem = false;
    boolean _isDestroy = false;

    public Brick(int x, int y, Image img) {
        super(x, y, img);
    }

    public Brick(int x, int y, Image img, boolean hideItem) {
        super(x, y, img);
        this.hideItem = hideItem;
    }

    @Override
    public boolean collide(Entity e) {
        if (e instanceof Ghost) return true;
        return false;
    }

    @Override
    public void update() {
        animate();
        if (_isDestroy) {
            destroy();
        }
    }

    public void destroy() {
        timeDestroy--;
        Image temp = Sprite.movingSprite(Sprite.brick_exploded, Sprite.brick_exploded1, Sprite.brick_exploded2, animate, 30).getFxImage();
        setImg(temp);
        if (timeDestroy < 0) {
            if (hideItem == true) {
                List<Entity> CheckList = BombermanGame.FindList(this.getXUnit(), this.getYUnit(), BombermanGame.staticObject);
                for (Entity check : CheckList) {
                    if (check instanceof Item) {
                        ((Item) check).hide = false;
                    }
                }
            }
            this.Remove();
        }
    }
    public void isDestroy() {
        _isDestroy = true;
    }
}
