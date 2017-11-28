/*********************************
 * Ancestor of elements in SubMmarineMain
 * 
 * Direct Known Subclasses:
 *  Submarine, Ship, Plane, Bomb, ToxicSeaBomb, Laser
 **********************************/
import javax.swing.*;
public abstract class Vehicle extends JLabel{

	private int x;
	private int y;
	private double speed;
	
	private boolean crashed;
	
	public Vehicle(int x, int y, double speed) {
		setLocation(x, y);
		setInitialIcon();
		this.speed=speed;
	}
	//重設LABEL位置
	private void resetLocation(){
		setLocation(x, y);
	}
	public void setX(int xx){
		x = xx;
		resetLocation();
	}
	public void setY(int yy){
		y = yy;
		resetLocation();
	}
	public int get_X(){
		return x;
	}
	public int get_Y(){
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
	
	public abstract void setInitialIcon();
}
