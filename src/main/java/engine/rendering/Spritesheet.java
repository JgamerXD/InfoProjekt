package engine.rendering;

import java.awt.*;

/**
 * Created by Jan on 15.02.2015.
 */
public class Spritesheet {
    Image image;
    int rows,columns;

    public Spritesheet(Image image, int columns, int rows) {
        this.image = image;
        this.rows = rows;
        this.columns = columns;
    }

    public int getSprites()
    {
        return rows * columns;
    }

    private int getXStart(int index)
    {
        if(index%columns == 0)
            return 0;
        return (int)((double)(index % columns) / columns * image.getWidth(null));
    }
    private int getXEnd(int index)
    {
        return (int)((index % columns + 1.0) / columns * image.getWidth(null));
    }
    private int getYStart(int index)
    {
        if((int)index / rows == 0)
            return 0;
        return (int)(Math.floor(index / columns) / rows * image.getHeight(null));
    }
    private int getYEnd(int index)
    {
        return (int)(Math.floor(index / columns + 1.0) / rows * image.getHeight(null));
    }

    public void draw(int i,RenderContext ctx,double xCenter, double yCenter, double width, double height)
    {
        ctx.drawImage(image,xCenter,yCenter,width,height,getXStart(i),getYStart(i),getXEnd(i),getYEnd(i));
    }
}
