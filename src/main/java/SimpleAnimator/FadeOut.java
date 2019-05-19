package SimpleAnimator;

import Window.Router;
import javafx.animation.FadeTransition;
import javafx.scene.Node;
import javafx.util.Duration;

public class FadeOut implements Animatable {
        private FadeTransition ft = new FadeTransition();

        public FadeOut(double seconds) {
            ft.setDuration(Duration.millis(Duration.seconds(seconds).toMillis()));
        }

        @Override
        public Node fire(Node node) {
            ft.setNode(node);
            ft.setFromValue(1.0);
            ft.setToValue(0.0);
            ft.setCycleCount(1);
            ft.setAutoReverse(false);

            ft.play();

            return ft.getNode();
        }

    @Override
    public void fire(Node node, String key, Animatable animatable) {
        ft.setNode(node);
        ft.setFromValue(1.0);
        ft.setToValue(0.0);
        ft.setCycleCount(1);
        ft.setAutoReverse(false);
        ft.setOnFinished(event -> {
            Router.switchTo(key, animatable);
        });

        ft.play();
    }
}
