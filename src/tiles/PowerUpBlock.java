package tiles;

import java.awt.Graphics;

import animation.Sprite;
import entity.powerup.Shield;
import finalProject.Game;
import finalProject.Tag;
import finalProject.TileRender;

public class PowerUpBlock extends Tile{
	
	private Sprite powerUp;
	
	private boolean poppedUp = false;
	
	private int spriteY = getY();

	public PowerUpBlock(int x, int y, int height, int width, boolean solid, Tag tag, TileRender tl, Sprite powerUp) {
		super(x, y, height, width, solid, tag, tl);
		this.powerUp = powerUp;
	}
	
	
	//--------------------------------------------------------------------------------------------------//

	
	public void draw(Graphics g) {
		if(!poppedUp) g.drawImage(Game.powerUp.getBufferedImage(), x, spriteY, width,height,null);
		if(!activated) g.drawImage(Game.powerUp.getBufferedImage(), x, y, width, height, null);
		else g.drawImage(Game.powerUpUsed.getBufferedImage(), x, y, width, height, null);

	}

	//--------------------------------------------------------------------------------------------------//

	
	public void update() {
		if(activated && !poppedUp){
			spriteY--;
			if(spriteY <= y - height){
				tl.addEntity(new Shield(x,spriteY,width,height,solid, Tag.shield, tl ));
				poppedUp = true;
				
				Game.power.play();
			}
		}
		
	}

}
