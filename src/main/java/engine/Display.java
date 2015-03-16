package engine;

import engine.Input;
import engine.rendering.Camera;
import engine.rendering.RenderContext;
import javafx.scene.canvas.GraphicsContext;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.util.function.Consumer;

/**
 * Created by JgamerXD on 19.08.2014.
 */
public class Display extends Canvas {

    private final JFrame            frame;
    private final RenderContext     renderContext;
//    private final BufferedImage     displayImage;
//    private final byte[]            displayComponents;
    private final BufferStrategy    bufferStrategy;
    private final Input input;
    private final Game game;
    public Camera cam;

    public Display(Game game,int width, int height, String title)
    {
        Dimension size = new Dimension(width, height);
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
        setIgnoreRepaint(true);

        this.game = game;

        renderContext = new RenderContext();
//        displayImage = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
//
//        displayComponents = ((DataBufferByte)displayImage.getRaster().getDataBuffer()).getData();

        frame = new JFrame();
        frame.setIgnoreRepaint(true);
        frame.add(this);
        frame.pack();
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        frame.setTitle(title);
        frame.setVisible(true);

        input = new Input();
        addKeyListener(input);
        addFocusListener(input);
        addMouseListener(input);
        addMouseMotionListener(input);

        createBufferStrategy(2);

        bufferStrategy = getBufferStrategy();
//        graphics = bufferStrategy.getDrawGraphics();

        //System.out.println("Display aspect: " + renderContext.getAspect() + " (" + renderContext.getWidth() + " / " + renderContext.getHeight() + ")");
    }

//    public RenderContext getRenderContext() {
//        return renderContext;
//    }

    public Input getInput() {
        return input;
    }

   public void swapBuffers()
    {

//        renderContext.copyToByteArray(displayComponents);
//        graphics.drawImage(displayImage, 0, 0, getWidth(), getHeight(), null);
//        bufferStrategy.show();
    }

    public void render() {// Render single frame
        do {
            // The following loop ensures that the contents of the drawing buffer
            // are consistent in case the underlying surface was recreated
            do {
                // Get a new graphics context every time through the loop
                // to make sure the strategy is validated
                Graphics graphics = bufferStrategy.getDrawGraphics();
                graphics.clearRect(0,0,getWidth(),getHeight());

                // Render to graphics
                // ...
                renderContext.update((Graphics2D)graphics,getWidth(),getHeight());
                game.render(renderContext);
                // Dispose the graphics
                graphics.dispose();

                // Repeat the rendering if the drawing buffer contents
                // were restored
            } while (bufferStrategy.contentsRestored());

            // Display the buffer
            bufferStrategy.show();

            // Repeat the rendering if the drawing buffer was lost
        } while (bufferStrategy.contentsLost());
    }

}
