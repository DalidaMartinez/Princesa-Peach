package entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import HUDsystem.BossState;
import finalProject.Game;
import finalProject.Tag;
import finalProject.TileRender;


//Creates an entity of given dimensions with 4 rectangle collision detection

public abstract class Entity {
	public int x,y;
	
	public int width,height;
	
	//facing left = 0 facing right = 1
	public int  VelX, VelY;
	public int facing = 0 ;
	public int health;
	
	//tracks amount of time boss is in each phase
	public int phaseTime;
	public double gravity = 0.0;
	
	public boolean attackable = false;


	
	public boolean solid;
	
	public boolean jumping = false;
	
	public boolean falling = true;
	
	
	
	
	public Tag tag;
	public BossState bossState;
	
	
	
	
	public int frame = 0;
	
	public int frameDelay = 0;
	
	public TileRender tl;
	

	
	
	
	public Entity(int x, int y, int width, int height, boolean solid, Tag tag, TileRender tl){
		this.x = x;
		this.y = y;
		this.height = height;
		this.width = width;
		this.solid = solid;
		this.tag = tag;
		this.tl = tl;
	}
	
	public abstract void draw(Graphics g);
	
	
	
	public abstract void update();

	

	public Tag getTag(){
		return tag;
		
	}

	
	public void death(){
		tl.removeEntity(this);
		if(getTag() == Tag.player){
		Game.lives--;
		Game.counter = 0;
		Game.showDeathScreen = true;
		
		if(Game.lives <= 0)Game.gameOver = true;
	}
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
	
	public int getWidth(){
		return width;
	}

	public int getHeight(){
		return height;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public boolean	isSolid(){
		return solid;
	}

	public void setVelX(int velX) {
		this.VelX = velX;
	}



	public void setVelY	(int velY) {
		this.VelY = velY;
	}
	

		

	
	public Rectangle getBounds(){
		return new Rectangle(getX(),getY(), width,height);
	}
	
	public Rectangle getTopBounds(){
		return new Rectangle(getX()+5,getY(), width-10,5);
	}
	 
	public Rectangle getBottomBounds(){
		return new Rectangle(getX()+5,getY()+height, width-10,5);
	}
	public Rectangle getLeftBounds(){
		return new Rectangle(getX(),getY()+10, 5,height-15);
	}
	public Rectangle getRightBounds(){
		return new Rectangle(getX()+width-5,getY()+10,5,height-15);
	}
	
	

	
}
