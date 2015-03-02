package engine;

import com.sun.javafx.iio.ImageStorage;
import com.sun.javafx.iio.ImageStorage.ImageType;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

/**
 * Created by Jan on 02.03.2015.
 */
public class ResourceManager {
    private static HashMap<String,BufferedImage> images =  new HashMap<>();

    public static BufferedImage loadImage(String path)
    {
        path.toLowerCase();
        if(images.containsKey(path))
            return images.get(path);

        try {
            InputStream resource = ResourceManager.class.getResourceAsStream(path);//Bitmap.class.getResource("./" + fileName);
            if (resource == null) throw new FileNotFoundException( path + " is not an accessible resource.");
            BufferedImage img = ImageIO.read(resource);
            return img;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new BufferedImage(1,1, ImageType.RGBA.ordinal());
    }
}
