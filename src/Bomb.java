import javax.swing.ImageIcon;
import javax.swing.JLabel;

/****
 * Bomb thrown from Ship
 * 
 * @author Jeff
 *
 ****/
public class Bomb extends Vehicle implements Runnable {
	Bomb tempBomb;
	SubmarineMain game;
	JLabel explode;
	JLabel explode2;
	JLabel explo_anmi;

	public Bomb(double x, double y, SubmarineMain game) {
		this.game = game;
		this.x = x;
		this.y = y;
		setLocation((int) x, (int) y);
		setSize(60, 60);

		// SET BOMB image
		ImageIcon icon = new ImageIcon(".\\image\\bomb.png");
		setIcon(icon);
		// EXPLOSION2
		ImageIcon icon3 = new ImageIcon(".\\image\\explo2.gif");
		explode2 = new JLabel(icon3);
		explode2.setSize(450, 255);
		ImageIcon icon4 = new ImageIcon(".\\image\\explore.gif");
		explo_anmi = new JLabel(icon4);
		explo_anmi.setSize(1000, 700);

	}

	public void addBomb(int x, int y) {
		Bomb bomb = new Bomb(x, y, game);
		game.add(bomb);
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
					if (diffX > -60 && diffX <= 120 && diffY > 5 && diffY < 25) {
						// handle 爆炸後
						// remove 被炸掉的submarine
						game.remove(game.sub[j]);
						game.sub[j].setCrash();
						game.sub[j] = null;
						game.remove(this);
						// Create new Sub
						game.addSubmarine(j);

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
						game.addScore();
						game.remove(explode);
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

					break LOOP;
				}
				// Check 碰到User
				diffX = this.get_X() - game.subUser.get_X();
				diffY = this.get_Y() - game.subUser.get_Y();
				if (diffX > -60 && diffX <= 110 && diffY > 5 && diffY < 25) {
					
					
					// handle 爆炸後
					game.remove(game.subUser);
					game.subUser.setCrash();
					game.subUser = null;
					game.remove(this);
					// Create new Sub
					game.addUserSub();

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
					game.addScore();
					game.remove(explode);
					break LOOP;
				}

			} else if (get_Y() > 700) {
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