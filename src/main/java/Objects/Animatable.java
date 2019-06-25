package Objects;

import javafx.scene.transform.Translate;

public interface Animatable {

    void resetToOrigin(AnimationPoint animPoint);
    void doAnimation(AnimationPoint animPoint);
    Translate getCurrentPosition();

    double getCurrentXAngle();
    double getCurrentYAngle();
}
