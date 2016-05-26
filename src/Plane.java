
import javax.swing.*;

public class Plane extends Vehicle implements Runnable {
	private Controller ctrl;
	private SubmarineMain game;

	public Plane(double x, double y, double speed, SubmarineMain game, Controller ctrl) {
		ImageIcon icon = new ImageIcon(".\\image\\plane.png");// SET image
		setIcon(icon);
		this.ctrl = ctrl;
		this.speed = speed;
		this.game = game;
		this.x = x;
		this.y = y;
		setLocation((int) x, (int) y);
		setSize(200, 200);
	}

	// 自動移動
	public void run() {
		int count = 0;
		while (true) {
			setX(get_X() - 1 * speed);
			setY(get_Y() + (Math.random() * 2 - 1.05));
			// 按照機率放出炸彈
			if ((int) (Math.random() * 2000) == 1) {
				new Bomb(get_X(), get_Y() + 70, game, ctrl);
			}

			if (get_X() <= -100) {
				setX(1300);
				speed = Math.random() + 1;
				if (get_Y() <= -50)
					setY(100);
			}
			try {
				Thread.sleep(5);

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}