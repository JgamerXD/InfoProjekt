package engine.rendering;

/**
 * Created by Jan on 15.02.2015.
 */
public class Spritesheet {
    Bitmap image;
    int rows,columns;

    public Spritesheet(Bitmap image, int rows, int columns) {
        this.image = image;
        this.rows = rows;
        this.columns = columns;
    }

    private double getXStart(int index)
    {
        if(index%columns == 0)
            return 0;
        return (double)(index % columns) / columns;
    }
    private double getXEnd(int index)
    {
        return (index % columns + 1.0) / columns;
    }
    private double getYStart(int index)
    {
        if((int)index/ rows == 0)
            return 0;
        return Math.floor(index / columns) / rows;
    }
    private double getYEnd(int index)
    {
        return Math.floor(index / columns + 1.0) / rows;
    }

    public void draw(int i,RenderContext ctx,double xCenter, double yCenter, double width, double height)
    {
        ctx.drawImage(image,xCenter,yCenter,width,height,getXStart(i),getYStart(i),getXEnd(i),getYEnd(i));
    }
}
