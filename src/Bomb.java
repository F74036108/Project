import javax.swing.*;

public class Bomb extends Vehicle implements Runnable {
	Controller ctrl;
	Bomb tempBomb;
	Submarine[] sub;
	SubmarineMain game;


	JLabel explode;

	public Bomb(double x, double y, SubmarineMain game, Controller ctrl) {

		ImageIcon icon;

		if (ctrl.type == 1)
			icon = new ImageIcon(".\\image\\bomb.png");// SET image
		else {
			icon = new ImageIcon(".\\image\\bb.png");
		}
		ImageIcon icon2 = new ImageIcon(".\\image\\explosion.gif");
		explode = new JLabel(icon2);
		explode.setSize(313, 308);
		setIcon(icon);
		this.x = x;
		this.y = y;
		this.game = game;
		this.ctrl = ctrl;
		// this.sub = sub;

		ctrl.add_Bomb(this);

		setLocation((int) x, (int) y);
		setSize(60, 60);
	}

	public void run() {
	LOOP:	while (true) {
			setY(get_Y() + 2);
			if (get_Y() <= 700 && (ctrl.type == 1)) { // check 炸彈碰到潛艇

				//for (int i = 0; i < ctrl.b.size(); i++) {
					//if (ctrl.b.get(i) == null)
					//	continue;
					//tempBomb = ctrl.b.get(i);
					for (int j = 0; j < game.sub.length; j++) {
						if (game.sub[j] == null)
							continue;
						//tempBomb.get_X();
						//game.sub[j].get_X();
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

							// remove linked list BOMB
							//ctrl.b.remove(i);
							// Create new Submarine
							game.addSubmarine(j);

							// 爆炸
							game.add(explode);
							game.getScore();
							
							try {
								Thread.sleep(1000);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							game.remove(explode);
							
							//setY(700);
							ctrl.remove(this);
							break LOOP;

						}
					}
				//}
			} else if (get_Y() <= 700 && (ctrl.type == 2)) {
				for (int j = 0; j < game.sub.length; j++) {
					if (game.sub[j] == null)
						continue;
					//tempBomb.get_X();
					//game.sub[j].get_X();
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

						// remove linked list BOMB
						//ctrl.b.remove(i);
						// Create new Submarine
						game.addSubmarine(j);

						// 爆炸
						game.add(explode);
						//game.sub_health();
						
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						game.remove(explode);
						
						//setY(700);
						ctrl.remove(this);
						break LOOP;

					}
				}

			} else if (get_Y() > 700) {

				ctrl.remove(this);
				break LOOP;

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
