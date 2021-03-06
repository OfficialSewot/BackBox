package MoveTest;


import java.awt.Dimension;
import java.awt.DisplayMode;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.Random;

import javax.swing.JFrame;


// Main class
public class Main {
	
	static LinkedList<Bullet> bullets = new LinkedList<Bullet>();
	static Background bg = new Background(50);
	static LinkedList<Enemy> enemys = new LinkedList<Enemy>();
	static LinkedList<Explosion> explosions = new LinkedList<Explosion>();
	static Player player = new Player(300, 300, 50, 1920, 1080, bullets, enemys, explosions);
	static Random r = new Random();

	
    public static void main(String[] args) {
        
        //Player, Background, Frame and Bullet is initialized
        
    	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    	
    	int width = (int) screenSize.getWidth();
    	int height = (int) screenSize.getHeight(); 
    	int bitDepth = 32; 
    	int refreshRate = 60;
    	
        //Frame settings
        Frame f = new Frame(player, enemys, bg, bullets, explosions);       
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(width, height);
        f.setUndecorated(true);
        f.setVisible(true);
        f.setResizable(false);
        f.setLocationRelativeTo(null);
        f.makeStrat();
        
        final float ENEMYSPAWNTIME = 1f;
        float timeSinceLastEnemySpawn = 0;
        
        DisplayMode displayMode = new DisplayMode (width, height, bitDepth, refreshRate);
        GraphicsEnvironment environment = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice device = environment.getDefaultScreenDevice();
        if(device.isFullScreenSupported())device.setFullScreenWindow(f);
		if(device.isDisplayChangeSupported())device.setDisplayMode(displayMode);
        
		long lastFrame = System.currentTimeMillis();
    	//spawnEnemy();
		timeSinceLastEnemySpawn++;
        //Loop that updates important things
        while(true){
            if(Keyboard.isKeyDown(KeyEvent.VK_ESCAPE))System.exit(0);
            long thisFrame = System.currentTimeMillis();
            float timeSinceLastFrame = ((float)(thisFrame-lastFrame)) / 1000f;
            lastFrame = thisFrame;
            timeSinceLastEnemySpawn+=timeSinceLastFrame;
            
            if(timeSinceLastEnemySpawn>ENEMYSPAWNTIME) {
            	timeSinceLastEnemySpawn-=ENEMYSPAWNTIME;
            	spawnEnemy(width, height);
            }
            
            player.update(timeSinceLastFrame);
            bg.update(timeSinceLastFrame);
            f.repaintScreen();
            
            for(int i = 0; i<bullets.size(); i++){
            	bullets.get(i).update(timeSinceLastFrame);
            }
            
            for(int i = 0; i<enemys.size(); i++) {
            	enemys.get(i).update(timeSinceLastFrame);
            }
            
            for(int i = 0; i<explosions.size(); i++) {
            	explosions.get(i).update(timeSinceLastFrame);
                for(int j = 0; j<explosions.size(); j++) {
       			 Explosion ex = explosions.get(j);
       			 if(ex.getAniTime()>=0.80)explosions.remove(ex);        		 
       		 }
            }
            
            try {
            	Thread.sleep(15);
            } catch (InterruptedException e) {
            	e.printStackTrace();
            }
            
        }
        
    }
    
    
    public static void spawnEnemy(int width, int height) {
//    	enemys.add(new Enemy((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth(),r.nextInt((int) Toolkit.getDefaultToolkit().getScreenSize().getHeight()-Enemy.getHeight()), bullets, explosions));
    	enemys.add(new Enemy(width,r.nextInt(height-Enemy.getHeight()), bullets, explosions));
    }
    
}