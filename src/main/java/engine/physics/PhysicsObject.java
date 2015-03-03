package engine.physics;

import engine.IQuadtreeObject;
import engine.math.Vec2d;

public class PhysicsObject implements IQuadtreeObject{
	public double mass;
	public Vec2d vel;
	public Vec2d acc;
	public Shape shape;
	public Transform transform;
	public boolean solid = true;
	
	public PhysicsObject(double mass, Shape shape, Transform transform) {
		this.mass = mass;
		this.shape = shape;
		this.transform = transform;
	}
	
	public void update(double dt)
	{
		vel.scale(dt);
	}
	
	public double getMass() {
		return mass;
	}
	public void setMass(double mass) {
		this.mass = mass;
	}
	public Shape getShape() {
		return shape;
	}
	public void setShape(Shape shape) {
		this.shape = shape;
	}
	public Transform getTransform() {
		return transform;
	}
	public void setTransform(Transform transform) {
		this.transform = transform;
	}

	public AABB getAABB()
	{
		return shape.getAABB(transform);
	}
}
