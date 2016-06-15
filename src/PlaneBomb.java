import javax.swing.*;

public class PlaneBomb extends JLabel implements Runnable {
	private int x, y;
	private ImageIcon icon = new ImageIcon(".\\image\\planebomb.png");
	private static SubmarineMain game;
	private static boolean reset;

	public PlaneBomb(SubmarineMain game) {
		PlaneBomb.game = game;
	}

	public PlaneBomb(int x, int y) {
		this.x = x;
		this.y = y;
		reset = false;
		setIcon(icon);
		setLocation((int) x, (int) y);
		setSize(30, 51);
	}

	public void run() {
		while (true) {
			setLocation(x--, y += 2);
			double diffX = x - game.ship.get_X();
			double diffY = y - game.ship.get_Y();
			if ((diffX > -30 && diffX <= 200 && diffY > 0 && diffY < 130)) {
				game.sub_health(10);
				game.remove(this);
				break;
			}
			if (reset || y > 250) {
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

	public void addBomb(int x, int y) {
		PlaneBomb bomb = new PlaneBomb(x, y);
		game.add(bomb);
		Thread thread = new Thread(bomb);
		thread.start();
	}

	public static void resetPlaneBomb() {
		reset = true;
	}
}
