package engine.rendering;

import com.sun.prism.Graphics;
import engine.Game;
import engine.entity.EntitySprite;
import engine.physics.AABB;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by Jan on 25.02.2015.
 */
public class GameCanvas extends JPanel {

    private Game game;
    private RenderContext context;
    private Map<RenderingHints.Key,Object> renderingHints = new HashMap<>();

    public GameCanvas(Game game)
    {
        this.game = game;
    }

    public void setRenderingHint(RenderingHints.Key k,Object v)
    {
        renderingHints.put(k,v);
    }


    public void paint(Graphics g)
    {
        Graphics2D g2d = (Graphics2D)g;
        g2d.setRenderingHints(renderingHints);
        //g2d.setBackground(new Color(31, 31, 47, 254));
        context.setGraphics(g2d);
        game.render(g2d);
    }
}
