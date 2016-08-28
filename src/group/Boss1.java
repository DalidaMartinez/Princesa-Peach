package group;

import java.awt.Graphics;
import java.util.Random;

import HUDsystem.BossState;
import entity.Entity;
import finalProject.Game;
import finalProject.Tag;
import finalProject.TileRender;
import tiles.Tile;

public class Boss1 extends Entity {

	public int jumpTime = 0;

	public boolean addJumpTime = false;

	private Random ran;

	public Boss1(int x, int y, int width, int height, boolean solid, Tag tag, TileRender tl, int health) {
		super(x, y, width, height, solid, tag, tl);
		this.health = health;

		bossState = BossState.IDLE;

		ran = new Random();
	}

	public void draw(Graphics g) {
		if (bossState == BossState.IDLE || bossState == BossState.CHASING || bossState == BossState.JUMPING
				|| bossState == BossState.SPINNING) {
			g.drawImage(Game.bossAttacking.getBufferedImage(), x, y, width, height, null);
		} else if (bossState == BossState.RECOVERING) {
			g.drawImage(Game.bossRecovering.getBufferedImage(), x, y, width, height, null);
		}

	}

	public void update() {
		x += VelX;
		y += VelY;

		if (health <= 0)
			death();

		phaseTime++;

		if ((phaseTime >= 180 && bossState == BossState.IDLE)
				|| (phaseTime >= 600 && bossState != BossState.SPINNING)){
			chooseState();
		}
		if (bossState == BossState.RECOVERING && phaseTime>= 180){
			bossState = BossState.SPINNING;
			phaseTime = 0;
		}
		
		if(phaseTime>=360 && bossState == BossState.SPINNING){
			phaseTime = 0;
			bossState = BossState.IDLE;
		}
		
		if (bossState == BossState.IDLE || bossState == BossState.RECOVERING) {
			setVelX(0);
			setVelY(0);
		}

		if (bossState == BossState.JUMPING || bossState == BossState.CHASING) {
			attackable = true;
		} else {
			attackable = false;
		}

		if (bossState != BossState.JUMPING) {
			addJumpTime = false;
			jumpTime = 0;
		}

		if (addJumpTime) {
			jumpTime++;
			if (jumpTime >= 30) {
				addJumpTime = false;
				jumpTime = 0;
			}
		}

		if (!jumping && !falling) {
			jumping = true;
			gravity = 8.0;
		}

		for (int i = 0; i < tl.tile.size(); i++) {
			Tile t = tl.tile.get(i);
			if (getTopBounds().intersects(t.getBounds())) {
				setVelY(0);
				if (jumping) {
					jumping = false;
					gravity = 0.8;
					falling = true;
				}
			}
			if (getBottomBounds().intersects(t.getBounds())) {
				setVelY(0);
				if (falling) {
					falling = false;
					addJumpTime = true;
				}

			}

			if (getLeftBounds().intersects(t.getBounds())) {
				setVelX(0);
				if (bossState == BossState.CHASING)
					setVelX(4);
				x = t.getX() + t.width;

			}
			if (getRightBounds().intersects(t.getBounds())) {
				setVelX(0);
				if (bossState == BossState.CHASING)
					setVelX(-4);
				x = t.getX() - t.width;

			}

		}

		for (int i = 0; i < tl.entity.size(); i++) {
			Entity e = tl.entity.get(i);
			if (e.getTag() == Tag.player) {
				if (bossState == BossState.JUMPING) {
					if (jumping || falling) {
						if((getX()>= (e.getX()-4)) && (getX() <= e.getX()+4)) setVelX(0);
						else if (e.getX() < getX())
							setVelX(-3);
						else if (e.getX() > getX())
							setVelX(3);
					} else
						setVelX(0);
				}else if(bossState == BossState.SPINNING){
					 if (e.getX() < getX())
						setVelX(-3);
					else if (e.getX() > getX());
					
				}
			}

		}
		if (jumping) {
			gravity -= 0.17;
			setVelY((int) -gravity);
			if (gravity <= 0.50) {
				jumping = false;
				falling = true;
			}
		}

		if (falling) {
			gravity += 0.17;
			setVelY((int) gravity);
		}

	}

	public void chooseState() {
		int nextPhase = ran.nextInt(2);
		if (nextPhase == 0) {
			bossState = BossState.CHASING;
			int direction = ran.nextInt(2);
			if (direction == 0)
				setVelX(-4);
			else
				setVelX(4);

		} else if (nextPhase == 1) {
			bossState = BossState.JUMPING;
			jumping = true;
			gravity = 8.0;

		}
		phaseTime = 0;
	}

}
