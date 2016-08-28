package group;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import com.sun.media.jfxmedia.events.PlayerStateEvent.PlayerState;

import HUDsystem.BossState;
import HUDsystem.PlayerStatus;
import animation.Sprite;
import entity.Entity;
import finalProject.Game;
import finalProject.Tag;
import finalProject.TileRender;
import tiles.Tile;

public class Player extends Entity {

	private PlayerStatus status;

	private int frame = 0;

	private int frameDelay = 0;

	private boolean animate = false;


	public Player(int x, int y, int width, int height, boolean solid, Tag tag, TileRender tl) {
		super(x, y, width, height, solid, tag, tl);

		status = PlayerStatus.SMALL;

	}

	public void draw(Graphics g) {
			Graphics2D g2 =   (Graphics2D) g ;
		g2.setColor(Color.GREEN);
		g2.fill(getTopBounds());
		g2.fill(getBottomBounds());
		g2.fill(getLeftBounds());
		g2.fill(getRightBounds());
		 

		if (facing == 0) {
			g.drawImage(Game.player[frame+4].getBufferedImage(), x, y, width, height, null);
		} else if (facing == 1) {
			g.drawImage(Game.player[frame].getBufferedImage(), x, y, width, height, null);


		}
	}


	public void update() {
		x += VelX;
		y += VelY;
	
		if (VelX != 0){
			animate = true;
		}else{
			animate = false;
		}

		for (int i = 0; i < tl.tile.size(); i++) {
			Tile t = tl.tile.get(i);
			if(getBounds().intersects(t.getBounds())){
				if(t.getTag() == Tag.portal){
					Game.nextLevel();
				}
			}
			
			
			if (getTopBounds().intersects(t.getBounds( )) && t.getTag() != tag.star ) {
				setVelY(0);
				if (jumping) {
					jumping = false;
					gravity = 0.8;
					falling = true;

				}
				if (t.getTag() == tag.shield) {
					if (getTopBounds().intersects(t.getBounds()))t.activated = true;
			}
			}
			if (getBottomBounds().intersects(t.getBounds()) && t.getTag() != tag.star ) {
				setVelY(0);
				if (falling)falling = false;
			} else if (!falling && !jumping) {
					falling = true;
					gravity = 0.8;
			
			}

			if (getLeftBounds().intersects(t.getBounds())  && t.getTag() != tag.star  ) {
				setVelX(0);
				x = t.getX() + width;

			}
			if (getRightBounds().intersects(t.getBounds()) && t.getTag() != tag.star  ) {
				setVelX(0);
				x = t.getX()-width;

			}

			

			if (getBounds().intersects(t.getBounds())  && t.getTag() == tag.star ) {
				Game.counter++;
				t.death();
			}
			}



			
		
		for (int i = 0; i < tl.entity.size(); i++) {

			Entity e = tl.entity.get(i);

			if (e.getTag() == Tag.shield) {
				if (getBounds().intersects(e.getBounds())) {
					// tx = when using powerup player sometime gets
					// teleported(bugged), this helps offset
				//	int tx = getX();
				//	int ty = getY();
					width += 10;
					height += 10 ;	
				//	setX(tx - width);
				//	setY(ty - height);
					if (status == PlayerStatus.SMALL) status = PlayerStatus.BIG;
					e.death();
				}

			} else if (e.getTag() == Tag.bug || e.getTag() == Tag.boss) {
				if (getBottomBounds().intersects(e.getTopBounds())) {
					if(e.getTag() != Tag.boss){
						e.death();
					} else if(e.attackable){
						e.health--;
						e.falling = true;
						e.gravity = 3.0;
						e.bossState = BossState.RECOVERING;
						e.attackable = false;
						e.phaseTime = 0;
						
						jumping = true;
						falling = false;
						gravity = 5.0;
					}
				} else if (getBounds().intersects(e.getBounds())) {
					if (status == PlayerStatus.BIG) {
						status = PlayerStatus.SMALL;
						width -= 10;
						height -= 10 ;
					//	x+= width;
					// y+= height;

					} else if (status == PlayerStatus.SMALL) {
						death();
				}
			}
		}
	}
		if (jumping ) {
			gravity -= 0.18;
			setVelY((int) -gravity);
			if (gravity <= 0.6) {
				jumping = false;
				falling = true;
			}
		}

		if (falling) {
			gravity += 0.18	;
			setVelY((int) gravity);
		}

		if (VelX!=0) {
			frameDelay++;
			if (frameDelay >= 8) {
				frame++;
				if(frame > 3){
					frame = 0;
				}
			}
			frameDelay = 0;
		}
	}
}




