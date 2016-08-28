package tiles;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import finalProject.Game;
import finalProject.Tag;
import finalProject.TileRender;

public class Wall extends Tile{

	public Wall(int x, int y, int height, int width, boolean solid, Tag tag, TileRender tl) {
		super(x, y, height, width, solid, tag, tl);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(Game.block.getBufferedImage(), x, y, width,height,null);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}


}
