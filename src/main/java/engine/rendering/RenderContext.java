package engine.rendering;

import engine.math.Vec2d;
import engine.physics.AABB;
import engine.Util;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;

/**
 * Created by JgamerXD on 24.08.2014.
 */
public class RenderContext
{
    private Camera camera = new Camera(0.5, new Vec2d(50,400));
    public Graphics2D g2d;
    public int width, height;

    public Camera getCamera() {
        return camera;
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }

    public Graphics2D getG2d() {
        return g2d;
    }

    public void update(Graphics2D g2d, int height, int width) {
        this.g2d = g2d;
        this.width = width;
        this.height = height;
    }


    public void drawImage(Image image, double xCenter, double yCenter, double width, double height) {
        Point2D pos = new Point2D.Double(xCenter,yCenter);
        camera.transform(pos,pos);
        width /= camera.getScaleX();
        height /= camera.getScaleY();
        g2d.drawImage(image, (int) (xCenter - width), (int) (yCenter - height), (int) (xCenter + width), (int) (yCenter + height), null);
    }
    public void drawImage(Image image, double xCenter, double yCenter, double width, double height,
                          int imageXStart, int imageYStart, int imageXEnd, int imageYEnd) {
        Point2D pos = new Point2D.Double(xCenter,yCenter);
        camera.transform(pos,pos);
        width *= camera.getScaleX() * 0.5;
        height *= camera.getScaleY() * 0.5;
        g2d.drawImage(image,(int)(pos.getX()-width),(int)(pos.getY()-height),(int)(pos.getX()+width),(int)(pos.getY()+height),imageXStart,imageYStart,imageXEnd,imageYEnd,null);
    }

//
//    public AABB getRenderArea()
//    {
//        double aspect = g2d.getTransform().;
//        return new AABB(-camera.scale * aspect + camera.translation.x,-camera.scale +camera. translation.y,
//                camera.scale * aspect + camera.translation.x,camera.scale + camera.translation.y);
//    }
//

//        //Apply Camera
//        width = width / camera.scale;
//        height = height / camera.scale;
//        Vec2d trans = camera.translation;
//        xCenter = (xCenter - trans.x) / camera.scale;
//        yCenter = (yCenter - trans.y) / camera.scale;
//
//
//        //Begin clipping logic
//        imageXStart = Util.clamp(imageXStart, -1.0, 1.0);
//        imageYStart = Util.clamp(imageYStart, -1.0, 1.0);
//        imageXEnd = Util.clamp(imageXEnd, -1.0, 1.0);
//        imageYEnd = Util.clamp(imageYEnd, -1.0, 1.0);
//
//        //Position auf Ziel
//        double xStart = xCenter / getAspect() - width / getAspect() / 2.0f;    //Left
//        double yStart = yCenter - height / 2.0f;                 //Bottom
//        double xEnd = xStart + width / getAspect();              //Right
//        double yEnd = yStart + height;                           //Top
//
//        double halfWidth = getWidth() / 2.0f;
//        double halfHeight = getHeight() / 2.0f;
//
//        //Position auf Bild
//        double imageYStep = (imageYEnd - imageYStart) / (((yEnd * halfHeight) + halfHeight)
//                - ((yStart * halfHeight) + halfHeight));
//        double imageXStep = (imageXEnd - imageXStart) / (((xEnd * halfWidth) + halfWidth)
//                - ((xStart * halfWidth) + halfWidth));
//
//        if (xStart < -1.0) {
//            imageXStart = imageXStart-((xStart + 1.0) / (xEnd - xStart));
//            xStart = -1.0f;
//        }
//        if (xStart > 1.0) {
//            imageXStart = imageXStart-((xStart + 1.0) / (xEnd - xStart));
//            xStart = 1.0;
//        }
//        if (yStart < -1.0) {
//            imageYStart = imageYStart-((yStart + 1.0) / (yEnd - yStart));
//            yStart = -1.0;
//        }
//        if (yStart > 1.0) {
//            imageYStart = imageYStart-((yStart + 1.0) / (yEnd - yStart));
//            yStart = 1.0;
//        }
//        xEnd = Util.clamp(xEnd, -1.0, 1.0);
//        yEnd = Util.clamp(yEnd, -1.0, 1.0);
//
//        //System.out.println("drawing area relative: " + xStart + " " + yStart + " " + xEnd + " " + yEnd);
//        xStart  = (xStart * halfWidth)  + halfWidth;
//        yStart  = (yStart * halfHeight) + halfHeight;
//        xEnd    = (xEnd * halfWidth)    + halfWidth;
//        yEnd    = (yEnd * halfHeight)   + halfHeight;
//        //System.out.println("drawing area absolute: " + (int)xStart + " " + (int)yStart + " " + (int)xEnd + " " + (int)yEnd);
//
//        //End clipping logic
//        double imageY = imageYStart;
//        for (int y = (int) yStart; y < (int) yEnd; y++) {
//            double imageX = imageXStart;
//            for (int x = (int) xStart; x < (int) xEnd; x++) {
//                //System.out.println(imageX + " " + imageY + " -> " + x + " " + y);
//                image.copyNearest(this, x, y, imageX, imageY);
//                imageX += imageXStep;
//            }
//
//            imageY += imageYStep;
//        }
//        //System.out.println(imageY);
//    }
//
//    public void DrawLine(float xStart, float yStart, float xEnd, float yEnd, Color color) {
//        DrawLine(xStart, yStart, xEnd, yEnd, (byte) color.getAlpha(), (byte) color.getBlue(), (byte) color.getGreen(), (byte) color.getRed());
//    }
//
//    public void DrawLine(float xStart, float yStart, float xEnd,
//                         float yEnd, byte a, byte b, byte g, byte r)
//    {
//        float halfWidth = getWidth()/2.0f;
//        float halfHeight = getHeight()/2.0f;
//        float scaleFactor = halfWidth < halfHeight ? halfWidth : halfHeight;
//        xStart = (xStart * scaleFactor) + halfWidth;
//        yStart = (yStart * scaleFactor) + halfHeight;
//        xEnd = (xEnd * scaleFactor) + halfWidth;
//        yEnd = (yEnd * scaleFactor) + halfHeight;
//        int x0 = (int)xStart;
//        int x1 = (int)xEnd;
//        int y0 = (int)yStart;
//        int y1 = (int)yEnd;
//        int deltaX = Math.abs(x1 - x0);
//        int deltaY = Math.abs(y1 - y0);
//        int error = deltaX - deltaY;
//        int xStep;
//        int yStep;
//        if(x0 < x1)
//            xStep = 1;
//        else
//            xStep = -1;
//        if(y0 < y1)
//            yStep = 1;
//        else
//            yStep = -1;
//        while(true)
//        {
//            safeDrawPixel(x0, y0, a, b, g, r);
//            if(x0 == x1 && y0 == y1)
//                break;
//            int error2 = error * 2;
//            if(error2 > -deltaY)
//            {
//                error -= deltaY;
//                x0 += xStep;
//            }
//            if(x0 == x1 && y0 == y1)
//            {
//                safeDrawPixel(x0, y0, a, b, g, r);
//                break;
//            }
//            if(error2 < deltaX)
//            {
//                error += deltaX;
//                y0 += yStep;
//            }
//        }
//    }
//
//    public void FillRect(int x, int y, int width, int height, Color color) {
//        FillRect(x, y, width, height, (byte)color.getAlpha(),(byte)color.getBlue(), (byte)color.getGreen(),(byte)color.getRed());
//    }
//
//    public void FillRect(int x, int y, int width, int height,
//                         byte a, byte b, byte g, byte r)
//    {
//        for(int j = y; j < y + height; j++)
//        {
//            for(int i = x; i < x + width; i++)
//            {
//                safeDrawPixel(i, j, a, b, g, r);
//            }
//        }
//    }
//
//    public void prepare(Graphics2D graphics, int width, int height) {
//        this.g2d = graphics;
//
//
//    }
}