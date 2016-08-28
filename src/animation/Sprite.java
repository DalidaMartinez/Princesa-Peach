package animation;

import java.awt.image.BufferedImage;



// Uses a sprite, or one image from the sprite sheet to draw onto a tile.


public class Sprite {
	
	public SpriteSheet sheet;
	
	public BufferedImage image;
	
	public Sprite(SpriteSheet sheet, int x, int y){
		image = sheet.getSprite(x, y);
		
	}
	
	public BufferedImage getBufferedImage(){
		return image;
	}
	
}
