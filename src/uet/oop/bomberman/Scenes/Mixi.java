package uet.oop.bomberman.Scenes;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class Mixi {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}