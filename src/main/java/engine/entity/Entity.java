package engine.entity;

import engine.Input;
import engine.Util;
import engine.math.Vec2d;
import engine.physics.AABB;
import engine.physics.Transform;
import engine.rendering.RenderContext;
import engine.rendering.Sprite;
import engine.world.World;

import java.util.HashMap;

/**
 * Created by Jan on 14.02.2015.
 */
public class Entity {
    public Transform transform;
    World world;

    //Velocity
    Vec2d vel = new Vec2d();
    //Acceleration
    Vec2d acc = new Vec2d();
    double dx, dy;

    //How much is the Entity affected by external forces (e.g. gravity)
    double forceScale = 1.0;

    Sprite sprite;
    AABB bounds =  new AABB(0,0,1,1);


    public void update(Input input)
    {
        //start Movement
        dx = 0.0;
        dy = 0.0;


        vel.add(acc.scale(input.getDelta()));
        vel.add(world.getForce((int)(transform.pos.x),(int)(transform.pos.y)).scale(forceScale));


        vel = vel.scale(1 - 0.10 * input.getDelta());

//        vel.x = Util.clamp(vel.x, -10, 10);
//        vel.y = Util.clamp(vel.y,-10,10);
        if(Math.abs(vel.x) < 0.04)
            vel.x = 0.0;
        if(Math.abs(vel.y) < 0.04)
            vel.y = 0.0;

        Vec2d dMov = vel.scale(input.getDelta());


        dx = getMovDistX(dMov.x);
        if(Math.abs(dx) < Math.abs(dMov.x))
            vel.x = 0.0;
        dy = getMovDistY(dMov.y);
        if(Math.abs(dy) < Math.abs(dMov.y))
            vel.y = 0.0;

        transform.pos.add(dx,dy);



    }


    public void render(RenderContext ctx)
    {
        sprite.draw(ctx, transform.pos.x, transform.pos.y, bounds.getWidth(), bounds.getHeight());
    }

    public boolean onGround()
    {
        return false;
        //TODO: Implement
    }

    /**
     * Calculates the distance to move
     * @param dist distance the object want's to move on X
     * @return distance, the object can move without intersecting a wall or solid entity
     */
    double getMovDistX(double dist)
    {
        if(dist == 0) //No movement on x
            return 0.0;

        //intersecting tiles
        int minYT, maxYT,startXT, endXT;
        minYT = (int)(bounds.minY + transform.pos.y);
        maxYT = (int)(bounds.maxY + transform.pos.y);

        double x = (dist > 0 ? bounds.maxX : bounds.minX);
        x +=  transform.pos.x;

        startXT = (int) (x);
        endXT = (int) (x + dist);


//        if(startXT == endXT) //no tile change TODO: change/remove when implementing slopes
//        {
//            return dist;
//        }
        if(dist > 0)
        {
            for(int i = startXT; i <= endXT; i++)
            {
                for(int j = minYT;j <= maxYT; j++)
                {
                    if(world.getTileAt(i,j).isSolid())
                    {
                        dist = Math.min(i - startXT - x % 1 ,dist);
                    }
                }
            }
        }
        else
        {
            for(int i = startXT; i >= endXT; i--)
            {
                for(int j = minYT;j <= maxYT; j++)
                {
                    if(world.getTileAt(i,j).isSolid())
                    {
                        dist = Math.max(i - startXT + 1 - x % 1,dist);
                    }
                }
            }
        }
        return dist;
    }

    /**
     * Calculates the distance to move
     * @param dist distance the object want's to move on Y
     * @return distance, the object can move without intersecting a wall or solid entity
     */
    double getMovDistY(double dist)
    {
        {
            if(dist == 0) //No movement on y
                return 0.0;

            //intersecting tiles
            int minXT, maxXT,startYT, endYT;
            minXT = (int)(bounds.minX + transform.pos.x);
            maxXT = (int)(bounds.maxX + transform.pos.x);

            double y = (dist > 0 ? bounds.maxY : bounds.minY);
            y +=  transform.pos.y;

            startYT = (int) (y);
            endYT = (int) (y + dist);


//        if(startYT == endYT) //no tile change TODO: change/remove when implementing slopes
//        {
//            return dist;
//        }
            if(dist > 0)
            {
                for(int i = startYT; i <= endYT; i++)
                {
                    for(int j = minXT;j <= maxXT; j++)
                    {
                        if(world.getTileAt(j,i).isSolid())
                        {
                            dist = Math.min(i - startYT - y % 1 ,dist);
                        }
                    }
                }
            }
            else
            {
                for(int i = startYT; i >= endYT; i--)
                {
                    for(int j = minXT;j <= maxXT; j++)
                    {
                        if(world.getTileAt(j,i).isSolid())
                        {
                            dist = Math.max(i - startYT + 1 - y % 1,dist);
                        }
                    }
                }
            }
            return dist;
        }
    }
}
