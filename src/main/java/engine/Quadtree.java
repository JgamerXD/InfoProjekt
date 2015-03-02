package engine;


import java.awt.geom.*;

import engine.entity.Entity;
import engine.entity.EntitySprite;
import engine.physics.AABB;
import engine.physics.PhysicsObject;
import engine.physics.Transform;
import engine.math.Vec2d;

import java.lang.Integer;
import java.lang.String;
import java.lang.System;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

/**
 * Created by JgamerXD on 30.08.2014.
 */
public class Quadtree<T extends IQuadtreeObject> {
    public static final int MAX_ENTITYS = 5;
    public static final int NW = 0;
    public static final int NE = 1;
    public static final int SW = 2;
    public static final int SE = 3;


    private List<T> objects;
    private Quadtree[] nodes;
    private AABB bound;
    private boolean hasSubnodes;
    /**
     * is the Quadtree the root node? (can be expanded)
     */

    public Quadtree(AABB bound)
    {
        this.bound = bound;
        objects = new ArrayList<>();
        nodes = new Quadtree[4];
        hasSubnodes = false;
    }

    public void addEntity(T entity)
    {
        AABB eaabb = entity.getAABB();
        if(bound.contains(eaabb))
        {
            if(objects.size() >= MAX_ENTITYS || hasSubnodes)
            {
                if(!hasSubnodes)
                {
                    List<T> objectsToRemove = new ArrayList<>();
                    for(T e : objects)
                    {
                        if(pushToChild(e))
                            objectsToRemove.add(e);

                    }
                    for(T e : objectsToRemove)
                    {
                        objects.remove(e);
                    }
                    hasSubnodes = true;
                }
                if(!pushToChild(entity))
                    objects.add(entity);
            }
            else
                objects.add(entity);
        }
        else {

            if(bound.minY < eaabb.minY)
                if(bound.minX < eaabb.minX)
                    pushThisToNode(NW);
                else
                    pushThisToNode(NE);
            else
                if(bound.minX < eaabb.minX)
                    pushThisToNode(SW);
                else
                    pushThisToNode(SE);

            addEntity(entity);
        }

    }
   /**
    * Pushes an @Entity to a child node of the @Quadtree if possible
    *
    * @param e: Entity to push
    * @return : success
    */
    private boolean pushToChild(T e)
    {
        int pos = getChildIndex(e.getAABB());

        if(pos != -1)
        {
            if(nodes[pos] == null) {
                nodes[pos] = new Quadtree(getChildBounds(pos));
            }
            nodes[pos].addEntity(e);
            return true;
        }
        return false;
    }

    private AABB getChildBounds(int index) {
        AABB childBounds = new AABB(0, 0, 0, 0);
        Vec2d center = bound.getCenter(Transform.DEFAULT);
        double halfWidth = bound.getWidth()/2;
        double halfHeight = bound.getHeight()/2;
        switch (index) {
            case NW:
                childBounds = new AABB(center.x + halfWidth,center.y - halfHeight, halfWidth, halfHeight);
                break;
            case SW:
                childBounds = new AABB(center.x + halfWidth,center.y + halfHeight, halfWidth, halfHeight);
                break;
            case NE:
                childBounds = new AABB(center.x - halfWidth,center.y - halfHeight, halfWidth, halfHeight);
                break;
            case SE:
                childBounds = new AABB(center.x - halfWidth,center.y + halfHeight, halfWidth, halfHeight);
                break;
            default:
                System.err.println("Invalid index for child AABB!");
        }
        return childBounds;
    }

    private int getChildIndex(AABB bounds)
    {
        for(int i = 0;i < 4;i++)
            if(getChildBounds(i).contains(bounds))
                return i;
        return -1;
    }

