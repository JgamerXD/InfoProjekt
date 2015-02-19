package engine.entity;

import engine.Input;
import engine.physics.Transform;
import engine.rendering.RenderContext;

import java.util.HashMap;

/**
 * Created by Jan on 14.02.2015.
 */
public class Entity {
    private HashMap<String,Boolean> flags;

    public Transform transform;

    public void setFlag(String key,boolean value)
    {
        flags.put(key,value);
    }

    public boolean getFlag(String key)
    {
       	return flags.getOrDefault(key, false);
    }

    public void update(Input input)
    {

    }

    public void render(RenderContext context)
    {

    }
}
