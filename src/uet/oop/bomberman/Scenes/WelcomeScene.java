package uet.oop.bomberman.Scenes;

import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.graphics.Sprite;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class WelcomeScene extends GeneralScene {

    public static final String IMAGE_PATH = "res/button_atlas.png";
    protected GraphicsContext gc;

    private Canvas canvas;


    private Image img;
    public WelcomeScene() throws IOException {
//        URL fxmlLocation = Game.class.getResource("Mixi.fxml");
//        System.out.println(fxmlLocation);
//        FXMLLoader loader = new FXMLLoader(fxmlLocation);
//        AnchorPane root = loader.load();

        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        gc = canvas.getGraphicsContext2D();

        // Tao root container
        Group root = new Group();
        root.getChildren().add(canvas);

        // load the tron font.
        Font.loadFont(
                Game.class.getResource("/Retro Gaming.TTF").toExternalForm(),
                10
        );


        Button button1 = new Button();
        Text a = new Text(200, 300, "Arsenal");
        a.setStyle("-fx-font-size:60; -fx-font-family: 'Rockwell Extra Bold' ");
        button1.setText("Hello");
        button1.setStyle("-fx-font-size:40; -fx-font-family: 'Rockwell Extra Bold' ");


        Image image = new Image("/play.png");
        ImageView imageView = new ImageView(image);

        // Button 2
        Button button2 = new Button("", imageView);


        root.getChildren().add(a);
        root.getChildren().add(button2);

        // Tao scene
        this.setRoot(root);


        this.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case SPACE: {
                    Game.initGame();
                    Game.setScene(Game.Game_Scene);
                }
            }
        });
    }

    @Override
    public void draw() {

    }

}
