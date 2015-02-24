package engine.rendering;

import engine.entity.Entity;
import engine.math.Vec2d;
import engine.physics.AABB;

/**
 * Created by Jan on 13.02.2015.
 */
public class Camera {
	Vec2d translation;
	double scale;

    public Camera() {
        translation = new Vec2d(0,0);
        scale = 1;
    }

    public Camera(double scale) {
        this.scale = scale;
        translation = new Vec2d(0,0);
    }

    public Camera(double scale,Vec2d translation)
	{
		this.scale = scale;
        this.translation = translation;
	}

    public Vec2d getTranslation()
    {
        return translation;
    }

    public double getScale()
    {
        return scale;
    }

    public void setTranslation(Vec2d translation) {
        this.translation = translation;
    }

    public void setScale(double scale)
	{
		this.scale = scale;
	}
}
