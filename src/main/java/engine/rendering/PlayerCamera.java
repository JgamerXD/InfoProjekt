package engine.rendering;

import engine.math.Vec2d;

/**
 * Created by Jan on 16.03.2015.
 */
public class PlayerCamera extends Camera{

    public PlayerCamera(double scale, Vec2d translation) {
        super(scale, translation);
    }

    public PlayerCamera() {
    }

    public PlayerCamera(double scale) {
        super(scale);
    }
}
