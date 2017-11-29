
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class PentaKill extends GameObject implements KeyPressedControl{
	
	private SubmarineMain game;

	private static ImageIcon icon1 = new ImageIcon("./image/many bombs1.png");
	private static ImageIcon icon2 = new ImageIcon("./image/many bombs2.png");
	private static ImageIcon icon3 = new ImageIcon("./image/many bombs3.png");
	private static ImageIcon iconPenta = new ImageIcon("./image/big kill2.gif");
	private static JLabel pentaAnimation = new JLabel(iconPenta);
	
	public PentaKill(int x, int y, SubmarineMain game) {
		super(x, y, 0);
		// TODO Auto-generated constructor stub
		setIcon(icon3);
		setSize(80, 110);
		
		this.game=game;
		pentaAnimation.setSize(185, 190);
	}


	public void changeIcon(int killCount) {
		if (killCount == 1)
			setIcon(icon2);
		else if (killCount == 2)
			setIcon(icon1);
		else if (killCount == 3)
			setVisible(false);
	}
	
	public void showPentaAnimationOnShip() {
		pentaAnimation.setLocation(game.ship.get_X(), game.ship.get_Y());
	}

	private boolean keyQPressed = false;
	private boolean keyEPressed = false;
	
	@Override
	public void keyPressedAction(int key) {
		// TODO Auto-generated method stub
		if (key == KeyEvent.VK_Q || key == KeyEvent.VK_E) {
			if (key == KeyEvent.VK_Q)
				keyQPressed = true;
			if (key == KeyEvent.VK_E)
				keyEPressed = true;

			if (keyEPressed && keyQPressed) {
				AttackController.pentaKill(game);
				keyEPressed = false;
				keyQPressed = false;
			}
		}
	}
}
