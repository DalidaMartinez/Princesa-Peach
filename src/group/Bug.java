package group;

import java.awt.Graphics;
import java.util.Random;

import entity.Entity;
import finalProject.Game;
import finalProject.Tag;
import finalProject.TileRender;
import tiles.Tile;

public class Bug extends Entity {

	private Random ran = new Random();

	public Bug(int x, int y, int width, int height, boolean solid, Tag tag, TileRender tl) {
		super(x, y, width, height, solid, tag, tl);
		
		int direction = ran.nextInt(2);

		switch(direction){
		case 0:
			setVelX(-2);
			facing = 0;
			break;
		case 1:
			setVelX(2);
			facing = 1;
			break;
		}
	}

	//--------------------------------------------------------------------------------------------------//

	public void draw(Graphics g) {
		if(facing == 0){
			g.drawImage(Game.bug[frame+4].getBufferedImage(), x, y, width,height,null);
		}else if (facing == 1){
			g.drawImage(Game.bug[frame].getBufferedImage(), x, y, width,height,null);

		}

	}

	//--------------------------------------------------------------------------------------------------//

	public void update() {
		x+= VelX;
		y+= VelY;

		for(int i = 0; i < tl.tile.size() ; i++) {
			Tile t = tl.tile.get(i);
			if(!t.solid) break;

			if(getBottomBounds().intersects(t.getBounds())){
				setVelY(0);
				if(falling) falling = false;
			}else{
				if(!falling){
					gravity = 0.8;
					falling = true; 

				}					

				if(getLeftBounds().intersects(t.getBounds())){
					setVelX(2);
					facing = 1;

				}

				if(getRightBounds().intersects(t.getBounds())){
					setVelX(-2);
					facing = 0;


				}
			}
		}
		if(falling){
			gravity += 0.10;
			setVelY((int) gravity);
		}
		if (VelX!=0){
			frameDelay++;
			if(frameDelay >= 8){
				frame++;
				if(frame > 3){
					frame = 0;
				}
				frameDelay = 0;
			}
		}

	}

}
