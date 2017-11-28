/**************************
 * User Control Submarine
 * 
 * @author ���a�v
 **************************/
import javax.swing.ImageIcon;
public class SubmarineUser extends Vehicle {
	private ImageIcon icon = new ImageIcon("./image/Submarine4.png");// LOAD image

	private ImageIcon icon2 = new ImageIcon("./image/Submarine5.png");// LOAD image
	
	public SubmarineUser(int x, int y) {
		super(x, y, 0);
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

	@Override
	public void setInitialIcon() {
		// TODO Auto-generated method stub
		setIcon(icon);
	}

}
