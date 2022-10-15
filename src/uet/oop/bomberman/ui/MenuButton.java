package uet.oop.bomberman.ui;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.Scenes.WelcomeScene;

public class MenuButton extends Button {
    private static final int B_WIDTH = 140;
    private static final int B_HEIGHT = 56;

    public MenuButton(int Status) {
        String a1 = "/menubuttons/play.png";
        String a2 = "/menubuttons/play3.png";
        switch (Status) {
            case 0: {
                a1 = "/menubuttons/play.png";
                a2 = "/menubuttons/play3.png";
                this.setOnAction(e -> {
                    WelcomeScene.stopMusic();
                    Game.initGame();
                    Game.setScene(Game.Game_Scene);
                });
                break;
            }
            case 1: {
                a1 = "/menubuttons/quit1.png";
                a2 = "/menubuttons/quit2.png";
                this.setOnAction(e -> {
                    System.exit(0);
                });
                break;
            }
            case 2: {
                a1 = "/menubuttons/option1.png";
                a2 = "/menubuttons/option2.png";
                this.setOnAction(e -> {

                });
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
