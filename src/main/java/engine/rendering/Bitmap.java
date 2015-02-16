package engine.rendering;

import engine.Util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.nio.file.Path;
import java.util.Arrays;
import java.io.*;
import java.awt.image.BufferedImage;
import java.net.URL;

/**
 * Created by JgamerXD on 19.08.2014.
 */
public class Bitmap {
    private final int width;
    private final int height;
    /** Every pixel component in the image */
    private final byte components[];
    public static final Bitmap MISSING = new Bitmap(4,4);

    public int getWidth() { return width; }
    public int getHeight() { return height; }
    public float getAspect() {
        return (float)width/(float)height;
    }
    public void setComponent(int location, byte value)
    {
        components[location] = value;
    }
    /**
     * Creates and initializes a Bitmap.
     *
     * @param width The width, in pixels, of the image.
     * @param height The height, in pixels, of the image.
     */
    public Bitmap(int width, int height)
    {
        this.width = width;
        this.height = height;
        components = new byte[this.width * this.height * 4];
    }


    /**
     * Loads an image into a Bitmap from a file.
     *
     * @param fileName
     * The name of the file to be loaded. File paths are relative to
     * the resource folder, so a file in
     * "C:/myFolder/myProject/projectResourceFolder/textures/myImage.png"
     * should be specified as "textures/myImage.png"
     */
    public Bitmap(String fileName) {
        int width;
        int height;
        byte[] components;
        try {
//            File resource =  new File(fileName);
//            if (!resource.exists()) throw new FileNotFoundException( resource.getAbsolutePath()+ " is not an accessible resource.");
            InputStream resource = this.getClass().getResourceAsStream(fileName);//Bitmap.class.getResource("./" + fileName);
            if (resource == null) throw new FileNotFoundException( fileName + " is not an accessible resource.");
            BufferedImage image = ImageIO.read(resource);
            width = image.getWidth();
            height = image.getHeight();
            int[] pixels = new int[width * height];
            image.getRGB(0, 0, width, height, pixels, 0, width);
            components = new byte[width * height * 4];
            for (int i = 0; i < width * height; i++) {
                components[i * 4]     = (byte) ((pixels[i] >> 24) & 0xFF);
                components[i * 4 + 1] = (byte) ((pixels[i]      ) & 0xFF);
                components[i * 4 + 2] = (byte) ((pixels[i] >> 8 ) & 0xFF);
                components[i * 4 + 3] = (byte) ((pixels[i] >> 16) & 0xFF);
            }
        } catch (Exception e) {
            e.printStackTrace();
            width       = 4;
            height      = 4;
            components  = new byte[width * height *4];
            fillError(components,width,height);
        }
        this.width = width;
        this.height = height;
        this.components = components;
    }
    /**
     * Sets every pixel in the bitmap to a specific shade of grey.
     */
    public void clear(byte shade)
    {
        Arrays.fill(components, shade);
    }
    /**
     * Sets the pixel at (x, y) to the color specified by (a,b,g,r).
     */
    public void drawPixel(int x, int y, byte a, byte b, byte g, byte r)
    {
        int index = (x + y * width) * 4;
        components[index ] = a;
        components[index + 1] = b;
        components[index + 2] = g;
        components[index + 3] = r;
    }
    public void safeDrawPixel(int x, int y, byte a, byte b, byte g, byte r)
    {
        int clampEnd = getWidth() < getHeight() ? getWidth() : getHeight();
        int clampStartY = (clampEnd - getHeight()) / -2;
        int clampStartX = (clampEnd - getWidth()) / -2;
        if(x < clampStartX || y < clampStartY
                || x >= (clampEnd + clampStartX)
                || y >= (clampEnd + clampStartY))
        {
            return;
        }
        drawPixel(x, y, a, b, g, r);
    }
    /**
     * Copies the Bitmap into a BGR byte array.
     */
    public void copyToByteArray(byte[] dest)
    {
        for(int i = 0; i < width * height; i++)
        {
            dest[i * 3 ] = components[i * 4 + 1];
            dest[i * 3 + 1] = components[i * 4 + 2];
            dest[i * 3 + 2] = components[i * 4 + 3];
        }
    }
    public void copyNearest(Bitmap dest, int destX, int destY, double srcXFloat, double srcYFloat)
    {

        int srcX = (int)(Util.clamp(srcXFloat, 0, 0.999999f) * getWidth());
        int srcY = (int)(Util.clamp(srcYFloat,0,0.999999f) * getHeight());

        int destIndex = (destX+destY*dest.getWidth())*4;
        int srcIndex = (srcX+srcY* getWidth())*4;
        dest.setComponent(destIndex, components[srcIndex]);
        dest.setComponent(destIndex + 1, components[srcIndex + 1]);
        dest.setComponent(destIndex + 2, components[srcIndex + 2]);
        dest.setComponent(destIndex + 3, components[srcIndex + 3]);
    }

    public Bitmap clear(byte a, byte b, byte g, byte r)
    {
        for(int i = 0; i < getWidth() * getHeight(); i++)
        {
            setComponent(i * 4, a);
            setComponent(i * 4 + 1, b);
            setComponent(i * 4 + 2, g);
            setComponent(i * 4 + 3, r);
        }
        return this;
    }

    private void fillError(byte[] components,int width, int height)
    {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Color c = (i+1 < width / 2 ^ j+1 < height / 2 ? Color.BLACK : Color.MAGENTA);
                components[(i + j * width) * 4]     = (byte) 0xFF;
                components[(i + j * width) * 4 + 1] = (byte) c.getBlue();
                components[(i + j * width) * 4 + 2] = (byte) c.getGreen();
                components[(i + j * width) * 4 + 3] = (byte) c.getRed();
            }
        }
    }
}
