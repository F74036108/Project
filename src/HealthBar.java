/**********************
 *Health bar with artwork
 *
 *@author �\���E
 **********************/
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class HealthBar extends JLabel {
	private int health = 100;
	private int max_health = 100;
	// SubmarineMain game;
	ImageIcon icon1 = new ImageIcon("./image/100.png");
	ImageIcon icon2 = new ImageIcon("./image/90.png");
	ImageIcon icon3 = new ImageIcon("./image/80.png");
	ImageIcon icon4 = new ImageIcon("./image/70.png");
	ImageIcon icon5 = new ImageIcon("./image/60.png");
	ImageIcon icon6 = new ImageIcon("./image/50.png");
	ImageIcon icon7 = new ImageIcon("./image/40.png");
	ImageIcon icon8 = new ImageIcon("./image/30.png");
	ImageIcon icon9 = new ImageIcon("./image/20.png");
	ImageIcon icon10 = new ImageIcon("./image/10.png");
	ImageIcon icon11 = new ImageIcon("./image/0.png");

	public HealthBar() {
		setIcon(icon1);
		setLocation(90, 0);
		setSize(400, 22);
	}

	public void minusHealth(int a) {
		health -= a;
		setHealthBar();
	}

	public int get_health() {
		return health;
	}

	public void refillhealth() {
		if (health + 10 >= max_health) {
			health = max_health;
		} else {
			health += 10;
		}
		setHealthBar();
	}

	public int getHealth() {
		return health;
	}

	public void reset() {
		health = 100;
		setHealthBar();
	}

	private void setHealthBar() {
		if (health == 100) {
			this.setIcon(icon1);
		} else if (health == 90) {
			this.setIcon(icon2);
		} else if (health == 80) {
			this.setIcon(icon3);
		} else if (health == 70) {
			this.setIcon(icon4);
		} else if (health == 60) {
			this.setIcon(icon5);
		} else if (health == 50) {
			this.setIcon(icon6);
		} else if (health == 40) {
			this.setIcon(icon7);
		} else if (health == 30) {
			this.setIcon(icon8);
		} else if (health == 20) {
			this.setIcon(icon9);
		} else if (health == 10) {
			this.setIcon(icon10);
		} else if (health <= 0) {
			this.setIcon(icon11);
		}
	}

}
