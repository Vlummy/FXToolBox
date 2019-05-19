package SimpleAnimator;

import Window.Router;
import javafx.scene.Node;

public interface Animatable {
    Node fire(Node node);
    void fire(Node node, String key, Animatable animatable);
}
