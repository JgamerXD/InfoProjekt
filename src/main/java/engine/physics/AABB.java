package engine.physics;

import engine.math.Vec2d;

/**
 * Created by JgamerXD on 30.08.2014.
 */
public class AABB implements Shape {

    public double minX,minY,maxX,maxY;

    public AABB()
    {
        this.minX = 0.0;
        this.minY = 0.0;
        this.maxX = 0.0;
        this.maxY = 0.0;
    }

    public AABB(AABB other)
    {
        this.minX = other.minX;
        this.minY = other.minY;
        this.maxX = other.maxX;
        this.maxY = other.maxY;
    }

    public AABB(double minX, double minY, double maxX, double maxY) {
        this.minX = minX;
        this.minY = minY;
        this.maxX = maxX;
        this.maxY = maxY;
    }


    public void translate(double xDir, double yDir)
    {
        minX += xDir;
        maxX += xDir;
        minY += yDir;
        maxY += yDir;
    }

    public void translate(Vec2d translation)
    {
        translate(translation.x,translation.y);
    }

    public boolean contains(AABB other)
    {
        return (minX <= other.minX
                &&  minY <= other.minY
                &&  maxX >= other.maxX
                &&  maxY >= other.maxY);
    }

    public boolean contains(double minX, double minY, double maxX, double maxY)
    {
        return (this.minX <= minX
            &&  this.minY <= minY
            &&  this.maxX >= maxX
            &&  this.maxY >= maxY);
    }

    public boolean intersects(AABB other)
    {
        return !(minX >= other.maxX ||
                minY >= other.maxY ||
                other.minX >= maxX ||
                other.minY >= maxY);
    }


    public String toString()
    {
        return (minX + ", " + minY + ", " + maxX + ", " + maxY);
    }


    @Override
    public Types getType() {
        return Types.AABB;
    }

    @Override
    public AABB getAABB(Transform t) {
        AABB aabb = new AABB(this);
        aabb.translate(t.pos);
        return aabb;
    }

    @Override
    public Vec2d getCenter(Transform t) {
        return new Vec2d((minX+maxX)/2,(minY+maxY)/2);
    }

    public double getWidth()
    {
        return maxX-minX;
    }

    public double getHeight()
    {
        return maxY-minY;
    }
}
