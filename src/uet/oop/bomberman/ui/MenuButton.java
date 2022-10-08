//package uet.oop.bomberman.ui;
//import javafx.scene.image.Image;
//
//import javax.imageio.ImageIO;
//import java.awt.image.BufferedImage;
//import java.io.IOException;
//import java.net.URL;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//
//public class MenuButton {
//
//    public static final String IMAGE_PATH = "res/button_atlas.png";
//    private static final int B_WIDTH = 140;
//    private static final int B_HEIGHT = 56;
//    private  int xPos, yPos, row;
//    private BufferedImage[] img;
//    BufferedImage temp;
//
//    public MenuButton(int xPos, int yPos, int row) {
//        this.xPos = xPos;
//        this.yPos = yPos;
//        this.row = row;
//        loadImg();
//    }
//
//    private void loadImg() {
//        URL a = MenuButton.class.getResource(IMAGE_PATH);
//        try {
//            temp = ImageIO.read(a);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        img = new BufferedImage[3];
//        for (int i = 0; i < img.length; i++) {
//            img[i] = temp.getSubimage(i * B_WIDTH, row * B_HEIGHT, B_WIDTH, B_HEIGHT) ;
//        }
//    }
//}
