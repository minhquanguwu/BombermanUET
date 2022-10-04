package uet.oop.bomberman.entities.bomb;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.AnimatedEntity;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Bomb extends AnimatedEntity {

    private List<Entity> FlameUp = new ArrayList<>();
    private List<Entity> FlameDown = new ArrayList<>();
    private List<Entity> FlameLeft = new ArrayList<>();
    private List<Entity> FlameRight = new ArrayList<>();
    protected int timeToExplode = 200;
    protected int flameSize = 1;

    public Bomb(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public boolean collide(Entity e) {
        return true;
    }

    @Override
    public void update() {
        animate();
        timeToExplode--;
        if (timeToExplode > 0) {
            Image temp = Sprite.movingSprite(Sprite.bomb, Sprite.bomb_1, Sprite.bomb_2, animate, 50).getFxImage();
            setImg(temp);
        } else if (timeToExplode == 0) {
            this.explode();
        } else if(timeToExplode < 0) {
            Image temp = Sprite.movingSprite(Sprite.bomb_exploded,Sprite.bomb_exploded1, Sprite.bomb_exploded2, animate, 50).getFxImage();
            setImg(temp);
            FlameUp.forEach(Entity::update);
            FlameDown.forEach(Entity::update);
            FlameLeft.forEach(Entity::update);
            FlameRight.forEach(Entity::update);
        }
        if (timeToExplode < -120) {
            FlameUp.clear();
            FlameDown.clear();
            FlameLeft.clear();
            FlameRight.clear();
            this.Remove();
        }
    }

    @Override
    public void render(GraphicsContext gc) {
        super.render(gc);
        FlameUp.forEach(g -> g.render(gc));
        FlameDown.forEach(g -> g.render(gc));
        FlameLeft.forEach(g -> g.render(gc));
        FlameRight.forEach(g -> g.render(gc));
    }

    protected void explode() {
        Image temp = Sprite.movingSprite(Sprite.bomb_exploded,Sprite.bomb_exploded1, Sprite.bomb_exploded2, animate, 50).getFxImage();
        setImg(temp);
        setFlameUp();
        setFlameDown();
        setFlameLeft();
        setFlameRight();
    }

    protected void setFlameUp() {
        int Size = caculateFlameSize(0);
        for (int i = 0; i < Size; i++) {
            boolean end = false;
            if (i == Size - 1) end = true;
            Entity temp = new FlameSegment(this.getXUnit(), this.getYUnit() - (i + 1), 0, end);
            FlameUp.add(temp);
        }
    }

    protected void setFlameDown() {
        int Size = caculateFlameSize(1);
        for (int i = 0; i < Size; i++) {
            boolean end = false;
            if (i == Size - 1) end = true;
            Entity temp = new FlameSegment(this.getXUnit(), this.getYUnit() + (i + 1), 1, end);
            FlameDown.add(temp);
        }
    }

    protected void setFlameLeft() {
        int Size = caculateFlameSize(2);
        for (int i = 0; i < Size; i++) {
            boolean end = false;
            if (i == Size - 1) end = true;
            Entity temp = new FlameSegment(this.getXUnit() - (i + 1), this.getYUnit(), 2, end);
            FlameLeft.add(temp);
        }
    }

    protected void setFlameRight() {
        int Size = caculateFlameSize(3);
        for (int i = 0; i < Size; i++) {
            boolean end = false;
            if (i == Size - 1) end = true;
            Entity temp = new FlameSegment(this.getXUnit() + (i + 1), this.getYUnit(), 3, end);
            FlameRight.add(temp);
        }
    }

    protected int caculateFlameSize(int Status) {
        switch (Status) {
            case 0 : {
                int tempSize = 1;
                while (tempSize <= flameSize) {
                    List<Entity> CheckList = BombermanGame.FindList(this.getXUnit(), this.getYUnit() - tempSize, BombermanGame.stillObjects);
                    for (Entity check : CheckList) {
                        if (!check.collide(check)) {
                            return tempSize - 1;
                        }
                    }
                    if (tempSize == flameSize) return tempSize;
                    tempSize++;
                }
                break;
            }
            case 1 : {
                int tempSize = 1;
                while (tempSize <= flameSize) {
                    List<Entity> CheckList = BombermanGame.FindList(this.getXUnit(), this.getYUnit() + tempSize, BombermanGame.stillObjects);
                    for (Entity check : CheckList) {
                        if (!check.collide(check)) {
                            return tempSize - 1;
                        }
                    }
                    if (tempSize == flameSize) return tempSize;
                    tempSize++;
                }
                break;
            }
            case 2 : {
                int tempSize = 1;
                while (tempSize <= flameSize) {
                    List<Entity> CheckList = BombermanGame.FindList(this.getXUnit() - tempSize, this.getYUnit(), BombermanGame.stillObjects);
                    for (Entity check : CheckList) {
                        if (!check.collide(check)) {
                            return tempSize - 1;
                        }
                    }
                    if (tempSize == flameSize) return tempSize;
                    tempSize++;
                }
                break;
            }
            case 3 : {
                int tempSize = 1;
                while (tempSize <= flameSize) {
                    List<Entity> CheckList = BombermanGame.FindList(this.getXUnit() + tempSize, this.getYUnit(), BombermanGame.stillObjects);
                    for (Entity check : CheckList) {
                        if (!check.collide(check)) {
                            return tempSize - 1;
                        }
                    }
                    if (tempSize == flameSize) return tempSize;
                    tempSize++;
                }
                break;
            }
        }
        return flameSize;
    }
}
