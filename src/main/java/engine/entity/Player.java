package engine.entity;

import engine.Input;
import engine.ResourceManager;
import engine.Util;
import engine.math.Vec2d;
import engine.physics.AABB;
import engine.physics.Transform;
import engine.rendering.Camera;
import engine.rendering.ImageSprite;
import engine.rendering.PlayerCamera;
import engine.world.World;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by Jan on 08.03.2015.
 */
public class Player extends Entity{

    public Player(World welt)
    {
        transform = new Transform(new Vec2d(9,2));
        world = welt;
        sprite = new ImageSprite(ResourceManager.loadImage("/textures/Player.png"));
        forceScale = 0;
        bounds = new AABB(0,0,1,2);
    }

    @Override
    public void update(Input input) {
        super.update(input);
        if(input.getKey(KeyEvent.VK_A))
        {
            vel.add(new Vec2d(-10,0).scale(input.getDelta()));
        }
        if(input.getKey(KeyEvent.VK_D))
        {
            vel.add(new Vec2d(10,0).scale(input.getDelta()));
        }
        if(input.getKey(KeyEvent.VK_W))
        {
            vel.add(new Vec2d(0,-10).scale(input.getDelta()));
        }
        if(input.getKey(KeyEvent.VK_S))
        {
            vel.add(new Vec2d(0,10).scale(input.getDelta()));
        }
        if(input.getKey(KeyEvent.VK_SPACE))
        {
            vel.add(new Vec2d(0,-100).scale(input.getDelta()));
        }
    }
}
