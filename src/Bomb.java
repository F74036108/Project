import javax.swing.*;

public class Bomb extends Vehicle implements Runnable {
	Controller ctrl;
	Bomb tempBomb;

	public Bomb(double x, double y, Controller ctrl) {
		ImageIcon icon;
		if(ctrl.type==1)
			icon = new ImageIcon(".\\image\\bomb.png");// SET image
		else {
			icon = new ImageIcon(".\\image\\bb.png");
			System.out.println("!!!!!!!");
		}
		setIcon(icon);
		this.x = x;
		this.y = y;
		this.ctrl = ctrl;

		ctrl.add_Bomb(this);

		setLocation((int) x, (int) y);
		setSize(50,50);
	}

	public void run() {
		while (true) {
			setY(get_Y() + 2);
			if (get_Y() > 700) {

				// remove BOMBs out of bounds
				ctrl.remove();
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

}
