package SceneSwitcher;

import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
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
    private SceneBinder<Parent> sceneBinder;
    private Scene scene;
    private Stage stage;
    private String entryView;

    /**
     * Private constructor
     */
    private SceneSwitcher() {
        sceneBinder = new SceneBinder<>();
    }

    public SceneSwitcher bind(String key, String fxmlDocName) {
        if(!isSwitchable())
            throw new IllegalStateException(getIllegalStateMessage());
        sceneBinder.bind(key, fxmlDocName);
        return sceneSwitcher;
    }

    public SceneSwitcher bind(String key, Parent view) {
        if(!isSwitchable())
            throw new IllegalStateException(getIllegalStateMessage());
        sceneBinder.bind(key, view);
        return sceneSwitcher;
    }

    public SceneSwitcher bindListOfViews(Parent[] views, String[] keys) {
        if(!isSwitchable())
            throw new IllegalStateException(getIllegalStateMessage());
        sceneBinder.bindListOfViews(views, keys);
        return sceneSwitcher;
    }

    public SceneSwitcher bindDirectory(String directoryName, String[] keys) {
        if(!isSwitchable())
            throw new IllegalStateException(getIllegalStateMessage());
        sceneBinder.bindDirectory(directoryName, keys);
        return sceneSwitcher;
    }

    /**
     * Method used to wrap a javafx application as switchable
     * @param stage The primary stage of this application
     */
    public SceneSwitcher applySwitchable(Stage stage) {
        this.scene = new Scene(new BorderPane());
        this.stage = stage;
        this.stage.setScene(this.scene);
        return SceneSwitcher.getInstance();
    }

    /**
     * Changes view of a scene to whatever the key points to
     * To use this method, use the class to bind a key and value path with SceneBinder class.
     * @param key The key that points to a fxml document path
     */
    public void changeToScene(String key) {
        if(key == null)
            throw new IllegalArgumentException("Key can not be null");
        if(!isSwitchable())
            throw new IllegalStateException(getIllegalStateMessage());

        this.scene.setRoot(sceneBinder.getView(key));
    }

    private String getIllegalStateMessage() {
        return "This application is not switchable. To make it switchable, use applySwitchable() method on primary stage and then add bindings";
    }

    private boolean isSwitchable() {
        return this.scene != null;
    }

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

    /**
     * Returns an singleton instance of SceneSwitcher
     * @return SceneSwitcher
     */
    public static SceneSwitcher getInstance() {
        return sceneSwitcher;
    }

    /**
     * Returns an singleton instance of SceneSwitcher with a view as first entry when application starts
     * @return SceneSwitcher
     */
    public static SceneSwitcher getInstance(String key) {
        getInstance().setMainEntryView(key);
        return sceneSwitcher;
    }

    private void setMainEntryView(String key) {
        this.entryView = key;
    }

    public void show() {
        if(this.entryView != null) {
            changeToScene(this.entryView);
        }
        this.stage.show();
    }
}