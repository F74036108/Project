
/*****************
 * Plane Bomb object
 * 
 * @author �Τ���
 *****************/
import javax.swing.*;

public class PlaneBomb extends GameObject implements Runnable {
	private ImageIcon icon = new ImageIcon("./image/planebomb.png");
	private SubmarineMain game;
	private static boolean reset;
	static JLabel hit = new JLabel(new ImageIcon("./image/hittted-n.gif"));

	public PlaneBomb(int x, int y, SubmarineMain game) {
		super(x, y, 0);
		this.game=game;
		
		reset = false;
		setIcon(icon);
		setSize(30, 51);
		hit.setSize(320, 200);
	}

	public void run() {
		while (true) {
			setX(get_X()-1);
			setY(get_Y()+2);
			double diffX = get_X() - game.ship.get_X();
			double diffY = get_Y() - game.ship.get_Y();
			if ((diffX > -30 && diffX <= 200 && diffY > 0 && diffY < 130)) {
				game.subHealth(10);
				game.remove(this);

				hit.setLocation((int) game.ship.get_X() - 65, (int) game.ship.get_Y() - 70);
				game.add(hit);
				// hit.setVisible(true);
				try {
					Thread.sleep(300);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// hit.setVisible(false);
				game.remove(hit);
				break;
			}
			if (reset || get_Y() > 250) {
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

	public static void resetPlaneBomb() {
		reset = true;
	}
}
