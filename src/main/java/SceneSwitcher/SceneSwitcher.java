package SceneSwitcher;

import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Class for easily switching between scenes. This class returns an SceneSwitcher instance with getInstance.
 * When applying an action event handler to a button, one can pass in the event and the Parent to be viewed in the scene.
 * Author: Øyvind Johannessen
 * Version: 0.1
 */
public class SceneSwitcher {

    private static SceneSwitcher sceneSwitcher = new SceneSwitcher();

    /**
     * Returns the primary stage by using the node parameter
     * @param node
     * @return Stage
     */
    private Stage getPrimaryStage(Node node) {
        return (Stage) node.getScene().getWindow();
    }

    /**
     * Changes the view of a scene to the new fxml document
     * @param event
     * @param fxmlDocName
     */
    public void changeToScene(Event event, String fxmlDocName) {
        Stage stage = getPrimaryStage((Node) event.getSource());
        try {
            stage.getScene().setRoot(FXMLLoader.load(getClass().getClassLoader().getResource(fxmlDocName)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Changes the view of a scene to the new Parent
     * @param event
     * @param parent
     */
    public void changeToScene(Event event, Parent parent) {
        Stage stage = getPrimaryStage((Node) event.getSource());
        stage.getScene().setRoot(parent);
    }

    public static SceneSwitcher getInstance() {
        return sceneSwitcher;
    }
}