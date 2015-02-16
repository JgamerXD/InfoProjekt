package engine.rendering;

import engine.entity.EntitySprite;

/**
 * Created by Jan on 16.02.2015.
 */
public class SpritesheetSprite extends Sprite{

    Spritesheet sheet;
    int index;

    public SpritesheetSprite(Spritesheet sheet, int index) {
        this.sheet = sheet;
        this.index = index;
    }

    @Override
    public void update(EntitySprite entity) {

    }

    @Override
    public void draw(RenderContext ctx, double xCenter, double yCenter, double width, double height) {
        sheet.draw(index,ctx,xCenter,yCenter,width,height);
    }
}
