package engine;


import java.awt.geom.Rectangle2D;

/**
 * Created by JgamerXD on 24.08.2014.
 */
public class Util {
    public static double clamp(double val, double min, double max) {

        if (val < min) {
            return min;
        }
        if (val > max) {
            return max;
        }
        return val;
    }

    public String Rect2DToString(Rectangle2D r)
    {
        return (r.getMinX() + ", " + r.getMinY() + ", " + r.getMaxX() + ", " + r.getMaxY());
    }
}
