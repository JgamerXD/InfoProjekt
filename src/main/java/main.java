import engine.Engine;
import engine.math.Vec2d;
import engine.rendering.Camera;
import game.Spiel;

/**
 * Created by Jan on 14.02.2015.
 */
public class main {
    public static void main(String[] args)
    {
        Engine engine = new Engine(new Spiel());
        engine.setCamera(new Camera(5,new Vec2d(8.0,4.0)));
        engine.run();
    }
}
