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

    double controlFactor = 2;
    double runningSpeed = 10;
    double runAcc = 10;
    public Player(World welt)
    {
        transform = new Transform(new Vec2d(19,2));
        world = welt;
        sprite = new ImageSprite(ResourceManager.loadImage("/textures/Player.png"));
        forceScale = 1;
        bounds = new AABB(0,0,1,2);
    }

    @Override
    public void update(Input input) {
        super.update(input);
        if(input.getKey(KeyEvent.VK_A))
        {
            Vec2d mov = new Vec2d(-runningSpeed * 2 - vel.x,0).scale(input.getDelta());
            if(vel.x > 0)
                mov.scale(controlFactor);
            vel.add(mov);
        }
        if(input.getKey(KeyEvent.VK_D))
        {
            Vec2d mov = new Vec2d(runningSpeed * 2 - vel.x,0).scale(input.getDelta());
            if(vel.x < 0)
                mov.scale(controlFactor);
            vel.add(mov);
        }
//        if(input.getKey(KeyEvent.VK_W))
//        {
//
//            vel.add(new Vec2d(0,-10).scale(input.getDelta()));
//        }
//        if(input.getKey(KeyEvent.VK_S))
//        {
//            vel.add(new Vec2d(0,10).scale(input.getDelta()));
//        }
        if(input.getKey(KeyEvent.VK_SPACE) && onGround())
        {
            vel.add(new Vec2d(0,-10));
        }
    }
}
