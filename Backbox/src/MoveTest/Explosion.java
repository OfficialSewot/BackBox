package MoveTest;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;



public class Explosion {

	private static BufferedImage tileset;
	private float posx;
	private float posy;
	private static final float NEEDEDANITIME = 1;
	private float aniTime = 0;
	BufferedImage[] look = new BufferedImage[5];
	private int speed;
	private int subx;
	
	static{
		try {
            tileset = ImageIO.read(Bullet.class.getClassLoader().getResourceAsStream("gfx/explosion.png"));
        } catch (IOException ex) {
            Logger.getLogger(Explosion.class.getName()).log(Level.SEVERE, null, ex);
        }
	}
	
	public Explosion (float posx, float posy, float speed) {
		
		this.posx = posx;
		this.posy = posy;
		this.speed = 150;
		this.subx = 0;
        try {
			for(int i = 0; i<5; i++) {
				look[i] = tileset.getSubimage(subx, 0, 64, 64);
				subx=subx+64;
			}
		} catch (Exception e) {
			Logger.getLogger(Explosion.class.getName()).log(Level.SEVERE, "i:"+subx+"look:"+look[0]+look[1] );
		}
	}
	
	public void update (float timeSinceLastFrame) {
		aniTime += timeSinceLastFrame;
		if(aniTime>NEEDEDANITIME) {
			aniTime = 0;
			
		}
		posx-=speed*timeSinceLastFrame;
	}
	
	public Image getLook() {
		if(look.length==0)return null;
		for(int i = 0; i<look.length; i++) {
			if(aniTime<(NEEDEDANITIME/look.length*(i+1))) {
				return look[i];
			}
		}
		return look[look.length-1];
	}

	public float getX() {
		return posx;
	}
	
	public float getY() {
		return posy;
	}
}
