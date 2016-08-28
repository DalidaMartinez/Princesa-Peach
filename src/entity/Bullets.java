package entity;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class Bullets {
	private int x;
	private int y;
	Image img;
	
	public Bullets(int x, int y){
		this.x = x;
		this.y = y;
		
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void update(){
		y += 10;
		
	}
	
	public void draw(Graphics g ){
			img = Toolkit.getDefaultToolkit().getImage("/bullet.png");
		
		    g.drawImage(img, x, y, null);
		
	
	}
}
