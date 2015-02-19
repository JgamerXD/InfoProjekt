package game;

import engine.Game;
import engine.Input;
import engine.Quadtree;
import engine.entity.EntitySprite;
import engine.physics.AABB;
import engine.physics.Transform;
import engine.rendering.Bitmap;
import engine.rendering.RenderContext;
import engine.rendering.Spritesheet;
import engine.rendering.SpritesheetSprite;

import java.util.HashSet;
import java.util.Set;

import engine.math.Vec2d;

/**
 * Created by Jan on 14.02.2015.
 */
public class Spiel implements Game {

    Quadtree tree;
    Welt welt = new Welt();
    Spritesheet sprites = new Spritesheet(new Bitmap("/textures/goodly.png"),16,16);
    SpritesheetSprite sprite = new SpritesheetSprite(sprites,70);
    SpritesheetSprite sprite2 = new SpritesheetSprite(sprites,137);

    public Spiel()
    {
        tree = new Quadtree(new AABB(-1,-1,1,1));

        tree.addEntity(new EntitySprite(new AABB(0,0,1,1),Transform.DEFAULT,sprite));
        tree.addEntity(new EntitySprite(new AABB(0,0,0.5,0.5),new Transform(new Vec2d(-0.5,0)),sprite2));
    }
    @Override
    public void update(Input input) {

    }

    @Override
    public void render(RenderContext ctx) {
        ctx.clear((byte)0xA0);
        Set<EntitySprite> renderable = new HashSet<>();

        tree.queryRange(new AABB(-1,-1,1,1),renderable);

        for(EntitySprite e:renderable)
        {
            e.getSprite().draw(ctx,e.transform.pos.x,e.transform.pos.y,e.getWidth(),e.getHeight());
        }
    }
}
