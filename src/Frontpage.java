import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.*;



public class Frontpage extends JFrame{
	int HEIGHT;
	int WIDTH;
	public static enum State{Start,Front};
	public static State state;
	
//	Rectangle startButton = new Rectangle(SubmarineMain.get_width() / 2 -50 , 175 ,100,50);
	public Frontpage(int height,int width){
		this.WIDTH = width;
		this.HEIGHT = height;
		/*JLabel startButton = new JLabel();
		ImageIcon icon = new ImageIcon(".\\image\\START.png");// LOAD image									
		startButton.setIcon(icon);
		startButton.setLocation(SubmarineMain.get_width() / 2 -50 , 175);
		startButton.setSize(600,500);
		this.add(startButton);
		.setVisible(true);*/
		this.addMouseListener(new MouseInput(this));
	    state = State.Front;
	    
	}
	public void action(){
	}
	
	public void paint(Graphics g){
		super.paint(g);
		Graphics2D g2d = (Graphics2D)g;
		Font fnt0 = new Font("arial", Font.BOLD, 80);
		g.setFont(fnt0);
		g.setColor(Color.white);
		g2d.drawString("Submarine WAR",SubmarineMain.get_width() / 2 -290, 200);
		
	}
	public void set_start_state(){
		state = State.Start;
	}
	public State get_state(){
		return state;
	}

}
