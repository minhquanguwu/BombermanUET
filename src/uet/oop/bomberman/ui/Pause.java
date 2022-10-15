package uet.oop.bomberman.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import uet.oop.bomberman.graphics.Sprite;

public class Pause extends VBox {
    public Pause() {
        Button button1 = new MenuButton(1);
        Button button2 = new MenuButton(2);
        this.getChildren().addAll(button1);
        this.setSpacing(8);
        this.setAlignment(Pos.CENTER);
    }
}
