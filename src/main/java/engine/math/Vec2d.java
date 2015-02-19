package engine.math;

public class Vec2d {
	public double x,y;
	public Vec2d()
	{
		
	}
	public Vec2d(double x, double y)
	{
		this.x = x;
		this.y = y;
	}
	public Vec2d(Vec2d other)
	{
		x = other.x;
		y = other.y;
	}
	
    public Vec2d rotate(float angle)
    {
       x = (float)Math.cos(angle) - x;
       y = (float)Math.sin(angle) + y;
        return this;
    }
    
    public Vec2d translate(Vec2d translation)
    {
        x += translation.x;
        y += translation.y;
        return this;
    }
}
