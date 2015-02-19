package engine.physics;

import engine.math.Vec2d;

/**
 * Created by Jan on 14.02.2015.
 * super class for all shapes used for physics.
 */
public interface Shape {
    public enum Types
    {
        AABB,
        CIRCLE;
    }
    public Types getType();

    public AABB getAABB(Transform t);

    public Vec2d getCenter(Transform t);
}
