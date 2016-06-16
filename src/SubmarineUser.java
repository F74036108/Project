/**************************
 * User Control Submarine
 **************************/
import javax.swing.ImageIcon;
public class SubmarineUser extends Vehicle {
	ImageIcon icon = new ImageIcon(".\\image\\Submarine4.png");// LOAD image

	ImageIcon icon2 = new ImageIcon(".\\image\\Submarine5.png");// LOAD image
	
	public SubmarineUser(double x, double y) {

		setIcon(icon);
		this.x = x;
		this.y = y;
		setLocation((int) x, (int) y);
		setSize(142, 49);
	}

	public void changeDirection(String dir) {
		if(dir=="right"){
			this.setIcon(icon);
		}else if(dir=="left"){
			this.setIcon(icon2);
		}
	}

	public void reset() {
		setIcon(icon);
	}
	public void setCrash() {
		
		this.setEnabled(false);
	}

}
