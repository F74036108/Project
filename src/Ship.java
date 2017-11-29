import java.awt.event.KeyEvent;

import javax.swing.*;

public class Ship extends GameObject implements KeyPressedControl{
	private ImageIcon icon = new ImageIcon("./image/Troop_Ship.png");// LOAD image
	private ImageIcon icon2 = new ImageIcon("./image/broken-ship.gif");
	private SubmarineMain game;
	public Ship(int x, int y, SubmarineMain game) {
		super(x, y, 5);
		setSize(189, 129);
		resetIcon();
		
		this.game=game;
	}

	public void change_picture() {
		setIcon(icon2);
	}

	public void resetIcon() {
		setIcon(icon);
	}

//	private long lastShoot = System.currentTimeMillis();
	// 主艦炸彈
	@Override
	public void keyPressedAction(int key) {
		// TODO Auto-generated method stub
		if (key == KeyEvent.VK_D) {// Right shift
			if (get_X() < 850)
				setX(get_X() + (int)getSpeed());
		} else if (key == KeyEvent.VK_A) {// Left shift
			if (get_X() > -50)
				setX(get_X() - (int)getSpeed());
		} else if (key == KeyEvent.VK_SPACE) {
			AttackController.addBomb(game, get_X() + 80, get_Y() + 80);
		}
	}

}
