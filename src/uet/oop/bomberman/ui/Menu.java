package uet.oop.bomberman.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.RadioButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import uet.oop.bomberman.graphics.Sprite;

public class Menu extends VBox {
    protected GraphicsContext gc;
    private Canvas canvas;
    public Menu() {
        Image image5 = new Image("/menu_background.png");

        MenuButton button = new MenuButton(0);
        MenuButton button1 = new MenuButton(1);
        MenuButton button2 = new MenuButton(0);

        this.setSpacing(8);
        this.setPadding(new Insets(60, 0, 0, 0));

        this.getChildren().add(button);
        this.getChildren().add(button1);
        this.getChildren().add(button2);
        this.setAlignment(Pos.CENTER);

    }
}
