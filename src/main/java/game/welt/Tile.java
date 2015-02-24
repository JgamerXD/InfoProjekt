package game.welt;

import engine.physics.AABB;
import engine.physics.Shape;
import engine.physics.Transform;
import engine.rendering.Bitmap;
import engine.rendering.BitmapSprite;
import engine.rendering.RenderContext;
import engine.rendering.Sprite;

public class Tile {
    public Sprite sprite;
    public Shape collision;
    
    public Tile()
    {
    	this.sprite = new BitmapSprite(Bitmap.MISSING);
    	this.collision = new AABB(0,0,1,1);
    }

    public Tile(Sprite sprite)
    {
        this.sprite = sprite;
        this.collision = new AABB(0,0,1,1);
    }
    
    public void render(int x,int y,RenderContext ctx)
    {
        sprite.draw(ctx,x + 0.5,y + 0.5, 1, 1);
    }
}
