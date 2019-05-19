import SimpleAnimator.FadeIn;
import SimpleAnimator.FadeOut;
import Window.Router;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class Login {
    @FXML private Button login;

    public void initialize() {
        login.setOnAction(event -> Router.switchTo(
                "login", // Current view
                "button", // Requested view
                new FadeOut(0.1), // Animation on current view
                new FadeIn(0.1) // Animation on requested view
                )
        );
    }
}
