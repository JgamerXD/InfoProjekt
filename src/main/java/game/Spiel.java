package game;

import engine.Game;
import engine.Input;
import engine.Quadtree;
import engine.entity.EntitySprite;
import engine.physics.AABB;
import engine.physics.Transform;
import engine.rendering.*;

import java.awt.*;
import java.awt.image.renderable.*;
import java.util.HashSet;
import java.util.Set;

import engine.math.Vec2d;
import engine.rendering.RenderContext;
import game.welt.Tileset;

/**
 * Created by Jan on 14.02.2015.
 */
public class Spiel implements Game {

    Quadtree tree;

    Welt welt;
    Spritesheet sprites = new Spritesheet(new Bitmap("/textures/goodly.png"),16,16);
    SpritesheetSprite sprite = new SpritesheetSprite(sprites,70);
    SpritesheetSprite sprite2 = new SpritesheetSprite(sprites,137);

    public Spiel()
    {
        tree = new Quadtree(new AABB(-1,-1,1,1));

//        EntitySprite e1 = new EntitySprite(new AABB(0,0,1,1),Transform.DEFAULT,sprite);
//        EntitySprite e2 = new EntitySprite(new AABB(0,0,0.5,0.5),new Transform(new Vec2d(-0.5,0)),sprite2);
//        tree.addEntity(e1);
//        tree.addEntity(e2);

        welt = new Welt(16, 8, new int []
                {0,     0,      0,      0,      0,      0,      0,      0,      0,      0,      101,    102,    103,    0,      0,      0,
                 1,     0,      0,      0,      0,      0,      0,      0,      0,      0,      117,    118,    119,    0,      0,      0,
                 19,    0,      0,      0,      0,      8,      0,      0,      0,      0,      8,      20,     8,      0,      0,      3,
                 35,    101,    102,    103,    0,      20,     45,     45,     45,     17,     18,     18,     19,     0,      0,      17,
                 35,    117,    118,    119,    1,      36,     0,      1,      0,      33,     34,     34,     35,     0,      0,      33,
                 35,    75,     67,     0,      17,     18,     18,     19,     0,      33,     34,     34,     35,     0,      0,      33,
                 35,    17,     19,     8,      33,     34,     34,     35,     75,     33,     34,     34,     35,     1,      1,      33,
                 18,    18,     18,     18,     18,     18,     18,     18,     18,     18,     18,     18,     18,     18,     18,     18});
        welt.setTiles(new Tileset(sprites,256,0),1);
        Camera cam = new Camera();
    }
    @Override
    public void update(Input input) {

    }

    @Override
    public void render(RenderContext ctx) {
        Set<EntitySprite> renderable = new HashSet<>();

        tree.queryRange(new AABB(-1,-1,1,1),renderable);

        welt.render(ctx);

        for(EntitySprite e:renderable)
        {
            e.getSprite().draw(ctx,e.transform.pos.x,e.transform.pos.y,e.getWidth(),e.getHeight());
        }
    }
}
