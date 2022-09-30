package uet.oop.bomberman.entities;

import javafx.scene.image.Image;

import java.util.List;

public abstract class AnimatedEntity extends Entity {
    protected int animate = 0;
    protected final int MAX_ANIMATE = 7500;

    public AnimatedEntity(int x, int y, Image img) {
        super(x, y, img);
    }

    protected void animate() {
        if (animate < MAX_ANIMATE) animate++;
        else animate = 0;
    }

    protected abstract void calculateMove();
    protected abstract void move();
    protected abstract boolean canMove(List<Entity> stillObjects, int x, int y, int status);

}
