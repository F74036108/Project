
/****
 * Laser  from User Submarine
 * 
 * Created in SubmarineMain class method - keyPressed()
 *
 *@author ���a�v
 ****/
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Laser extends GameObject implements Runnable {

	private ImageIcon icon = new ImageIcon("./image/bullet.png");
	private static SoundBase laserMusic = new SoundBase("./audio/lazer.wav");
	
	private SubmarineMain game;
	private static boolean reset;
	private static int speed = 0;

	public Laser(int x, int y, SubmarineMain game) {
		super(x, y, 0);
		this.game = game;
		reset = false;
		setSize(86, 100);
		setIcon(icon);
	}
	
	public void playLaserSound() {
		laserMusic.play();
	}

	public void run() {

		while (true) {
			speed++;
			setY(get_Y() - speed);
			double diffX = get_X() - game.ship.get_X();
			double diffY = get_Y() - game.ship.get_Y();
			if ((diffX > -30 && diffX <= 200 && diffY > 0 && diffY < 110)) {
				speed = 0;
				game.sub_health(10);
				game.remove(this);
				break;
			}
			if (reset || get_Y() < 250) {
				speed = 0;
				game.remove(this);
				break;
			}
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
