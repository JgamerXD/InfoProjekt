package engine;

import com.sun.javafx.geom.Vec2d;

/**
 * Created by Jan on 02.11.2014.
 */
public class VectorMath {

    public static Vec2d translate(Vec2d vector, Vec2d translation)
    {
        vector.x += translation.x;
        vector.y += translation.y;
        return vector;
    }

    public static Vec2d rotate(Vec2d vector,float angle)
    {
        vector.x = (float)Math.cos(angle) - vector.y;
        vector.y = (float)Math.sin(angle) + vector.y;
        return vector;
    }
}
