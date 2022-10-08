package uet.oop.bomberman;

import javafx.application.Application;
import javafx.stage.Stage;
import uet.oop.bomberman.Scenes.GeneralScene;
import uet.oop.bomberman.Scenes.WelcomeScene;

public class Game extends Application {
    public static final int MAX_SCENES = 3;
    public static final int Welcome_Scene = 0;
    public static final int Game_Scene = 1;
    public static final int End_Scene = 2;

    public static final GeneralScene[] scenes = new GeneralScene[MAX_SCENES];

    private static Stage stage;

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        scenes[0] = new WelcomeScene();


        stage.setTitle("Bomberman By Minh Quang");
        setScene(Welcome_Scene);
        stage.show();
    }
    public static void setScene(int numScene) {
        stage.setScene(scenes[numScene]);
        scenes[numScene].draw();
    }

    public static void exit() {
        stage.hide();
    }

    public static void initGame() {
        scenes[1] = new BombermanGame();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
