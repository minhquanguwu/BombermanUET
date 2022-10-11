package uet.oop.bomberman.ui;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import uet.oop.bomberman.Game;

public class MenuButton extends Button {
    private static final int B_WIDTH = 140;
    private static final int B_HEIGHT = 56;

    public MenuButton(int Status) {
        String a1 = "/play.png";
        String a2 = "/play3.png";
        switch (Status) {
            case 0: {
                a1 = "/play.png";
                a2 = "/play3.png";
                this.setOnAction(e -> {
                    Game.initGame();
                    Game.setScene(Game.Game_Scene);
                });
                break;
            }
            case 1: {
                a1 = "/quit1.png";
                a2 = "/quit2.png";
                break;
            }
            default:
                break;
        }
        String s1 = a1;
        String s2 = a2;
        loadBackground(s1);
        this.setOnMouseEntered(e -> {
            loadBackground(s2);
        });
        this.setOnMouseExited(e -> {
            loadBackground(s1);
        });
        this.setPrefSize(B_WIDTH, B_HEIGHT);
    }

    private void loadBackground(String s) {
        Image image = new Image(s);
        BackgroundImage backgroundimage = new BackgroundImage(image,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        Background background = new Background(backgroundimage);
        this.setBackground(background);
    }

}
