package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import uet.oop.bomberman.Scenes.GeneralScene;
import uet.oop.bomberman.Scenes.WelcomeScene;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.movingEntities.Bomber;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.sound.Sound;
import uet.oop.bomberman.ui.Menu;
import uet.oop.bomberman.ui.Pause;


import java.util.ArrayList;
import java.util.List;

import static javafx.scene.input.KeyCode.P;

public class BombermanGame extends GeneralScene {

    public static final int WIDTH = 31;
    public static final int HEIGHT = 13;

    public static boolean Game_Pause = false;
    public static boolean Game_Running = true;
    public static boolean NextLevel = false;
    private static Sound sound1;
    private Pause pause = new Pause();
    private GraphicsContext gc;
    private Canvas canvas;

    public static List<Entity> entities = new ArrayList<>();
    public static List<Entity> enemies = new ArrayList<>();
    public static List<Entity> staticObject = new ArrayList<>();
    public BombermanGame() {
        //clear root and components
        root.getChildren().clear();
        entities.clear();
        enemies.clear();
        staticObject.clear();

        //Sound
        sound1 = new Sound();
        Media media = new Media(getClass().getResource("/sounds/music/stage_theme.mp3").toExternalForm());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();

        // Tao Canvas
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        gc = canvas.getGraphicsContext2D();


        // Tao root container
        root.getChildren().add(canvas);
        root.setStyle("-fx-background-image: url(bgmenu1.jpg); " +
                      "-fx-backgroun-size: cover");

        // Tao scene
        this.setRoot(root);

        Entity bomberman = new Bomber(1, 1, Sprite.player_right.getFxImage());
        entities.add(bomberman);

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                if (Game_Pause == true) {
                    gc.drawImage(new Image("/menu_background.png"), 355, 40);
                    return;
                }
                if (Game_Running == false) {
                    mediaPlayer.stop();
                    Game.setScene(Game.End_Scene);
                    return;
                }
                if (isNextLevel()) {
                    mediaPlayer.stop();
                    this.stop();
                    Game.level++;
                    Game.initGameLevel2();
                    Game.setScene(3);
                }
                render();
                update();
            }
        };
        timer.start();

        Map map = new Map();

        this.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case UP:
                {
                    ((Bomber) bomberman).STATUS = 0;
                    break;
                }
                case DOWN:
                {
                    ((Bomber) bomberman).STATUS = 1;
                    break;
                }
                case LEFT:
                {
                    ((Bomber) bomberman).STATUS = 2;
                    break;
                }
                case RIGHT:
                {
                    ((Bomber) bomberman).STATUS = 3;
                    break;
                }
                case SPACE:
                {
                    ((Bomber) bomberman).STATUS = 4;
                    break;
                }
                case P:
                {
                    if(Game_Pause == true) {
                        root.getChildren().remove(pause);
                        mediaPlayer.setMute(false);
                        Game_Pause = false;
                    } else {
                        root.getChildren().add(pause);
                        mediaPlayer.setMute(true);
                        Game_Pause = true;
                    }
                    break;
                }
                default:
                    break;
            }
        });
    }


    public void update() {
        for (int i = 0; i < enemies.size(); i++) {
            if (enemies.get(i).removed) {
                enemies.remove(i);
                continue;
            }
            enemies.get(i).update();
        }
        for (int i = 0; i < entities.size(); i++) {
            if (entities.get(i).removed) {
                entities.remove(i);
                continue;
            }
            entities.get(i).update();
        }
        for (int i = 0; i < staticObject.size(); i++) {
            if (staticObject.get(i).removed) {
                staticObject.remove(i);
                continue;
            }
            staticObject.get(i).update();
        }
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        staticObject.forEach(g -> g.render(gc));
        enemies.forEach(g -> g.render(gc));
        entities.forEach(g -> g.render(gc));
    }

    public static List<Entity> FindList (int x, int y, List<Entity> CheckList) {
        List<Entity> ResultList = new ArrayList<>();
        for (Entity check : CheckList) {
            if (check.getXUnit() == x && check.getYUnit() == y)
                ResultList.add(check);
        }
        return ResultList;
    }
    @Override
    public void draw() {

    }
    public static void playSoundEffect(int i) {
        sound1.setFileMedia(i);
        sound1.playMedia();
    }

    private boolean isNextLevel() {
        return (NextLevel && enemies.size() == 0);
    }


}
