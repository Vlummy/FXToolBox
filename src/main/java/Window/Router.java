package Window;

import SceneSwitcher.SceneSwitcher;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;

public class Router {
    private static Router router = new Router();
    private SceneSwitcher sceneSwitcher = SceneSwitcher.getInstance();
    private BorderPane window;

    /**
     * Private empty Constructor
     */
    private Router() {
    }

    /**
     * Switch to view binned to input key
     * @param key
     */
    public static void switchTo(String key) {
        getInstance().setView(key);
    }

    /**
     * Private method for setting view of window
     * @param key
     */
    private void setView(String key) {
        this.window.setCenter(this.sceneSwitcher.getView(key));
    }

    /**
     * Returns the router instance
     * @return Router instance
     */
    private static Router getInstance() {
        return router;
    }

    /**
     * Connects a root window to router
     * @param window
     * @return
     */
    public static void connect(Window window) {
        getInstance().set(window);
    }

    /**
     * Set window stage
     * @param window
     * @return Router instance
     */
    private void set(Window window) {
        this.window = window.getWindow();
    }
}
