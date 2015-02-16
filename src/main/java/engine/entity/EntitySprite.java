package engine.entity;

import engine.Input;
import engine.physics.AABB;
import engine.physics.Transform;
import engine.rendering.RenderContext;
import engine.rendering.Sprite;

/**
 * Created by Jan on 15.02.2015.
 */
public class EntitySprite extends Entity {
    Sprite sprite;
    AABB bounds;

    public EntitySprite()
    {
        super();
    }
    public EntitySprite(AABB bounds,Transform transform, Sprite sprite)
    {
        this.bounds = bounds;
        this.sprite = sprite;
        this.transform = transform;
    }

    public AABB getAABB()
    {
        return bounds.getAABB(transform);
    }

    public double getWidth()
    {
        return bounds.getWidth();
    }

    public double getHeight()
    {
        return bounds.getHeight();
    }

    @Override
    public void update(Input input) {
        super.update(input);
    }

    public Sprite getSprite()
    {
        return sprite;
    }
}
