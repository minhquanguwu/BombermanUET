package uet.oop.bomberman.Scenes;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.ui.MenuButton;

public class EndScene extends GeneralScene{
    public EndScene() {
        HBox root = new HBox();
        Button button1 = new MenuButton(2);
        Button button2 = new MenuButton(1);
        root.getChildren().addAll(button2);
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-background-image: url(bgmenu1.jpg); " +
                      "-fx-backgroun-size: cover");
        this.setRoot(root);

    }
    @Override
    public void draw() {

    }
}
