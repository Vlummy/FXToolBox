package SimpleAnimator;

import Window.Router;
import javafx.animation.FadeTransition;
import javafx.scene.Node;
import javafx.util.Duration;

public class FadeIn implements Animatable {
    private FadeTransition ft = new FadeTransition();

    public FadeIn(double seconds) {
        ft.setDuration(Duration.millis(Duration.seconds(seconds).toMillis()));
    }

    @Override
    public Node fire(Node node) {
        node.setOpacity(0);
        ft.setNode(node);
        ft.setFromValue(0.0);
        ft.setToValue(1.0);
        ft.setCycleCount(1);
        ft.setAutoReverse(false);

        ft.play();

        return ft.getNode();
    }

    @Override
    public void fire(Node node, String key, Animatable animatable) {
        node.setOpacity(0);
        ft.setNode(node);
        ft.setFromValue(0.0);
        ft.setToValue(1.0);
        ft.setCycleCount(1);
        ft.setAutoReverse(false);
        ft.setOnFinished(event -> {
            Router.switchTo(key, animatable);
        });

        ft.play();
    }
}
