import javax.swing.*;

public class Plane extends Vehicle implements Runnable {
	private SubmarineMain game;
	PlaneBomb bomb;

	public Plane(double x, double y, double speed, SubmarineMain game) {
		ImageIcon icon = new ImageIcon(".\\image\\plane.png");// SET image
		setIcon(icon);
		this.speed = speed;
		this.game = game;
		this.x = x;
		this.y = y;
		setLocation((int) x, (int) y);
		setSize(200, 200);
		bomb = new PlaneBomb(game);
	}

	// 自動移動
	public void run() {
		int count = 0;
		while (true) {
			setX(get_X() - speed);
			setY(get_Y() + (Math.random()-0.51));
			// 按照機率放出炸彈
			if ((int) (Math.random() * 2000) == 1.0 && get_X()<1000) {
				bomb.addBomb((int)get_X(), (int)get_Y()+70);
			}

			if (get_X() <= -100) {
				setX(1000);
				if (get_Y() <= -50)
					setY(50);
			}
			try {
				Thread.sleep(5);

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}