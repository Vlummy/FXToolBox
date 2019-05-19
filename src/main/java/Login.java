import Window.Router;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class Login {
    @FXML private Button login;

    public void initialize() {
        login.setOnAction(event -> Router.switchTo("date"));
    }
}
