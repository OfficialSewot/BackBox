package MoveTest;


import java.awt.DisplayMode;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.KeyEvent;
import java.util.LinkedList;

import javax.swing.JFrame;


// Main class
public class MoveTest {
    public static void RunGame(int width, int height, int bitDepth, int refreshRate, boolean fullscreen, boolean customResolution) {
        
        //Player, Background, Frame and Bullet is initialized
        
    	
    	LinkedList<Bullet> bullets = new LinkedList<Bullet>();
        Player player = new Player(300, 300, 50, width, height, bullets);
        Background bg = new Background(100);
        Enemy enemy = new Enemy(300, 300, 50, width, height);
        Frame f = new Frame(player, enemy, bg, bullets);
        
        //Frame settings
        
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(width, height);
        f.setUndecorated(true);
        f.setVisible(true);
        f.setResizable(false);
        f.setLocationRelativeTo(null);
        f.makeStrat();

        DisplayMode displayMode = new DisplayMode (width, height, bitDepth, refreshRate);
        GraphicsEnvironment environment = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice device = environment.getDefaultScreenDevice();
        
        if (fullscreen = true) {
			device.setFullScreenWindow(f);
		}
		
        if (customResolution = false) {
			device.setDisplayMode(displayMode);
		}
        
		long lastFrame = System.currentTimeMillis();
        
        //Loop that updates important things
        while(true){
            if(Keyboard.isKeyDown(KeyEvent.VK_ESCAPE))System.exit(0);
            long thisFrame = System.currentTimeMillis();
            float timeSinceLastFrame = ((float)(thisFrame-lastFrame)) / 1000f;
            lastFrame = thisFrame;
            
            player.update(timeSinceLastFrame);
            enemy.update(timeSinceLastFrame);
            bg.update(timeSinceLastFrame);
            f.repaintScreen();
            
            for(int i = 0; i<bullets.size(); i++){
            	bullets.get(i).update(timeSinceLastFrame);
            }
            
            try {
            	Thread.sleep(15);
            } catch (InterruptedException e) {
            	e.printStackTrace();
            }
            
        }
        
    }
    
}