package engine.world;

import engine.rendering.Spritesheet;
import engine.rendering.SpritesheetSprite;

/**
 * Created by Jan on 24.02.2015.
 */
public class Tileset {
    public Tile[] tiles;
    int tileCount;

    public Tileset(Spritesheet spritesheet, int tileCount, int offset)
    {
        this.tileCount = tileCount;
        tiles = new Tile[tileCount];
        for(int i = 0; i<tileCount ; i++)
        {
            tiles[i] = new Tile(new SpritesheetSprite(spritesheet,i+offset));
        }

    }

    public int getTileCount()
    {
        return tileCount;
    }

    public Tile getTile(int i)
    {
        return tiles[i];
    }
}
