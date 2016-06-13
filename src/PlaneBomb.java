import java.util.LinkedList;

import javax.swing.*;

public class PlaneBomb extends JLabel implements Runnable{
//	protected static LinkedList<PlaneBomb> b = new LinkedList<PlaneBomb>();
	private int x,y;
	private ImageIcon icon = new ImageIcon(".\\image\\bb.png");
	private static SubmarineMain game;

	
	public PlaneBomb(SubmarineMain game){
		PlaneBomb.game=game;
	}
	public PlaneBomb(int x, int y){
		this.x=x;
		this.y=y;
		setIcon(icon);
		setLocation((int) x, (int) y);
		setSize(60, 60);
	}
	public void run() {
		while(true){
			setLocation(x, y++);
			double diffX = x - game.ship.get_X();   
			double diffY = y - game.ship.get_Y();   
			if (diffX > -60 && diffX <= 120 && diffY > 10 && diffY < 25) {
				System.out.println("touch!!");
				game.sub_health();
				game.remove(this);
				break;
			}
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public void addBomb(int x, int y){
//		System.out.println(b.size());
		PlaneBomb bomb = new PlaneBomb(x,y);
//		b.add(bomb);
		game.add(bomb);
		
		Thread thread = new Thread(bomb);
		thread.start();
	}
}
