package MoveTest;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

public class Enemy {
	
	private static BufferedImage look;
	private static BufferedImage look_dead;
	private final static Random r = new Random();
	private LinkedList<Bullet> bullets;
	private LinkedList<Enemy> enemys;
	private boolean alive = true;
	private static int score = 0;
	
    static {
    	try {
            look = ImageIO.read(Enemy.class.getClassLoader().getResourceAsStream("gfx/enemy.png"));
            look_dead = ImageIO.read(Enemy.class.getClassLoader().getResourceAsStream("gfx/enemy_destroyed.png"));
        } catch (IOException ex) {
            Logger.getLogger(Enemy.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
	private Rectangle bounding;
    private float posx;
    private float posy;
    
    public Enemy(int x, int y, LinkedList<Bullet> bullets) {
    	
    	bounding = new Rectangle(x, y, look.getWidth(), look.getHeight());
    	this.posx = x;
		this.posy = y;
		this.bullets = bullets;
		
	}
    


	public void update(float timeSinceLastFrame) {
		posx-=200*timeSinceLastFrame;
		if(posx<=-getLook().getWidth()) {
			posx = 1920;
			posy = r.nextInt(1080-getLook().getHeight());
			alive = true;
		}
		
		for(int i = 0; i<bullets.size(); i++) {
			Bullet b = bullets.get(i);
			
			if(alive&&bounding.intersects(b.getBounding())) {
				alive = false;
				bullets.remove(b);
				score++;
			}
			
		}
        
		
//		if (player.isAlive() && bounding.intersects(bounding)) {
//			enemys.remove(enemys);
//		} 
		
		bounding.x = (int) posx;
		bounding.y = (int) posy;
	}
	
    public Rectangle getBounding(){
        return bounding;
    }
    
    public BufferedImage getLook(){
        if(alive) return look;
        else return look_dead;
    }
    
    public boolean isAlive() {
    	return alive;
    }
    
    public static int getScore() {
    	return score;
    }

    public static int getWidth() {
    	return look.getWidth();
    }
    public static int getHeight() {
    	return look.getHeight();
    }
}
