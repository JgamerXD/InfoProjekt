package engine.world;

import engine.physics.AABB;
import engine.physics.Shape;
import engine.physics.Transform;
import engine.rendering.ImageSprite;
import engine.rendering.RenderContext;
import engine.rendering.Sprite;

public class Tile {
    public Sprite sprite;
    public Collision collision;
    public static final Tile EMPTY = new Tile(Collision.NONE);


    public enum Collision
    {
        NONE,
        SOLID,
        SLOPE
    }

    public Tile()
    {
        this.collision = Collision.SOLID;
    }

    public Tile(Collision collision)
    {
        this.collision = collision;
    }

    public Tile(Sprite sprite)
    {
        this.sprite = sprite;
    }
    
    public void render(int x,int y,RenderContext ctx)
    {
        if(sprite != null)
            sprite.draw(ctx,x+0.5,y+0.5, 1, 1);
    }

    public boolean isSolid()
    {
        return collision != Collision.NONE;
    }
}
