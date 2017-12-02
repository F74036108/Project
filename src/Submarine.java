import javax.swing.*;

public class Submarine extends GameObject implements Runnable {

	public Submarine(int x, int y, double speed) {
		super(x, y, speed);
		setLocation(x, y);
		setSize(142, 49);
		setInitialIcon();
	}

	public void run() {
		while (true) {
			if (getCrashedStatus() == true)
				break;
			setX(get_X() + (int) getSpeed());
			if (get_X() >= 1200 && getSpeed() >= 0)
				setX(-200);
			if (get_X() <= -200 && getSpeed() < 0)
				setX(1200);
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	public void setInitialIcon() {
		// TODO Auto-generated method stub
		if (getSpeed() > 0) {
			setIcon(new ImageIcon("./image/submarine2.png"));// SET image
		} else {
			setIcon(new ImageIcon("./image/submarine3.png"));// SET image
		}
	}
}
