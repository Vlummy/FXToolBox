import SimpleAnimator.*;
import Window.Router;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class Login {
    @FXML private Button login;

    public void initialize() {
        login.setOnAction(event -> Router.switchTo(
                "login", // Current view
                "button", // Requested view
                new SlideOutLeft(1), // Animation on current view
                new ZoomIn(1) // Animation on requested view
                )
        );
    }
}
