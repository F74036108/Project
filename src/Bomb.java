
/****
 * Bomb thrown from Ship
 * 
 * Created in SubmarineMain class method - keyPressed()
 *
 *@author 王冠鈞
 ****/
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Bomb extends Vehicle implements Runnable {
	Bomb tempBomb;
	SubmarineMain game;
	static ImageIcon icon = new ImageIcon(".\\image\\bomb.png");
	static ImageIcon icon3 = new ImageIcon(".\\image\\explo2.gif");
	static ImageIcon icon4 = new ImageIcon(".\\image\\explore.gif");
	JLabel explode;
	JLabel explode2;
	static JLabel explo_anmi;
	private static SoundBase bombMusic = new SoundBase(".\\audio\\bomb.wav");
	private static SoundBase bombPathMusic = new SoundBase(".\\audio\\bomb_path.wav");
	private static SoundBase bigBombMusic = new SoundBase(".\\audio\\explode.wav");

	public Bomb(double x, double y, SubmarineMain game) {
		this.game = game;
		this.x = x;
		this.y = y;
		setLocation((int) x, (int) y);
		setSize(60, 60);

		// SET BOMB image
		//ImageIcon icon = new ImageIcon(".\\image\\bomb.png");
		setIcon(icon);
		// EXPLOSION2
		//ImageIcon icon3 = new ImageIcon(".\\image\\explo2.gif");
		explode2 = new JLabel(icon3);
		explode2.setSize(450, 255);
		//ImageIcon icon4 = new ImageIcon(".\\image\\explore.gif");
		explo_anmi = new JLabel(icon4);
		explo_anmi.setSize(1000, 700);

	}

	public void addBomb(int x, int y) {
		Bomb bomb = new Bomb(x, y, game);
		game.add(bomb);
		bombPathMusic.play();
		Thread thread = new Thread(bomb);
		thread.start();
	}

	public void run() {
		LOOP: while (true) {
			setY(get_Y() + 1);
			if (get_Y() <= 700) { // check 炸彈碰到潛艇
				for (int j = 0; j < 6; j++) {
					if (game.sub[j] == null)
						continue;
					double diffX = this.get_X() - game.sub[j].get_X();
					double diffY = this.get_Y() - game.sub[j].get_Y();
					if (diffX > -60 && diffX <= 120 && diffY > -10 && diffY < 15) {
						// handle 爆炸後
						// remove 被炸掉的submarine
						game.remove(game.sub[j]);
						game.sub[j].setCrash();
						game.sub[j] = null;
						game.remove(this);
						// Create new Sub
						game.addSubmarine(j);
						//播放音效
						bombPathMusic.pause();
						bombMusic.play();
						// 爆炸動畫
						ImageIcon icon2 = new ImageIcon(".\\image\\Nuclear_explosion1.gif");
						explode = new JLabel(icon2);
						explode.setSize(325, 275);
						explode.setLocation((int) this.get_X() - 130, (int) this.get_Y() - 120);
						icon2.getImage().flush();
						game.add(explode);
						try {
							Thread.sleep(500);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						// 分數累加
						game.addScore(10);
						game.remove(explode);
						bombPathMusic.stop();
						break LOOP;
					}
				}
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
					break LOOP;
				}
				// Check 碰到User
				diffX = this.get_X() - game.subUser.get_X();
				diffY = this.get_Y() - game.subUser.get_Y();
				if (diffX > -60 && diffX <= 110 && diffY > 5 && diffY < 25) {
					
					
					// handle 爆炸後
					game.remove(game.subUser);
				//	game.subUser.setCrash();
				//	game.subUser = null;
					game.remove(this);
					// Create new Sub
					game.addUserSub();
					bombPathMusic.pause();
					bombMusic.play();
					// 爆炸動畫
					ImageIcon icon2 = new ImageIcon(".\\image\\Nuclear_explosion1.gif");
					explode = new JLabel(icon2);
					explode.setSize(325, 275);
					explode.setLocation((int) this.get_X() - 130, (int) this.get_Y() - 120);
					icon2.getImage().flush();
					game.add(explode);
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					// 分數累加
					game.addScore(50);
					game.remove(explode);
					bombPathMusic.stop();
					break LOOP;
				}

			} else if (get_Y() > 700) {
				bombPathMusic.stop();
				game.remove(this);
				break LOOP;
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