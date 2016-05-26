import javax.swing.*;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Frontpage extends JFrame{
	int HEIGHT;
	int WIDTH;
	public static enum State{Start,Front};
	public static State state;
	
	Rectangle startButton = new Rectangle(SubmarineMain.get_width() / 2 -50 , 175 ,100,50);
	public Frontpage(int height,int width){
		this.WIDTH = width;
		this.HEIGHT = height;
	    this.addMouseListener(new MouseInput(this));
	    state = State.Front;
	}
	public void action(){
	}
	
	public void paint(Graphics g){
		super.paint(g);
		Graphics2D g2d = (Graphics2D)g;
		g2d.draw(startButton);
		g2d.drawString("Start",SubmarineMain.get_width() / 2 -12, 175 +30);
		
	}
	public void set_start_state(){
		state = State.Start;
	}
	public State get_state(){
		return state;
	}
		
}
