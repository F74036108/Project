
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Laser extends JLabel implements Runnable {
	private int x, y;
	private ImageIcon icon = new ImageIcon(".\\image\\bullet.png");
	private SubmarineMain game;
	private static boolean reset;
	private static int speed=0;
	

	public Laser(int x, int y, SubmarineMain game) {
		this.game = game;
		this.x = x;
		this.y = y;
		reset = false;
		setIcon(icon);
		setLocation((int) x, (int) y);
		setSize(86, 100);
	}

	public void run() {
		while (true) {
			speed++;
			setLocation(x, y-=speed);
			double diffX = x - game.ship.get_X();
			double diffY = y - game.ship.get_Y();
			if ((diffX > -30 && diffX <= 200 && diffY > 0 && diffY < 110)) {
				speed=0;
				game.sub_health(10);
				game.remove(this);
				break;
			}
			if (reset || y < 250) {
				speed=0;
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

	public void addLaser(int x, int y) {
		Laser laser = new Laser(x, y, game);
		game.add(laser);
		Thread thread = new Thread(laser);
		thread.start();
	}

	public static void resetPlaneBomb() {
		reset = true;
	}
}
