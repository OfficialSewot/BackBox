package MoveTest;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

import javafx.scene.shape.Circle;

public class Enemy {
	
	private static BufferedImage look;
	private static BufferedImage look_dead;
	private final static Random r = new Random();
	private LinkedList<Bullet> bullets;
    private LinkedList<Explosion> explosions;
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
    
	private Circle bounding;
    private float posx;
    private float posy;
    
    public Enemy(int d, int y, LinkedList<Bullet> bullets, LinkedList<Explosion> explosions) {
    	
    	bounding = new Circle(d, y, (look.getWidth()/2));
    	this.posx = d;
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
			
			if(alive&&collides(bounding, b.getBounding())){
				alive = false;
				//explosions.add(new Explosion(posx, posy, 50));
				bullets.remove(b);
				score++;
			}
		}
		
		bounding.setCenterX(posx);
        bounding.setCenterY(posy);
	}
	
    private static boolean collides(Circle c1, Circle c2) {
		return Math.pow(c1.getCenterX() - c2.getCenterX(), 2) + Math.pow(c1.getCenterY() - c2.getCenterY(), 2) <= Math.pow(c1.getRadius() + c2.getRadius(), 2);
	}
	
    public Circle getBounding(){
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
