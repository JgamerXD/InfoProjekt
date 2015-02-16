package engine.config;

import java.awt.*;
import java.io.*;
import java.util.Properties;

/**
 * Created by JgamerXD on 30.08.2014.
 */
public class Configuration {

    Properties properties;
    String filename;

    public Configuration(String file)
    {
        properties = new Properties();
        filename = file;
        load();
        properties.list(System.out);
        System.out.println();
    }

    public void save()
    {
        FileWriter writer;


        try {

            File file = new File(filename);
            if(!file.exists())
                file.createNewFile();
            writer = new FileWriter(file);

            properties.store(writer,"");
        }
        catch(IOException e)
        {
            System.out.println("Error while saving configuration file!");
            e.printStackTrace();
        }
    }

    public void load()
    {
        FileReader reader = null;

        try {
            reader = new FileReader(filename);
            properties.load(reader);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try { if(reader != null)
                    reader.close();
            } catch ( Exception e ) { }
        }
    }

    public boolean getBoolean(String key, boolean defaultValue){
        String value = properties.getProperty(key);
        boolean result;
        if(value != null)
            try
            {
                result = Boolean.parseBoolean(value);
                return result;
            }
            catch(Exception e)
            {
                setBoolean(key, defaultValue);
                return defaultValue;
            }
        else {
            setBoolean(key, defaultValue);
            return defaultValue;
        }

    }
    public void setBoolean(String key, boolean value){
        properties.setProperty(key,Boolean.toString(value));
    }



    public String getString(String key, String defaultValue){
        String value = properties.getProperty(key);
        if(value == null)
        {
            setString(key, defaultValue);
            value = defaultValue;
        }
        return value;
    }
    public void setString(String key,String value){
        properties.setProperty(key,value);
    }



    public int getInteger(String key, int defaultValue){
        String value = properties.getProperty(key);
        int result;
        if(value != null)
            try
            {
                result = Integer.parseInt(value);
                return result;
            }
            catch(Exception e)
            {
                setInteger(key, defaultValue);
                return defaultValue;
            }
        else {
            setInteger(key, defaultValue);
            return defaultValue;
        }
    }
    public void setInteger(String key, int value){
        properties.setProperty(key,Integer.toString(value));
    }



    public float getFloat(String key, float defaultValue){
        String value = properties.getProperty(key);
        float result;
        if(value != null)
            try
            {
                result = Float.parseFloat(value);
                return result;
            }
            catch(Exception e)
            {
                setFloat(key,defaultValue);
                return defaultValue;
            }
        else {
            setFloat(key, defaultValue);
            return defaultValue;
        }

    }
    public void setFloat(String key, float value){
        properties.setProperty(key,Float.toString(value));
    }



    public Color getColor(String key,Color defaultValue)
    {
        String value = properties.getProperty(key);
        Color result;
        if(value != null)
            try
            {
                result = Color.decode(value);
                return result;
            }
            catch(Exception e)
            {
                setColor(key, defaultValue);
                return defaultValue;
            }
        else {
            setColor(key, defaultValue);
            return defaultValue;
        }
    }
    public void setColor(String key, Color value)
    {
        properties.setProperty(key,Integer.toHexString(value.getRGB()));
    }
}
