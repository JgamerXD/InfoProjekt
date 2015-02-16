package engine.rendering;

import com.sun.javafx.geom.Vec2d;
import engine.entity.EntitySprite;
import engine.physics.AABB;
import engine.physics.Transform;

/**
 * Created by Jan on 16.02.2015.
 */
public abstract class Sprite {
    public abstract void update(EntitySprite entity);
    public abstract void draw(RenderContext ctx,double xCenter, double yCenter, double width, double height);
    public void draw(RenderContext ctx, AABB aabb)
    {
        Vec2d c = aabb.getCenter(Transform.DEFAULT);
        draw(ctx, c.x, c.y, aabb.getWidth(),aabb.getHeight());
    }
}
