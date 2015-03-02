package game;

import engine.Util;
import engine.physics.AABB;
import engine.rendering.RenderContext;
import game.welt.Tile;
import game.welt.Tileset;

import java.util.Arrays;

public class Welt {
	
	int[] data;
    int width, height;
	
	double tileSize = 1;
	Tile[] objekte = new Tile[1024];
	
	
	
	
	public Welt(int width,int height, int[] data)
	{
        this.width = width;
        this.height = height;
		this.data = data;
	}

    public Tile getTileAt(int x, int y)
    {
        return getTile(data[x + y * width]);
    }


	
	public Tile getTile(int id)
	{
		return objekte[id];
	}

    public void setTile(int id, Tile tile)
    {
        objekte[id] = tile;
    }

    public void setTiles(Tileset tileset, int start)
    {
        for(int i = 0; i<tileset.getTileCount();i++)
            setTile(i+start,tileset.tiles[i]);
    }

    public void render(RenderContext ctx)
    {
        AABB renderArea = new AABB(-100,-100,100,100);//ctx.getRenderArea();
        int minx = Util.clamp((int) renderArea.minX, 0,width-1);
        int miny = Util.clamp((int)renderArea.minY,0,height-1);
        int maxx = Util.clamp((int)renderArea.maxX +1,0,width-1);
        int maxy = Util.clamp((int)renderArea.maxY +1,0,height-1);

        for(int i = minx; i <= maxx;i++)
            for(int j = miny; j <= maxy; j++)
            {
                if(getTileAt(i,j) != null)
                    getTileAt(i,j).render(i,j,ctx);
            }

    }
	
}
