package engine;

import engine.rendering.RenderContext;

import java.util.*;

/**
 * Created by JgamerXD on 30.08.2014.
 */
public interface Game {

    public void update(Input input);
    public void render(RenderContext target);
}
