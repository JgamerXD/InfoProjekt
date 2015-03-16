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
    
    public Vec2d add(Vec2d translation)
    {
        x += translation.x;
        y += translation.y;
        return this;
    }
    public Vec2d add(double x,double y)
    {
        this.x += x;
        this.y += y;
        return this;
    }
    public Vec2d sub(Vec2d translation)
    {
        x -= translation.x;
        y -= translation.y;
        return this;
    }
     
    public double length() {
       return Math.sqrt(x*x+y*y);
    }

    public double lengthSq() {
        return x*x+y*y;
    }

    public Vec2d scale( double scaleFactor ) {
   	 	Vec2d v2 = new Vec2d( x*scaleFactor, y*scaleFactor );
        return v2;
    }

     // Normalize a vectors length....

    public Vec2d normalize() {
    	Vec2d v2 = new Vec2d();

        double length = Math.sqrt( x*x + y*y );
        if (length != 0) {
          v2.x = x/length;
          v2.y = y/length;
        }

        return v2;
    }   

     // Dot product of two vectors .....

     public double dotProduct ( Vec2d other ) {
          return x*other.x + y*other.y;
     }
}
