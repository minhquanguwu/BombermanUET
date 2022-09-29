package uet.oop.bomberman.entities.movingEntities;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

import java.util.MissingFormatWidthException;

public class Bomber extends Entity {
    public int directionX;
    public int directionY;
    public static int animate = 0;
    private int STEP = 2;

    public int STATUS = -1;
    public Bomber(int x, int y, Image img) {
        super( x, y, img);
    }

    private void setX(int x) {
        this.x = x;
    }
    private void setY(int y) {
        this.y = y;
    }
    private void setImg(Image img) {
        this.img = img;
    }

    @Override
    public void update() {
        switch (STATUS) {
            case 0: {
                animate++;
                y -= STEP;
                Image temp = Sprite.movingSprite(Sprite.player_up, Sprite.player_up_1, Sprite.player_up_2, animate, 50).getFxImage();
                setImg(temp);
                break;
            }
            case 1: {
                animate++;
                y += STEP;
                Image temp = Sprite.movingSprite(Sprite.player_down, Sprite.player_down_1, Sprite.player_down_2, animate, 50).getFxImage();
                setImg(temp);
                break;
            }
            case 2: {
                animate++;
                x -= STEP;
                Image temp = Sprite.movingSprite(Sprite.player_left, Sprite.player_left_1, Sprite.player_left_2, animate, 50).getFxImage();
                setImg(temp);
                break;
            }
            case 3: {
                animate++;
                x += STEP;
                Image temp = Sprite.movingSprite(Sprite.player_right, Sprite.player_right_1, Sprite.player_right_2, animate, 50).getFxImage();
                setImg(temp);
                break;
            }
            default:
                break;
        }
        //STATUS = -1;
    }
}
