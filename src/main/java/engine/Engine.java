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
    private Display display;
    private boolean running = true;
    private boolean paused = false;


    public Engine(Game game) {
        this.game = game;
    }

    public void setCamera(Camera cam)
    {
        display.cam = cam;
    }
    public void run() {
        Options.save();
        display = new Display(game, Options.RES_X, Options.RES_Y, "Info Projekt");
        display.setBackground(Options.BACKGROUND);


        Thread loop = new Thread("game loop") {
            public void run() {
                gameLoop();
            }
        };
        loop.start();


    }
    //Only run this in another Thread!
    private void gameLoop()
    {
        Time time = new Time(Options.SHOW_FPS);
        Input input = display.getInput();
        //This value would probably be stored elsewhere.
        final double GAME_HERTZ = 30.0;
        //Calculate how many ns each frame should take for our target game hertz.
        final double TIME_BETWEEN_UPDATES = 1000000000 / GAME_HERTZ;
        //At the very most we will update the game this many times before a new render.
        //If you're worried about visual hitches more than perfect timing, set this to 1.
        final int MAX_UPDATES_BEFORE_RENDER = 5;
        //We will need the last update time.
        double lastUpdateTime = System.nanoTime();
        //Store the last time we rendered.
        double lastRenderTime = System.nanoTime();

        //If we are able to get as high as this FPS, don't render again.
        final double TARGET_FPS = 60;
        final double TARGET_TIME_BETWEEN_RENDERS = 1000000000 / TARGET_FPS;

        //Simple way of finding FPS.
        int lastSecondTime = (int) (lastUpdateTime / 1000000000);

        input.update(1/GAME_HERTZ);

        while (running)
        {
            double now = System.nanoTime();
            double delta = time.getDelta();


            int updateCount = 0;

            if (!paused)
            {
                //Do as many game updates as we need to, potentially playing catchup.
                while( now - lastUpdateTime > TIME_BETWEEN_UPDATES && updateCount < MAX_UPDATES_BEFORE_RENDER )
                {

                    game.update(display.getInput());
                    lastUpdateTime += TIME_BETWEEN_UPDATES;
                    updateCount++;
                }

                //If for some reason an update takes forever, we don't want to do an insane number of catchups.
                //If you were doing some sort of game that needed to keep EXACT time, you would get rid of this.
                if ( now - lastUpdateTime > TIME_BETWEEN_UPDATES)
                {
                    lastUpdateTime = now - TIME_BETWEEN_UPDATES;
                }

                //Render. To do so, we need to calculate interpolation for a smooth render.
                float interpolation = Math.min(1.0f, (float) ((now - lastUpdateTime) / TIME_BETWEEN_UPDATES) );
                display.render();
                lastRenderTime = now;


                //Yield until it has been at least the target time between renders. This saves the CPU from hogging.
                while ( now - lastRenderTime < TARGET_TIME_BETWEEN_RENDERS && now - lastUpdateTime < TIME_BETWEEN_UPDATES)
                {
                    Thread.yield();

                    //This stops the app from consuming all your CPU. It makes this slightly less accurate, but is worth it.
                    try {Thread.sleep(1);} catch(Exception e) {}

                    now = System.nanoTime();
                }
            }
        }
    }
}
