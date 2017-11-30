
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Bomb extends GameObject implements Runnable {
	Bomb tempBomb;
	SubmarineMain game;
	private static ImageIcon icon = new ImageIcon("./image/bomb.png");
	private static ImageIcon icon3 = new ImageIcon("./image/explo2.gif");
	private static ImageIcon icon4 = new ImageIcon("./image/explore.gif");
	JLabel explode;
	JLabel explode2;
	static JLabel explo_anmi;
	private static SoundBase bombPathMusic = new SoundBase("./audio/bomb_path.wav");
	private static SoundBase bigBombMusic = new SoundBase("./audio/explode.wav");

	public Bomb(int x, int y, SubmarineMain game) {
		super(x, y, 0);
		this.game = game;

		setSize(60, 60);
		explode2 = new JLabel(icon3);
		explode2.setSize(450, 255);
		// ImageIcon icon4 = new ImageIcon("./image/explore.gif");
		explo_anmi = new JLabel(icon4);
		explo_anmi.setSize(1000, 700);

		setIcon(icon);

	}

	public void playDroppingSound() {
		bombPathMusic.play();
	}
	
	public void stopDroppingSound() {
		bombPathMusic.stop();
	}
	
	private void removeSubmarineAndCreate(int submarinIndex) {
		game.remove(game.sub[submarinIndex]);
		game.sub[submarinIndex].setCrash();
		game.sub[submarinIndex] = null;
		game.remove(this);
		
		// Create new Sub
		game.addSubmarine(submarinIndex);
	}
	
	
	private boolean handleCollisionWithSubmarine() {
		for (int j = 0; j < 6; j++) {
			if (game.sub[j] == null)
				continue;
			double diffX = this.get_X() - game.sub[j].get_X();
			double diffY = this.get_Y() - game.sub[j].get_Y();
			if (diffX > -60 && diffX <= 120 && diffY > -10 && diffY < 15) {
				removeSubmarineAndCreate(j);
				stopDroppingSound();
				AttackController.showExplosion(game, this.get_X(), this.get_Y());
				
				// 分數累加
				game.addScore(10);
				return true;
			}
		}
		return false;
	}
	
	private boolean handleCollisionWithToxicSeaBomb() {
		// Check 碰到toxic bomb
		double diffX = this.get_X() - game.seaBomb.get_X();
		double diffY = this.get_Y() - game.seaBomb.get_Y();
		if (diffX > -60 && diffX <= 110 && diffY > 5 && diffY < 25) {
			// 爆炸setLocation
			explode2.setLocation((int) this.get_X() - 160, (int) this.get_Y() - 120);
			// handle 爆炸後
			// remove LABEL
			game.remove(game.seaBomb);
			game.seaBomb.setCrash();
			game.remove(this);
			game.backGroundMusic.pause();
			bombPathMusic.pause();
			// Create new Sub
			game.addToxicBomb();
			// 爆炸
			game.add(explode2);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			game.remove(explode2);
			// 動畫
			explo_anmi.setLocation(0, 0);
			bigBombMusic.play();
			game.add(explo_anmi);
			try {
				Thread.sleep(2100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// 扣health
			game.sub_health(40);
			game.remove(explo_anmi);
			bombPathMusic.stop();
			game.backGroundMusic.resume();
			return true;
		}
		return false;
	}
	
	private boolean handleCollisionWithSubmarineUser() {
		// Check 碰到User
		double diffX = this.get_X() - game.subUser.get_X();
		double diffY = this.get_Y() - game.subUser.get_Y();
		if (diffX > -60 && diffX <= 110 && diffY > 5 && diffY < 25) {

			game.remove(game.subUser);
			game.remove(this);
			// Create new Sub
			game.addUserSub();
			
			stopDroppingSound();
			// 爆炸動畫
			AttackController.showExplosion(game, this.get_X(), this.get_Y());
			
			game.addScore(50);
			return true;
		}
		return false;
	}
	
	public void run() {
		while (true) {
			setY(get_Y() + 1);
			if (get_Y() <= 700) { // check 炸彈碰到潛艇
				if(handleCollisionWithSubmarine()) break;
				if(handleCollisionWithToxicSeaBomb()) break;
				if(handleCollisionWithSubmarineUser()) break;
			} else if (get_Y() > 700) {
				bombPathMusic.stop();
				game.remove(this);
				break;
			}
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
}