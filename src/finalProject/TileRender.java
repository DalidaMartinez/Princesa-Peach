package finalProject;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.List;

import com.sun.media.jfxmediaimpl.platform.Platform;

import entity.Entity;
import entity.powerup.Shield;
import group.Boss1;
import group.Bug;
import group.Player;
import tiles.Grass;
import tiles.Ledge;
import tiles.Portal;
import tiles.PowerUpBlock;
import tiles.Sky;
import tiles.Stars;
import tiles.Tile;
import tiles.Wall;

public class TileRender {

	public LinkedList<Entity> entity = new LinkedList<Entity>();

	public LinkedList<Tile> tile = new LinkedList<Tile>();

	public TileRender(){
	}
	//--------------------------------------------------------------------------------------------------//

	public void draw(Graphics g){
		for(int i = 0; i< entity.size();i++){
			Entity en = entity.get(i);
			if(Game.getVisibleArea() != null && en.getBounds().intersects(Game.getVisibleArea())){
				en.draw(g);
			}
		}
		for(int i = 0; i< tile.size();i++){
			Tile t = tile.get(i);  
			if(Game.getVisibleArea() != null && t.getBounds().intersects(Game.getVisibleArea())){
				t.draw(g);
			}
		}
	
	}
	//--------------------------------------------------------------------------------------------------//

	public void update(){
		
		for(int i = 0; i< entity.size();i++){
			Entity en = entity.get(i);

			en.update();
		}
		for(int i = 0; i< tile.size();i++){
			Tile t = tile.get(i);

			t.update();
		}
	
	}
	//--------------------------------------------------------------------------------------------------//


	public void addEntity(Entity e){
		entity.add(e);
	}

	public void removeEntity(Entity e){
		entity.remove(e);
	}

	public void addTile(Tile t){
		tile.add(t);
	}

	public void removeTile(Tile t){
		tile.remove(t);
	}


	//--------------------------------------------------------------------------------------------------//



	public void createLevel(	BufferedImage level) {

		int width = level.getWidth();
		int height = level.getHeight();

		for (int y = 0; y < height; y++){
			for(int x = 0; x< width; x++){
				int pixel = level.getRGB(x, y);

				int red = (pixel >> 16) & 0xff;
				int green = (pixel >> 8) & 0xff;
				int blue = (pixel) & 0xff;


				//black
				if(red==0&& blue == 0 && green == 0) addTile(new Wall(x*64,y*64,64,64,true,Tag.wall,this));

				//cobalt
				if(red==0&& blue == 255 && green == 12) addTile(new Sky(x*64,y*64,64,64,true,Tag.sky,this));
				//grassyLedge
				if(red== 255 && blue == 0 && green == 120) addTile(new Ledge(x*64,y*64,64,64,true,Tag.ledge,this));

				//blue		
				if(red==0&& blue == 255 && green == 0) addEntity(new Player(x*64,y*64,64,64,true,Tag.player,this));
				//red		
				if(red==255 && blue == 0 && green == 0) addTile(new Grass(x*64,y*64,64,64,true,Tag.grass,this));
				//green		
				if(red==0 && blue == 0 && green == 255) addEntity(new Bug(x*64,y*64,64,64,true,Tag.bug,this));
				//yellow		
				if(red==255 && blue == 0 && green == 255) addTile(new PowerUpBlock(x*64,y*64,64,64,true,Tag.shield,this, Game.shield));
				//star	
				if(red==255 && blue == 0 && green == 204) addTile(new Stars(x*64,y*64,64,64,true,Tag.star,this));
				//boss
				if(red==241 && blue == 73 && green == 73) addEntity(new Boss1(x*64,y*64,64,64,true,Tag.boss,this, 2 ));
				//portal
				if(red==225 && blue == 255 && green == 0) addTile(new Portal(x*64,y*64,64,64,true,Tag.portal,this));

			}


		}
	}
	public void clearLevel(){
		entity.clear();
		tile.clear();
	}
}
