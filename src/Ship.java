import javax.swing.*;

public class Ship extends Vehicle {
	ImageIcon icon = new ImageIcon(".\\image\\Troop_Ship2.png");// LOAD image

	public Ship(double x, double y) {

		setIcon(icon);
		this.x = x;
		this.y = y;
		setLocation((int) x, (int) y);
		setSize(189, 129);
	}

	public void change_picture() {
		ImageIcon icon2 = new ImageIcon(".\\image\\broken-ship.gif");
		setIcon(icon2);
	}

	public void resetIcon() {
		setIcon(icon);
	}

}
