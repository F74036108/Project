/*********************************
 * Ancestor of elements in SubMmarineMain
 * 
 * Direct Known Subclasses:
 *  Submarine, Ship, Plane, Bomb, ToxicSeaBomb, Laser
 */
import javax.swing.*;
public class Vehicle extends JLabel{

	protected double x;
	protected double y;
	protected double speed;
	
	//重設LABEL位置
	public void resetLocation(){
		setLocation((int)x, (int)y);
	}
	public void setX(double xx){
		x = xx;
		resetLocation();
	}
	public void setY(double yy){
		y = yy;
		resetLocation();
	}
	public double get_X(){
		return x;
	}
	public double get_Y(){
		return y;
	}
}
