package entity;

import java.awt.Graphics;
import java.util.LinkedList;


public class Projectile {


		
		private LinkedList<Bullets> b = new LinkedList<Bullets>();

		
		Bullets TempBullet;

		
		
		public void update(){
			

			for (int i = 0; i<b.size();i++){
				TempBullet = b.get(i);
				
				TempBullet.update();
			}
			
		}
		
		public void draw(Graphics g){
					
			for (int i = 0; i<b.size();i++){
				TempBullet = b.get(i);
				
				if(TempBullet.getY() <0){
					removeBullet(TempBullet);
				}
				
				TempBullet.draw(g);
			}
			
		}
		
		public void addBullet(Bullets bull ){
			b.add(bull);
		}
		
		public void removeBullet(Bullets bull){
			b.remove(bull);
		}

		
		
	}



