package Window;

import javafx.application.Platform;
import javafx.stage.Stage;

import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

/**
 * OpenCloseAction handles a smoother open and close transition when application starts and exits.
 * Author: Øyvind Johannessen
 * Version 0.1
 */
public class OpenCloseAction {
    private static OpenCloseAction openCloseAction = new OpenCloseAction();
    private Stage stage;
    private Timer timer;
    private double originalPosition;
    private double screenHeight;

    public static OpenCloseAction getInstance() {
        return openCloseAction;
    }

    /**
     * Private constructor
     */
    private OpenCloseAction() {
    }

    /**
     * Closes the application after a transition downwards.
     */
    private void privateClose() {
        TimerTask task = new TimerTask() {
            double yPos = stage.getY();
            @Override
            public void run() {
                stage.setY(yPos);
                yPos += 3;
                if(yPos > screenHeight) {
                    System.exit(0);
                }
            }
        };
        timer.scheduleAtFixedRate(task, 0, 1);
    }

    /**
     * Opens the application after a transition upwards.
     */
    private void privateOpen() {
        stage.show();
        originalPosition = stage.getY();
        stage.setY(screenHeight);
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if(stage.getY() > originalPosition) {
                    stage.setY(stage.getY());
                    stage.setY(stage.getY() - 3);
                }
            }
        };
        timer.scheduleAtFixedRate(task, 0,1);
    }

    /**
     * Opens the application after a transition upwards.
     */
    public static void open() {
        OpenCloseAction.getInstance().privateOpen();
    }

    /**
     * Closes the application after a transition downwards.
     */
    public static void close() {
        OpenCloseAction.getInstance().privateClose();
    }

    public static void setStage(Stage stage) {
        OpenCloseAction.getInstance().privateSetStage(stage);
    }

    /**
     * Sets the stage that is being handled
     * @param stage
     */
    private void privateSetStage(Stage stage) {
        this.stage = stage;
        screenHeight = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        timer = new Timer();
    }
}
