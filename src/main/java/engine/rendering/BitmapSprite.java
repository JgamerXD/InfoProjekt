package engine.rendering;

import engine.entity.EntitySprite;

/**
 * Created by Jan on 16.02.2015.
 */
public class BitmapSprite extends Sprite{
    Bitmap bitmap;

    public BitmapSprite(Bitmap bitmap) {
        this.bitmap = bitmap;
    }


    @Override
    public void update(EntitySprite entity) {

    }

    @Override
    public void draw(RenderContext ctx, double xCenter, double yCenter, double width, double height) {
        ctx.drawImage(bitmap,xCenter,yCenter,width,height);
    }
}
