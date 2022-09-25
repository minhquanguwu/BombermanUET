package videogame;

import javafx.application.Application;
import javafx.stage.Stage;
import videogame.scene.EndScene;
import videogame.scene.GameScene;
import videogame.scene.GeneralScene;
import videogame.scene.WelcomeScene;

public class BomberGame extends Application {
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
        scenes[1] = new GameScene();
        scenes[2] = new EndScene();

        stage.setTitle("Bear Fruit Challenge");
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

    public static void main(String[] args) {
        launch(args);
    }

}
