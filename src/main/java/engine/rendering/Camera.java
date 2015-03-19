package engine.rendering;

import engine.entity.Entity;
import engine.math.Vec2d;
import engine.physics.AABB;

import java.awt.geom.AffineTransform;

/**
 * Created by Jan on 13.02.2015.
 */
public class Camera extends AffineTransform{
    public Camera() {
        setToIdentity();
    }

    public Camera(double scale) {
        setToIdentity();
        scale(scale,scale);
    }

    public Camera(double scale,Vec2d translation)
	{
        setToIdentity();
        scale(scale,scale);
        translate(translation.x,translation.y);


	}

    public void setPos(double x,double y)
    {
        //TODO: find a working solution
        translate(getTranslateX() - x * getScaleX(),getTranslateY()-y * getScaleY());

    }
}
