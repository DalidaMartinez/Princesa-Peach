package finalProject;


import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import animation.Sprite;
import animation.SpriteSheet;
import entity.Entity;
import entity.Projectile;
import finalProject.GUI.Launch;
import group.Player;
import tiles.Wall;


public class Game extends Canvas implements Runnable
{
	public static final int WIDTH = 320;
	public static final int HEIGHT = 180;
	public static final int SCALE = 4;
	public static final String TITLE = "Princesa Peach";

	private Thread t;
	private boolean running = false;
	
	private BufferedImage bg;	
	
	private static BufferedImage[] levels;
	private static int level = 0;





	public static int counter = 0;
	public static int lives = 2;
	public static int deathScreenTime = 0;

	public static boolean showDeathScreen = true;
	public static boolean gameOver = false;
	public static boolean playing = false;;



	public static TileRender tilerender;
	public static SpriteSheet sheet;
	public static Camera camera;
	public static Launch launcher;
	public static MouseInput mouse;



	public static Sprite block;
	public static Sprite ledge;
	public static Sprite grass;
	public static Sprite sky;
	public static Sprite shield;
	public static Sprite powerUp;
	public static Sprite powerUpUsed;
	public static Sprite star;
	public static Sprite bossAttacking;
	public static Sprite bossRecovering;
	public static Sprite portal;

	public static Sprite player[];
	public static Sprite bug[];
	public static Sprite starAnimated[];
	
	
	
	public static Sound jump;
	public static Sound power;
	public static Sound bgM;

	public static Projectile p  = new Projectile();


	public Game(){

		// allows to use both width and height in a single object
		Dimension size = new Dimension(WIDTH*SCALE,HEIGHT*SCALE);
		setPreferredSize(size);
		setMaximumSize(size);
		setMinimumSize(size);
	}

	private void init(){
		camera = new Camera();

		tilerender = new TileRender();
		sheet = new SpriteSheet("/spritesheet.png");

		launcher = new Launch();
		mouse = new MouseInput();

		addKeyListener(new KeyInput());
		addMouseListener(mouse);
		addMouseMotionListener(mouse);

		block = new Sprite(sheet,4,3);
		ledge = new Sprite(sheet,2,4);
		grass = new Sprite(sheet,1,3);
		sky = new Sprite(sheet,5,3);
		player = new Sprite[8];
		shield = new Sprite(sheet,3,3); 
		bug = new Sprite[8];
		powerUp = new Sprite(sheet,2,3);
		powerUpUsed = new Sprite(sheet, 4,3);
		star = new Sprite(sheet,1,4);
		starAnimated = new Sprite[8];
		bossAttacking = new Sprite(sheet,1,5);
		bossRecovering = new Sprite(sheet,2,5);
		portal = new Sprite(sheet,3,5);
		
		levels = new BufferedImage[2];
		
		

		for(int i = 0; i< player.length; i++){
			player[i] = new Sprite(sheet, i+1,1);
		}
		for(int i = 0; i< bug.length; i++){
			bug[i] = new Sprite(sheet, i+1,2);
		}
		try {
			levels[0] = ImageIO.read(getClass().getResource("/level0.png"));
			levels[1] = ImageIO.read(getClass().getResource("/level1.png"));
			
			bg = ImageIO.read(getClass().getResource("/bg1.png"));

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		bgM = new Sound("/POL-starry-night-short.wav");
		
		jump = new Sound("/smalljump.wav");
		
		power = new Sound("/powerUp.wav");
	
	}


	//does not allow thread to be interrupted by other threads and memory issues
	public synchronized void start(){
		if(running) return;
		running = true;
		t = new Thread(this);
		t.start();

	}
	// Synchronized methods enable a simple strategy for preventing
	// thread interference and memory consistency errors
	public synchronized void stop(){
		if(running) return;
		running = false;
		t = new Thread(this);
		try {
			//waits for thread to be completed
			t.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	@Override
	public void run() {
		init();
		requestFocus();
		long last= System.nanoTime();
		long timer = System.currentTimeMillis();
		double change = 0.0;
		double ns = 1000000000.0/60.0;
		int frames = 0;
		int updates = 0;

		while(running){
			long curr = System.nanoTime();
			change+= (curr-last)/ns;
			last = curr;
			while(change >= 1) {
				update();
				updates++;
				change--;
			}

			draw();
			frames++;	
			if(System.currentTimeMillis()-timer>1000){
				timer+=100;
				System.out.println(frames + " FPS " + updates + " Updates Per Second");
				frames = 0;
				updates = 0;
			}
		}
	}

	public void draw() {
		BufferStrategy buff = getBufferStrategy();
		if(buff == null){
			createBufferStrategy(3);
			return;
		}
		Graphics g = buff.getDrawGraphics();
		g.setColor(Color.black);
		g.fillRect(0, 0, getWidth(), getHeight());
		if(!showDeathScreen){
			g.drawImage(bg,0, 0, getWidth(), getHeight(), null);
			g.drawImage(Game.star.getBufferedImage(), 20,20,75,75, null);
			
			g.setColor(Color.WHITE);
			g.setFont(new Font("Cracked",Font.BOLD,30));
			g.drawString("x"+Game.counter,100,95);
			
		}
		if(showDeathScreen){
			if(!gameOver){

				g.setColor(Color.WHITE);
				g.setFont(new Font("Cracked",Font.BOLD,40));
				g.drawImage(Game.player[0].getBufferedImage(), 500, 300,100,100, null);		
				g.drawString("x"+ lives, 600, 410);
			}else{
			bgM.stop();
				g.setColor(Color.WHITE);
				g.setFont(new Font("Cracked",Font.BOLD,40));
				g.drawString("Game Over", 500, 410);

			}
		}
		//Camera
		if(playing)  g.translate(camera.getX(), camera.getY());		

		if(!showDeathScreen && playing) tilerender.draw(g);
		else if(!playing) launcher.draw(g);
		g.dispose();
		buff.show();	

	}

	public void update() 	{
		if(playing) tilerender.update();	

		if(showDeathScreen && gameOver == false) deathScreenTime++;
		if(deathScreenTime >=180){
			deathScreenTime = 0;
			tilerender.entity.clear();
			tilerender.clearLevel();
			tilerender.createLevel(levels[level]);
			showDeathScreen = false;
			bgM.playBG();
			

		}
		for(int i = 0; i< tilerender.entity.size();i++){
			Entity en = tilerender.entity.get(i);
			if(en.getTag() == Tag.player){
				camera.update(en);
			}
		}
	}

	public static int getFrameWidth(){
		return WIDTH*SCALE;
	}

	public static int getFrameHeight(){
		return HEIGHT *SCALE;
	}

	public static void nextLevel() {
		Game.level++;
		
		tilerender.clearLevel();
		
		tilerender.createLevel(levels[level]);
		
		
		

	}

	public static Rectangle getVisibleArea(){
		for (int i = 0; i< tilerender.entity.size();i++){
			Entity en = tilerender.entity.get(i);
			if(en.getTag() == Tag.player)
				return new Rectangle(en.getX()-(getFrameHeight()/2-5),en.getY()-(getFrameWidth()/2-5),
						getFrameWidth()+200,getFrameHeight()+1500);

		}
		return null;


	}



	public static void main(String[] args){
		Game game = new Game();
		JFrame frame = new JFrame(TITLE);
		frame.add(game);
		//The pack method sizes the frame so that all its contents are at or above their preferred sizes
		frame.pack();
		frame.setResizable(false);
		//centers frame
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		game.start();
		
		System.out.println("x = "+getVisibleArea().x+" " + "y = "+getVisibleArea().y);

	}


}
