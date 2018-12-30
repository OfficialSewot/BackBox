package MoveTest;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;



public class Explosion {

	private static BufferedImage look;
	private LinkedList<Enemy> enemys;
	static{
		try {
            look = ImageIO.read(Bullet.class.getClassLoader().getResourceAsStream("gfx/explosion.png"));
        } catch (IOException ex) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        }
	}
	
	private float posx;
	private float posy;
	
	public Explosion (float posx, float posy, LinkedList<Enemy> enemys) {
		
		this.posx = posx;
		this.posy =posy;
		
		
	}
}
