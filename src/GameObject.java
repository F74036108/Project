
/*********************************
 * Ancestor of elements in SubMmarineMain
 * 
 * Direct Known Subclasses:
 *  Submarine, Ship, Plane, Bomb, ToxicSeaBomb, Laser
 **********************************/
import javax.swing.*;

public class GameObject extends JLabel {

	private int x;
	private int y;
	private double speed;

	private boolean crashed;

	public GameObject(int x, int y, double speed) {
		setLocation(x, y);
		this.x = x;
		this.y = y;
		this.speed = speed;
		this.crashed = false;
	}

	// 重設LABEL位置
	private void resetLocation() {
		setLocation(x, y);
	}

	public void setX(int xx) {
		this.x = xx;
		resetLocation();
	}

	public void setY(int yy) {
		this.y = yy;
		resetLocation();
	}

	public int get_X() {
		return x;
	}

	public int get_Y() {
		return y;
	}

	public double getSpeed() {
		return speed;
	}

	public void setCrash() {
		crashed = true;
	}

	public boolean getCrashedStatus() {
		return crashed;
	}

}
