package engine.world;

import engine.Util;
import engine.math.Vec2d;
import engine.physics.AABB;
import engine.rendering.RenderContext;

public class World {
	
	int[] data;
    int width, height;
	
	//double tileSize = 2;
	Tile[] objekte = new Tile[1024];

    private Vec2d gravity = new Vec2d(0,9.81);
	
	
	
	
	public World(int width, int height, int[] data)
	{
        this.width = width;
        this.height = height;
		this.data = data;
        objekte[0] = Tile.EMPTY;
	}

    /**
     * Calculates the sum of all forces on a given tile (ATM only gravity)
     * @param x x coordinate of the tile
     * @param y y coordinate of the tile
     * @return sum of all forces at the tile
     */
    public Vec2d getForce(int x, int y)
    {
        return gravity;
    }







    public Tile getTileAt(int x, int y)
    {
        if(0 > x || x >= width || 0 > y || y >= height)
            return Tile.EMPTY;
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