    public void queryRange(AABB bounds, Set<T> result)
    {
        for(T e : objects)
        {
            if(e.getAABB().intersects(bounds))
                result.add(e);
        }
        if(hasSubnodes){
            if(nodes[0] != null && nodes[0].bound.intersects(bounds))
                nodes[0].queryRange(bounds, result);
            if(nodes[1] != null && nodes[1].bound.intersects(bounds))
                nodes[1].queryRange(bounds, result);
            if(nodes[2] != null && nodes[2].bound.intersects(bounds))
                nodes[2].queryRange(bounds, result);
            if(nodes[3] != null && nodes[3].bound.intersects(bounds))
                nodes[3].queryRange(bounds, result);
        }
    }

    public void getAll(Set<T> result)
    {
        result.addAll(objects);
        if(hasSubnodes){
            if(nodes[0] != null)
                nodes[0].getAll(result);
            if(nodes[1] != null)
                nodes[1].getAll(result);
            if(nodes[2] != null)
               nodes[2].getAll(result);
            if(nodes[3] != null)
                nodes[3].getAll(result);
        }
    }

    public void get(Predicate<T> condition, Set<T> result) {
        objects.stream().filter(condition).forEach(result::add);
        if(hasSubnodes){
            if(nodes[0] != null)
                nodes[0].get(condition, result);
            if(nodes[1] != null)
                nodes[1].get(condition, result);
            if(nodes[2] != null)
                nodes[2].get(condition, result);
            if(nodes[3] != null)
                nodes[3].get(condition, result);
        }
    }
//
//    public void remove(Predicate<Entity> condition)
//    {
//        objects.removeIf(condition);
//        if(hasSubnodes){
//            for(int i = 0; i < nodes.length; i++)
//                if(nodes[i] != null) {
//                    nodes[i].remove(condition);
//                    if(nodes[i].isEmpty())
//                        nodes[i] = null;
//                }
//            checkHasSubnodes();
//        }
//
//    }
    private void checkHasSubnodes()
    {
        hasSubnodes = nodes[0] != null || nodes[1] != null || nodes[2] != null || nodes[3] != null;
    }
    public boolean getHasSubnodes()
    {
        return hasSubnodes;
    }

    public boolean hasEntities()
    {
        return (objects.size() != 0);
    }

    public boolean isEmpty()
    {
        return !hasEntities() && !getHasSubnodes();
    }

    public void print(int depth,String prefix)
    {
        System.out.println(prefix+"DEPTH: " + depth + " BOUNDS: " + bound + " NUM_ENTITYS: "+ objects.size());
/*        for(Entity e : objects)
        {
            System.out.println(prefix + e);
        }*/
        if(hasSubnodes)
            for(Quadtree q : nodes)
            {
                if(q != null)
                    q.print(depth+1,prefix + "-");
            }
    }


    protected void pushThisToNode(int node)
    {
        System.out.printf("Pushing this to node %d:\n",node);
        this.print(0,"");
        Quadtree clone = new Quadtree(new AABB(bound));
        if(hasSubnodes) {
            clone.nodes = nodes.clone();
            clone.hasSubnodes = true;
        }
        clone.objects = new ArrayList<>();
        clone.objects.addAll(objects);
        objects = new ArrayList<>();

        for(int i = 0; i< nodes.length; i++)
            if(i == node)
                nodes[i] = clone;
        else
            nodes[i] = null;



        AABB newBound = new AABB(0,0,0,0); //bound;
        double height = bound.getHeight(), width = bound.getWidth();
        switch (node) {
            case NE:
                newBound = new AABB(bound.minX - width  ,bound.minY - height    ,bound.maxX         ,bound.maxY);
                break;
            case NW:
                newBound = new AABB(bound.minX - width  ,bound.minY             ,bound.maxX         ,bound.maxY + height);
                break;
            case SE:
                newBound = new AABB(bound.minX          ,bound.minY - height    ,bound.maxX + width ,bound.maxY);
                break;
            case SW:
                newBound = new AABB(bound.minX          ,bound.minY             ,bound.maxX + width ,bound.maxY + height);
                break;
        }

        bound = newBound;

        System.out.println("expanded Quadtree bounds:");
        this.print(0,"");


    }
}
