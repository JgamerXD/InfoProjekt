import engine.Engine;
import game.Spiel;

/**
 * Created by Jan on 14.02.2015.
 */
public class main {
    public static void main(String[] args)
    {
        Engine engine = new Engine(new Spiel());
        engine.run();
    }
}
