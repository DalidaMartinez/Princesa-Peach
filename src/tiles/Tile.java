package tiles;

import java.awt.Graphics;
import java.awt.Rectangle;

import finalProject.Tag;
import finalProject.TileRender;

public abstract class Tile {
	public int x,y;
	
	public int width,height;
	
	public boolean solid = false;
	public boolean activated = false;
	
	
	public int VEL_X,VEL_Y;
	
	public Tag tag;
	
	public TileRender tl;
	
	public Tile(int x, int y, int height, int width, boolean solid, Tag tag, TileRender tl){
		this.x = x;
		this.y = y;
		this.height = height;
		this.width = width;
		this.solid = solid;
		this.tl = tl;
		this.tag = tag;
		
	}
	
	public abstract void draw(Graphics g);

	
	public abstract void update();
	
	public void death(){
		tl.removeTile(this);
		
	}

	public Tag getTag(){
		return tag;
		
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

	public boolean isSolid() {
		return solid;
	}

	public void setSolid(boolean solid) {
		this.solid = solid;
	}

	public int getVelx() {
		return VEL_Y;
	}

	public void setVelx(int velx) {
		VEL_Y = velx;
	}

	public int getvely() {
		return VEL_X;
	}

	public void setVely(int vely) {
		VEL_X = vely;
	}
		
	public Rectangle getBounds(){
		return new Rectangle(x,y, width,height);
	}
	
	public int getWidth(){
		return width;
	}

	public int getHeight(){
		return height;
	}
	
	
}



