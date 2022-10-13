package uet.oop.bomberman.entities.bomb;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.AnimatedEntity;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.movingEntities.Bomber;
import uet.oop.bomberman.entities.staticEntities.Brick;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;

public class Bomb extends AnimatedEntity {

    private List<Entity> FlameUp = new ArrayList<>();
    private List<Entity> FlameDown = new ArrayList<>();
    private List<Entity> FlameLeft = new ArrayList<>();
    private List<Entity> FlameRight = new ArrayList<>();
    private List<Entity> FlameCenter = new ArrayList<>();
    protected int timeToExplode = 200;
    public static int flameSize = 5;

    public Bomb(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public boolean collide(Entity e) {
        if (e instanceof Bomber) return true;
        return false;
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
            BombermanGame.playSoundEffect(3);
        } else if(timeToExplode < 0) {
            UpdateFlame();
        }
        if (timeToExplode < -30) {
            FlameCenter.clear();
            FlameUp.clear();
            FlameDown.clear();
            FlameLeft.clear();
            FlameRight.clear();
            Bomber.CountBomb--;
            this.Remove();
        }
    }

    @Override
    public void render(GraphicsContext gc) {
        super.render(gc);
        RenderFlame(gc);
    }

    protected void explode() {
        Image temp = Sprite.movingSprite(Sprite.bomb_exploded,Sprite.bomb_exploded1, Sprite.bomb_exploded2, animate, 50).getFxImage();
        setImg(temp);
        setFlame();
    }

    protected void setFlameCenter() {
        Entity temp = new FlameSegment(this.getXUnit(), this.getYUnit(), 4, false);
        FlameUp.add(temp);
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
                    List<Entity> CheckList = BombermanGame.FindList(this.getXUnit(), this.getYUnit() - tempSize, BombermanGame.staticObject);
                    for (Entity check : CheckList) {
                        if (!check.collide(check)) {
                            if (check instanceof Brick) {
                                ((Brick) check).isDestroy();
                            } else if (check instanceof Bomb && ((Bomb) check).timeToExplode > 0 ) {
                                ((Bomb) check).timeToExplode = 1;
                            }
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
                    List<Entity> CheckList = BombermanGame.FindList(this.getXUnit(), this.getYUnit() + tempSize, BombermanGame.staticObject);
                    for (Entity check : CheckList) {
                        if (!check.collide(check)) {
                            if (check instanceof Brick) {
                                ((Brick) check).isDestroy();
                            } else if (check instanceof Bomb && ((Bomb) check).timeToExplode > 0 ) {
                                ((Bomb) check).timeToExplode = 1;
                            }
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
                    List<Entity> CheckList = BombermanGame.FindList(this.getXUnit() - tempSize, this.getYUnit(), BombermanGame.staticObject);
                    for (Entity check : CheckList) {
                        if (!check.collide(check)) {
                            if (check instanceof Brick) {
                                ((Brick) check).isDestroy();
                            } else if (check instanceof Bomb && ((Bomb) check).timeToExplode > 0 ) {
                                ((Bomb) check).timeToExplode = 1;
                            }
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
                    List<Entity> CheckList = BombermanGame.FindList(this.getXUnit() + tempSize, this.getYUnit(), BombermanGame.staticObject);
                    for (Entity check : CheckList) {
                        if (!check.collide(check)) {
                            if (check instanceof Brick) {
                                ((Brick) check).isDestroy();
                            } else if (check instanceof Bomb && ((Bomb) check).timeToExplode > 0 ) {
                                ((Bomb) check).timeToExplode = 1;
                            }
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

    private void UpdateFlame() {
        FlameCenter.forEach(Entity::update);
        FlameUp.forEach(Entity::update);
        FlameDown.forEach(Entity::update);
        FlameLeft.forEach(Entity::update);
        FlameRight.forEach(Entity::update);
    }
    private void RenderFlame(GraphicsContext gc) {
        FlameCenter.forEach(g -> g.render(gc));
        FlameUp.forEach(g -> g.render(gc));
        FlameDown.forEach(g -> g.render(gc));
        FlameLeft.forEach(g -> g.render(gc));
        FlameRight.forEach(g -> g.render(gc));
    }
    private void setFlame() {
        setFlameCenter();
        setFlameUp();
        setFlameDown();
        setFlameLeft();
        setFlameRight();
    }
}
