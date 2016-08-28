package finalProject.GUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import finalProject.Game;

public class Launch {
	
	public Buttons[] buttons;
	private  BufferedImage menu;
	
	public Launch(){
		buttons = new Buttons[2];
		buttons [0] = new Buttons(100, 100, 100, 100, "Start Game");
		buttons [1] = new Buttons(200, 200, 100, 100, "Exit Game");
	}
	public void draw(Graphics g){
		try {
			menu = ImageIO.read(getClass().getResource("/menu.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		g.drawImage(menu, 0, 0, null);

		for (int i = 0; i< buttons.length; i++){
			buttons[i].draw(g);
		}
	}
	

}
