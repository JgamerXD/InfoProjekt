package engine;

import engine.Input;
import engine.rendering.RenderContext;

import javax.swing.JFrame;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

/**
 * Created by JgamerXD on 19.08.2014.
 */
public class Display extends Canvas {

    private final JFrame            frame;
    private final RenderContext renderContext;
    private final BufferedImage     displayImage;
    private final byte[]            displayComponents;
    private final BufferStrategy    bufferStrategy;
    private final Graphics          graphics;
    private final Input input;

    public Display(int width, int height, String title)
    {
        Dimension size = new Dimension(width, height);
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);

        renderContext = new RenderContext(width, height);
        displayImage = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);

        displayComponents = ((DataBufferByte)displayImage.getRaster().getDataBuffer()).getData();

        frame = new JFrame();
        frame.add(this);
        frame.pack();
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        frame.setTitle(title);
        frame.setVisible(true);

        input = new Input();
        addKeyListener(input);
        addFocusListener(input);
        addMouseListener(input);
        addMouseMotionListener(input);

        createBufferStrategy(1);

        bufferStrategy = getBufferStrategy();
        graphics = bufferStrategy.getDrawGraphics();

        System.out.println("Display aspect: " + renderContext.getAspect() + " (" + renderContext.getWidth() + " / " + renderContext.getHeight() + ")");
    }

    public RenderContext getRenderContext() {
        return renderContext;
    }

    public Input getInput() {
        return input;
    }

    public void swapBuffers()
    {
        renderContext.copyToByteArray(displayComponents);
        graphics.drawImage(displayImage, 0, 0, getWidth(), getHeight(), null);
        bufferStrategy.show();
    }

}
