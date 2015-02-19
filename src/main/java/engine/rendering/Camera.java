package engine.rendering;

import engine.entity.Entity;
import engine.math.Vec2d;
import engine.physics.AABB;

/**
 * Created by Jan on 13.02.2015.
 */
public class Camera {
	private Entity fokus;
	private double scale;
	public Camera(double scale,Entity fokus)
	{
		this.scale = scale;
		this.fokus = fokus;
	}
	
	public AABB getRenderArea(double aspect)
	{
		Vec2d pos = fokus.transform.pos;
		return new AABB(-scale * aspect + pos.x,-scale + pos.y,scale * aspect + pos.x,scale + pos.y);
	}
	
	public void setFokus(Entity entity)
	{
		fokus = entity;
	}
	
	public void setScale(double scale)
	{
		this.scale = scale;
	}
}
