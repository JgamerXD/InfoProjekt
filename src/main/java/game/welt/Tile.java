package game.welt;

import engine.physics.AABB;
import engine.physics.Shape;
import engine.physics.Transform;
import engine.rendering.ImageSprite;
import engine.rendering.RenderContext;
import engine.rendering.Sprite;

public class Tile {
    public Sprite sprite;
    public Shape collision;
    
    public Tile()
    {
//    	this.sprite = new ImageSprite(Bitmap.MISSING);
    	this.collision = new AABB(0,0,1,1);
    }

    public Tile(Sprite sprite)
    {
        this.sprite = sprite;
        this.collision = new AABB(0,0,1,1);
    }
    
    public void render(int x,int y,RenderContext ctx)
    {
        sprite.draw(ctx,x*100,y*100, 100, 100);
    }
}
