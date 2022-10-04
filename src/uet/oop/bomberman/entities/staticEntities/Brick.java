package uet.oop.bomberman.entities.staticEntities;
import javafx.scene.image.Image;
import uet.oop.bomberman.entities.AnimatedEntity;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.StaticEntity;
import uet.oop.bomberman.graphics.Sprite;

public class Brick extends AnimatedEntity {

    int timeDestroy = 50;
    boolean _isDestroy = false;

    public Brick(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public boolean collide(Entity e) {
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
        Image temp = Sprite.movingSprite(Sprite.brick_exploded, Sprite.brick_exploded1, Sprite.brick_exploded2, animate, 50).getFxImage();
        setImg(temp);
        if (timeDestroy < 0) this.Remove();
    }
    public void isDestroy() {
        _isDestroy = true;
    }
}
