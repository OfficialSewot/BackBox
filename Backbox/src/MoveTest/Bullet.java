package MoveTest;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

import javafx.scene.shape.Circle;




public class Bullet {
	private static BufferedImage look;
	
	static{
		try {
            look = ImageIO.read(Bullet.class.getClassLoader().getResourceAsStream("gfx/schuss.png"));
        } catch (IOException ex) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        }
	}
	
	private float f_posx;
	private float f_posy;
	private float f_speedx;
	private float f_speedy;
	private Circle bounding;
	
	public Bullet(float x, float y, float speedx, float speedy){
		this.f_posx = x;
		this.f_posy = y;
		this.f_speedx = speedx;
		this.f_speedy = speedy;
		bounding = new Circle(x, y, (look.getWidth()/2));
		
	}
	
	public void update(float timeSinceLastFrame){
		f_posx+=f_speedx*timeSinceLastFrame;
		f_posy+=f_speedy*timeSinceLastFrame;
		bounding.setCenterX(f_posx);
        bounding.setCenterY(f_posy);
	}
	
	public Circle getBounding(){
		return bounding;
	}
	
	public static BufferedImage getLook(){
		return look;
	}
	
}
