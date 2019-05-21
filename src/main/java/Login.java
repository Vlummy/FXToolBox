import SimpleAnimator.FadeIn;
import SimpleAnimator.FadeOut;
import SimpleAnimator.ZoomIn;
import SimpleAnimator.ZoomOut;
import Window.Router;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class Login {
    @FXML private Button login;

    public void initialize() {
        login.setOnAction(event -> Router.switchTo(
                "login", // Current view
                "button", // Requested view
                new ZoomOut(0.1), // Animation on current view
                new ZoomIn(0.1) // Animation on requested view
                )
        );
    }
}
