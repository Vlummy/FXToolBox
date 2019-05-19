package StageCustomizer;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Objects;

public class Window {
    BorderPane window;

    public Window(Stage stage) {
        try {
            window = FXMLLoader.load(Objects.requireNonNull(this.getClass().getClassLoader().getResource("fxml/window.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setScene(new Scene(window));
        stage.initStyle(StageStyle.TRANSPARENT);
    }

}
