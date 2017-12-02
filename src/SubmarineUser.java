
/**************************
 * User Control Submarine
 * 
 * @author ���a�v
 **************************/
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

public class SubmarineUser extends GameObject implements KeyPressedControl{
	private ImageIcon icon = new ImageIcon("./image/Submarine4.png");// LOAD image

	private ImageIcon icon2 = new ImageIcon("./image/Submarine5.png");// LOAD image

	private SubmarineMain game;
	public SubmarineUser(int x, int y, SubmarineMain game ) {
		super(x, y, 0.0);
		setSize(142, 49);
		reset();
		
		this.game=game;
	
	}

	public void changeDirection(String dir) {
		if (dir == "right") {
			this.setIcon(icon);
		} else if (dir == "left") {
			this.setIcon(icon2);
		}
	}

	public void reset() {
		setIcon(icon);
	}

	
//	private long lastShoot2 = System.currentTimeMillis();
	@Override
	public void keyPressedAction(int key) {
		// TODO Auto-generated method stub
		if (key == KeyEvent.VK_UP) {
			if (get_Y() >= 300)
				setY(get_Y() - 5);// Up shift
		} else if (key == KeyEvent.VK_DOWN) {
			if (get_Y() <= 600)
				setY(get_Y() + 5);// Down shift
		} else if (key == KeyEvent.VK_LEFT) {
			if (get_X() > -25) {
				setX(get_X() - 10);// Left shift
				changeDirection("left");
			}
		} else if (key == KeyEvent.VK_RIGHT) {
			if (get_X() < 850) {
				setX(get_X() + 10);// Right shift
				changeDirection("right");
			}
		} else if (key == KeyEvent.VK_ENTER) {
			AttackController.addLaser(game, get_X() + 40, get_Y() - 50);
		} 
	}

}
