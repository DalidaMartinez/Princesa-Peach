package tiles;

import java.awt.Graphics;

import finalProject.Game;
import finalProject.Tag;
import finalProject.TileRender;

public class Stars extends Tile {

	public Stars(int x, int y, int height, int width, boolean solid, Tag tag, TileRender tl) {
		super(x, y, height, width, solid, tag, tl);
			}

	//--------------------------------------------------------------------------------------------------//

	public void draw(Graphics g) {
		g.drawImage(Game.star.getBufferedImage(), x, y,width,height,null);
	}

	//--------------------------------------------------------------------------------------------------//
	public void update() {
		// TODO Auto-generated method stub
		
	}
	
	

}
