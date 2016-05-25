import javax.swing.*;
public class Ship extends Vehicle {
	
	public Ship(double x, double y) {
		ImageIcon icon = new ImageIcon(".\\image\\Troop_Ship2.png");// LOAD image									
		setIcon(icon);
		this.x = x;
		this.y = y;
		setLocation((int) x, (int) y);
		setSize(189, 129);
	}

}
