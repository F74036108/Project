import javax.swing.*;

public class Ship extends Vehicle {
	private ImageIcon icon = new ImageIcon("./image/Troop_Ship.png");// LOAD image
	private ImageIcon icon2 = new ImageIcon("./image/broken-ship.gif");
	public Ship(int x, int y) {
		super(x, y, 0.0);
		setSize(189, 129);
		resetIcon();
	}

	public void change_picture() {
		setIcon(icon2);
	}

	public void resetIcon() {
		setIcon(icon);
	}

}
