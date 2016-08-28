package entity.powerup;

import java.awt.Graphics;
import java.util.Random;

import entity.Entity;
import finalProject.Game;
import finalProject.Tag;
import finalProject.TileRender;
import tiles.Tile;

public class Shield extends Entity{


	
	private Random ran = new Random();

	public Shield(int x, int y, int width, int height, boolean solid, Tag tag, TileRender tl) {
		super(x, y, width, height, solid, tag, tl);
		
		// has entity randomly start moving either left or right
		int direction = ran.nextInt(2);
		
		switch(direction){
		case 0:
			setVelX(-2);
			break;
		case 1:
			setVelX(2);
			break;
		}
	}
	
	public void draw(Graphics g){
		g.drawImage(Game.shield.getBufferedImage(),x,y,width,height,null);
	}
	
	public void update(){
		x += VelX;
		y += VelY;
		

		for(int i = 0; i < tl.tile.size() ; i++) {
			Tile t = tl.tile.get(i);
				 if(getBottomBounds().intersects(t.getBounds())){
					setVelY(0);
					if(falling) falling = false;
				 }else{
					 if(!falling){
						 gravity = 0.8;
						 falling = true; 
						 
				 }					
	
				 if(getLeftBounds().intersects(t.getBounds())){
					setVelX(5);
					
				}
				
				 if(getRightBounds().intersects(t.getBounds())){
					setVelX(-5);
				
					
				}
			}
		}
		if(falling){
			gravity += 0.10;
			setVelY((int) gravity);
		}
	}
	

}
