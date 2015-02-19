package game.welt;

import engine.physics.AABB;
import engine.physics.Shape;
import engine.rendering.Bitmap;
import engine.rendering.BitmapSprite;
import engine.rendering.RenderContext;
import engine.rendering.Sprite;

public class WeltObjekt {
    public Sprite sprite;
    public AABB bounds;
    public Shape collision;
    
    public WeltObjekt()
    {
    	this.sprite = new BitmapSprite(Bitmap.MISSING);
    	this.bounds = new AABB(0,0,1,1);
    	this.collision = bounds;
    }
    
    public void render(int x,int y,RenderContext ctx)
    {
    	
    }
}
