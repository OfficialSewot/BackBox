/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MoveTest;

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
    private LinkedList<Bullet> bullets;
    
    
    public Frame(Player player, Background bg, LinkedList<Bullet> bullets){
        super("MoveTest");
        addKeyListener(new Keyboard());
        this.player = player;
        this.bg = bg;
        this.bullets = bullets;
    
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
        //To fix massive lags with Linux, flushes the Graphics buffer which Linux uses.
        Toolkit.getDefaultToolkit().sync();
        
    }
    
    @SuppressWarnings("static-access")
	private void draw(Graphics g){
        g.drawImage(bg.getLook(), bg.getX(), 0, null);
        g.drawImage(bg.getLook(), bg.getX() + bg.getLook().getWidth(), 0, null);
        for(int i = 0;i<bullets.size(); i++){
        	Bullet b = bullets.get(i);
        	g.drawImage(b.getLook(), b.getBounding().x +5, b.getBounding().y +20, null);
        }
        g.drawImage(player.getLook(), player.getBounding().x, player.getBounding().y, null);
            
    }
}