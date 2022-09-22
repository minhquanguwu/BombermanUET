package uet.oop.bomberman.entities;


import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.event.EventHandler;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.*;

public class Bomber extends Entity {

    private int dx = 1;
    public Bomber(int x, int y, Image img) {
        super( x, y, img);
    }

    public  boolean isAlive = true;

    public int directionX = 1;

    public int directionY = 1;
//    nhap su kien ban phim -> thay doi huong di cua bomber


    @Override
    public void update() {

        this.x += directionX;
        this.y += directionY;
    }
}
