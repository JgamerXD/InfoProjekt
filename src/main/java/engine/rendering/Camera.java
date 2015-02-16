package engine.rendering;

/**
 * Created by Jan on 13.02.2015.
 */
public class Camera {
    float x;
    float y;
    //scale, since it is only 2D
    float fov;

    public Camera(float x, float y, float fov) {
        this.x = x;
        this.y = y;
        this.fov = fov;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getFov() {
        return fov;
    }

    public void setFov(float fov) {
        this.fov = fov;
    }
}
