
/****
 * Special green bomb down in the sea
 *
 *@author ���a�v
 ****/
import javax.swing.ImageIcon;

public class ToxicSeaBomb extends GameObject implements Runnable {

	private SubmarineMain game;
	private boolean crash;

	public ToxicSeaBomb(int x, int y, double speed, SubmarineMain game) {
		super(x, y, speed);
		this.game = game;
		setSize(100, 100);
		setInitialIcon();
	}

	public void run() {
		while (true) {
			if (crash == true)
				break;
			setX(get_X() + (int) getSpeed());
			if (get_X() >= 1500 && getSpeed() >= 0) {
				game.addToxicBomb();
				game.remove(this);
				break;
			}
			if (get_X() <= -400 && getSpeed() < 0) {
				game.addToxicBomb();
				game.remove(this);
				break;
			}
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	public void setCrash() {
		crash = true;
	}

	public void setInitialIcon() {
		// TODO Auto-generated method stub
		if (getSpeed() > 0) {
			setIcon(new ImageIcon("./image/Toxic_bill2.png"));// SET image
		} else {
			setIcon(new ImageIcon("./image/Toxic_bill3.png"));// SET image
			setX(1300);
		}
	}

}
