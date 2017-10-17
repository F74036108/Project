import javax.swing.*;

public class Plane extends Vehicle implements Runnable {
	private SubmarineMain game;
	PlaneBomb bomb;
	private static boolean reset;

	public Plane(double x, double y, double speed, SubmarineMain game) {
		ImageIcon icon = new ImageIcon("./image/plane.png");// SET image
		setIcon(icon);
		reset = false;
		this.speed = speed;
		this.game = game;
		this.x = x;
		this.y = y;
		setLocation((int) x, (int) y);
		setSize(200, 200);
		bomb = new PlaneBomb(game);
	}

	// Automove
	public void run() {
		int count = 0;
		while (true) {
			if(reset)
				break;
			setX(get_X() - speed);
			setY(get_Y() + (Math.random() - 0.51));
			//Randomly dropped Bomb
			if ((int) (Math.random() * 1700) == 1.0 && get_X() < 1000) {
				bomb.addBomb((int) get_X(), (int) get_Y() + 70);
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
	
	public static void resetPlane() {
		reset = true;
	}
}