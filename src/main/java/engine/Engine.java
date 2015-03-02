package engine;


import engine.Display;
import engine.Game;
import engine.Options;
import engine.Time;
import engine.rendering.Camera;
import engine.rendering.RenderContext;

import javax.swing.*;

/**
 * Created by JgamerXD on 30.08.2014.
 */
public class Engine {
    private Game game;
    private Camera cam;
    public Engine(Game game) {
        this.game = game;
    }

    public void setCamera(Camera cam)
    {
        this.cam = cam;
    }
    public void run()
    {
        Options.save();
        Display display = new Display(game,Options.RES_X, Options.RES_Y, "Info Projekt");

        if(cam != null)
            display.cam = cam;

        Time time = new Time(Options.SHOW_FPS);

        while (true) {
            display.getInput().update(time.getDelta());
            game.update(display.getInput());
            display.render();
            display.swapBuffers();
            time.sleep(1);
        }
    }
}
