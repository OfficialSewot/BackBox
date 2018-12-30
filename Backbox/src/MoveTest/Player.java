package MoveTest;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

import javafx.scene.shape.Circle;

public class Player {
    private Circle bounding;
    private float f_posx;
    private float f_posy;
    private int worldsize_x;
    private int worldsize_y;
    private static BufferedImage look;
    private static BufferedImage look_dead;
    private static BufferedImage heart;
    private LinkedList<Bullet> bullets;
    private LinkedList<Enemy> enemys;
    private LinkedList<Explosion> explosions;
    private float timeSinceLastShot = 0;
    private final float SHOTFREQUENZY = 0.4f;
    private boolean alive = true;
    public int health = 3;
    private int sprint;
    
    public Player (int x, int y, int size, int worldsize_x, int worldsize_y, LinkedList<Bullet> bullets, LinkedList<Enemy> enemys, LinkedList<Explosion> explosions){
        try {
            look = ImageIO.read(getClass().getClassLoader().getResourceAsStream("gfx/player.png"));
            look_dead = ImageIO.read(getClass().getClassLoader().getResourceAsStream("gfx/player_destroyed.png"));
            heart = ImageIO.read(getClass().getClassLoader().getResourceAsStream("gfx/heart.png"));
        } catch (IOException ex) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        }
        //bounding = new Rectangle(x, y, look.getWidth(), look.getHeight());
        
        f_posx = x;
        f_posy = y;
        bounding = new Circle(x, y, 32);
        this.worldsize_x = worldsize_x;
        this.worldsize_y = worldsize_y;
        this.bullets = bullets;
        this.enemys = enemys;
        this.sprint = 0;
        
    }



    
    
    public void update (float timeSinceLastFrame){
        if(!alive) return;
    	timeSinceLastShot+=timeSinceLastFrame;
    	
        //Hier wird die Bewegungssteuerung an den Player uebergeben
        if(Keyboard.isKeyDown(KeyEvent.VK_W))f_posy-=300*timeSinceLastFrame;
        if(Keyboard.isKeyDown(KeyEvent.VK_S))f_posy+=300*timeSinceLastFrame;
        if(Keyboard.isKeyDown(KeyEvent.VK_D))f_posx+=300*timeSinceLastFrame;
        if(Keyboard.isKeyDown(KeyEvent.VK_A))f_posx-=300*timeSinceLastFrame;
        
        if(Keyboard.isKeyDown(KeyEvent.VK_W)&&Keyboard.isKeyDown(KeyEvent.VK_SHIFT))f_posy-=400*timeSinceLastFrame;
        if(Keyboard.isKeyDown(KeyEvent.VK_S)&&Keyboard.isKeyDown(KeyEvent.VK_SHIFT))f_posy+=400*timeSinceLastFrame;
        if(Keyboard.isKeyDown(KeyEvent.VK_D)&&Keyboard.isKeyDown(KeyEvent.VK_SHIFT))f_posx+=400*timeSinceLastFrame;
        if(Keyboard.isKeyDown(KeyEvent.VK_A)&&Keyboard.isKeyDown(KeyEvent.VK_SHIFT))f_posx-=400*timeSinceLastFrame;
        
        if(Keyboard.isKeyDown(KeyEvent.VK_SHIFT))sprint++;
        
        
        if(timeSinceLastShot>SHOTFREQUENZY&&Keyboard.isKeyDown(KeyEvent.VK_SPACE)){
        	timeSinceLastShot = 0;
        	bullets.add(new Bullet(f_posx, f_posy, 500, 0));
        }
         
        if(f_posx<0)f_posx=0;
        if(f_posy<0)f_posy=0;
        if(f_posx>worldsize_x-(bounding.getRadius()*2))f_posx=(float) (worldsize_x-(bounding.getRadius()*2));
        if(f_posy>worldsize_y-(bounding.getRadius()*2))f_posy=(float) (worldsize_y-(bounding.getRadius()*2));
    
        
        
        
        bounding.setCenterX(f_posx);
        bounding.setCenterY(f_posy);
         
         for(int i = 0; i<enemys.size(); i++) {
        	 Enemy e = enemys.get(i);
        	 
        	    
        	 if(e.isAlive()&&collides(bounding, e.getBounding())){
        		 health--;
        		 enemys.remove(e);
        		 if(health==0) {
        			 alive = false;
        			 
        		 }
        	 }
      	 }
    }
    private static boolean collides(Circle c1, Circle c2) {
		return Math.pow(c1.getCenterX() - c2.getCenterX(), 2) + Math.pow(c1.getCenterY() - c2.getCenterY(), 2) <= Math.pow(c1.getRadius() + c2.getRadius(), 2);
	}
    public boolean isAlive() {
    	return alive;
    }
    
    public Circle getBounding(){
        return bounding;
    }
    
    public int sprint(){
        return sprint;
    }
    
    public BufferedImage getLook(){
        if(alive) return look;
        else return look_dead;
    }
    
    public BufferedImage getHeart() {
    	return heart;	
    }
    
    public int getHealth() {
    	return health;
    }
}

