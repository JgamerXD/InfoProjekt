package engine.rendering;

import engine.entity.EntitySprite;

import java.awt.*;

/**
 * Created by Jan on 16.02.2015.
 */
public class ImageSprite extends Sprite{
    Image image;

    public ImageSprite(Image image) {
        this.image = image;
    }

    public void update(EntitySprite entity) {

    }

    public void draw(RenderContext ctx, double xCenter, double yCenter, double width, double height) {
        ctx.drawImage(image,xCenter,yCenter,width,height);
    }
}
