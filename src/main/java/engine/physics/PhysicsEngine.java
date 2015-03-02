package engine.physics;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import engine.Quadtree;
import engine.entity.EntityPhysical;

/**
 * Created by Jan on 15.02.2015.
 */
public class PhysicsEngine {
	private Quadtree<PhysicsObject> objects;
	
	private void update(double delta)
	{
		Set<PhysicsObject> set = new HashSet();
		objects.getAll(set);
		
		
		objects.get((p -> p.mass < 0), set);
		
		Set<PhysicsObject> collided = new HashSet();
		
	}
	
	public void resolveCollisions(PhysicsObject obj)
	{
		
	}
	
}
