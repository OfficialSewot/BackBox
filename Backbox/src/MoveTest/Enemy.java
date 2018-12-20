package MoveTest;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

public class Enemy {
	
	private static BufferedImage look;
	private static BufferedImage look_dead;
	private LinkedList<Bullet> bullets;
	private boolean alive = true;
	
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
		
		for(int i = 0; i<bullets.size(); i++) {
			Bullet b = bullets.get(i);
			
			if(bounding.intersects(b.getBounding())) {
				alive = false;
			}
		}
        
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

}
