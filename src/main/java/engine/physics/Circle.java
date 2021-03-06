package engine.physics;

import engine.math.Vec2d;


/**
 * Created by Jan on 13.02.2015.
 */
public class Circle implements Shape {
    public Vec2d pos;
    public double radius;

    @Override
    public Types getType() {
        return Types.CIRCLE;
    }

    @Override
    public AABB getAABB(Transform t) {
        return new AABB(t.pos.x + pos.x - radius,t.pos.y + pos.y - radius,t.pos.x + pos.x + radius,t.pos.y + pos.y + radius);
    }

    @Override
    public Vec2d getCenter(Transform t) {
        return new Vec2d(pos).add(t.pos);
    }
}
