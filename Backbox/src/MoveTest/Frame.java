/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MoveTest;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.util.LinkedList;

import javax.swing.JFrame;


@SuppressWarnings("serial")
public class Frame extends JFrame{
    
    final Player player;
    final Background bg;

       
    private BufferStrategy strat;
    private LinkedList<Enemy> enemys;
    private LinkedList<Bullet> bullets;
    private LinkedList<Explosion> explosions;
    Font myFont = new Font("Arial", 1, 25);
    
    
    public Frame(Player player, LinkedList<Enemy> enemys, Background bg, LinkedList<Bullet> bullets, LinkedList<Explosion> explosions){
        super("Main");
        addKeyListener(new Keyboard());
        this.enemys = enemys;
        this.player = player;
        this.bg = bg;
        this.bullets = bullets;
        this.explosions = explosions;
        
    
    }
    
    //Erstellt den Buffer um Screentearing zu verhindern.
    
    public void makeStrat(){
        createBufferStrategy(2);
        strat = getBufferStrategy();
    }
       
    
    public void repaintScreen(){
    	//screen.repaint();
        Graphics g = strat.getDrawGraphics();
        draw(g);
        g.dispose();
        strat.show();
        //To fix massive lags with Linux
        Toolkit.getDefaultToolkit().sync();
        
    }
    
    @SuppressWarnings("static-access")
	private void draw(Graphics g){
        g.drawImage(bg.getLook(), bg.getX(), 0, null);
        g.drawImage(bg.getLook(), bg.getX() + bg.getLook().getWidth(), 0, null);
       
        for(int i = 0; i<enemys.size(); i++) {
        	Enemy e = enemys.get(i);
        	g.drawImage(e.getLook(), (int) e.getBounding().getCenterX(), (int) e.getBounding().getCenterY(), null);
            g.drawOval((int)e.getBounding().getCenterX(), (int)e.getBounding().getCenterY(), 64, 64);
        	g.setColor(Color.red);
        }
        
        for(int i = 0; i<explosions.size(); i++) {
        	Explosion ex = explosions.get(i);
        	g.drawImage(ex.getLook(), (int)ex.getX(), (int)ex.getY(), null);
        }
        
        for(int i = 0;i<bullets.size(); i++){
        	Bullet b = bullets.get(i);
        	g.drawImage(b.getLook(), (int) b.getBounding().getCenterX() +5,(int) b.getBounding().getCenterY() +20, null);
        }
         
        g.drawImage(player.getLook(), (int) player.getBounding().getCenterX(), (int) player.getBounding().getCenterY(), null);
        g.setFont (myFont);
        g.setColor(Color.white);
        g.drawString("Score: " + String.valueOf(Enemy.getScore()), 50, 60);
        if(player.getHealth()==3) {
        	g.drawImage(player.getHeart(), 500, 20, null); 
        	g.drawImage(player.getHeart(), 580, 20, null); 
        	g.drawImage(player.getHeart(), 660, 20, null); 
        }
        else if(player.getHealth()==2) {
        	g.drawImage(player.getHeart(), 500, 20, null); 
        	g.drawImage(player.getHeart(), 580, 20, null); 
        }
        else if(player.getHealth()==1) {
        	g.drawImage(player.getHeart(), 500, 20, null); 
        }
    }   
}