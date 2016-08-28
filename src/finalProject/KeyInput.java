package finalProject;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import entity.Bullets;
import entity.Entity;
import entity.Projectile;
import finalProject.Game;
import group.Player;

public class KeyInput implements KeyListener{
	
	
	
	
	//--------------------------------------------------------------------------------------------------//

	@Override
	public void keyPressed(KeyEvent e) {
		  int key = e.getKeyCode();
		  for(int i = 0; i< Game.tilerender.entity.size();i++) {
			 Entity en = Game.tilerender.entity.get(i);
			 if(en.getTag() == Tag.player){
			 
		   switch(key) {
		   case KeyEvent.VK_UP:
			   if(!en.jumping){ 
				   en.jumping = true;
				   en.gravity = 10.0;
					Game.jump.play();

				   
			   }
		    break;
//		   case KeyEvent.VK_DOWN:
//		    en.setVelY(5);
//		    break;
		   case KeyEvent.VK_LEFT:
		    en.setVelX(-5);
		    en.facing = 0;
		    break;
		   case KeyEvent.VK_RIGHT:
		    en.setVelX(5);
		    en.facing = 1;
			break;	
		   case KeyEvent.VK_SPACE:
			//	Game.p.addBullet(new Bullets(en.getX(),en.getY()));
			break;
			
		   		}	
			 }
		  }
	}
	//--------------------------------------------------------------------------------------------------//
	
	@Override
	public void keyReleased(KeyEvent e) {
		  int key = e.getKeyCode();
		  for(int i = 0; i< Game.tilerender.entity.size();i++) {
			  Entity en = Game.tilerender.entity.get(i);
			 if(en.getTag() == Tag.player){
				   switch(key) {
		   case KeyEvent.VK_UP:
		    en.setVelY(0);
		    break;
		   case KeyEvent.VK_DOWN:
		    en.setVelY(0);
		    break;
		   case KeyEvent.VK_LEFT:
		    en.setVelX(0);
		    break;
		   case KeyEvent.VK_RIGHT:
		    en.setVelX(0);
		    break;
		   case KeyEvent.VK_SPACE:
		    break;
		   }
				
		  }
	}
	}
	@Override
	
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
