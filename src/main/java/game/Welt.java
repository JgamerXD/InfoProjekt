package game;

import engine.rendering.Sprite;
import game.welt.WeltObjekt;

public class Welt {
	
	int[][] tiles;
	
	double tileSize = 1;
	WeltObjekt[] objekte = new WeltObjekt[1024];
	
	
	
	
	public Welt()
	{
		
	}
	
	public WeltObjekt getObjekt(int id)
	{
		return objekte[id];
	}
	
}
