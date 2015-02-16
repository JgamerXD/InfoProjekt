package engine.physics;

import com.sun.javafx.geom.Vec2d;

import java.io.Serializable;

/**
 * Created by Jan on 15.02.2015.
 */
public class Transform implements Serializable{
    public static final Transform DEFAULT = new Transform();
    public Vec2d pos;
    public double rot;
    public double scale;

    public Transform()
    {
        pos = new Vec2d(0.0,0.0);
        rot = 0.0;
        scale = 1.0;
    }

    public Transform(Vec2d pos, double rot, double scale) {
        this.pos = pos;
        this.rot = rot;
        this.scale = scale;
    }

    public Transform(Vec2d pos) {
        this.pos = pos;
        rot = 0.0;
        scale = 1.0;
    }
}
