import javax.swing.*;
/****
 * Bomb thrown from Ship
 * @author Jeff
 *
 ****/
public class Bomb extends Vehicle implements Runnable {
	Controller ctrl;
	Bomb tempBomb;
	SubmarineMain game;
	JLabel explode;

	public Bomb(double x, double y, SubmarineMain game, Controller ctrl) {

		this.x = x;
		this.y = y;
		setLocation((int) x, (int) y);
		setSize(60, 60);
		this.game = game;
		this.ctrl = ctrl;
		
		// SET BOMB image
		ImageIcon icon = new ImageIcon(".\\image\\bomb.png");	
		setIcon(icon);
		//EXPLOSION
		ImageIcon icon2 = new ImageIcon(".\\image\\explosion.gif");
		explode = new JLabel(icon2);
		explode.setSize(313, 308);
		//Manage each Bomb	
		ctrl.add_Bomb(this);

	}
	public void run() {
LOOP:	while (true) {
			setY(get_Y() + 1);
			if (get_Y() <= 700) { // check 炸彈碰到潛艇
					for (int j = 0; j < game.sub.length; j++) {
						if (game.sub[j] == null)
							continue;
						double diffX = this.get_X() - game.sub[j].get_X();
						double diffY = this.get_Y() - game.sub[j].get_Y();
						if (diffX > -60 && diffX <= 120 && diffY > 5 && diffY < 25) {
							// 爆炸setLocation
							explode.setLocation((int) this.get_X() - 130, (int) this.get_Y() - 120);
							// handle 爆炸後
							// remove LABEL
							game.remove(game.sub[j]);
							game.sub[j]=null;
							game.remove(this);

							//Create new Sub
							game.addSubmarine(j);
							
							//分數累加
							game.getScore();
							
							// 爆炸
							game.add(explode);			
							try {
								Thread.sleep(500);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							game.remove(explode);
							
							ctrl.remove(this);
							break LOOP;

						}
					}
			} else if (get_Y() > 700) {

				ctrl.remove(this);
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
