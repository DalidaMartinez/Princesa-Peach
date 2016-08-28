package finalProject;

import entity.Entity;

public class Camera {
	public static int x;
	public int y;

	
	public void update(Entity player){
		setX(-player.getX() + 300 );
		setY(-player.getY() + 400);
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

	  public static void moveLeftBy(int dx)
	  {
	      x -= dx;
	  }

	  public static void moveRightBy(int dx)
	  {
	      x += dx;
	  }

}
