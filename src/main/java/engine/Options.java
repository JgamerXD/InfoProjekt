package engine;

import java.awt.Color;

import engine.config.Configuration;

/**
 * Created by Jan on 17.10.2014.
 */
public class Options {

    private static Configuration config = new Configuration("options.properties");

    public static int       RES_X       = config.getInteger("resX" , 800);
    public static int       RES_Y       = config.getInteger("resY" , 600);

    public static boolean   SHOW_FPS    = config.getBoolean("showFPS", false);
    public static boolean   DEBUG       = config.getBoolean("debug" , false);
    
    public static Color BACKGROUND 		= config.getColor("background", new Color(0x60,0x80,0xFF));

    public static void save()
    {
        config.save();
    }
}
