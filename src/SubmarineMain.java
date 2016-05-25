
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SubmarineMain extends JFrame {
	Image img;
	private static Ship ship;
	private static Controller ctrl;
	private static Controller ctrlPlaneBomb;
	static Submarine[] sub;
	private static final int WIDTH = 1000;
	private static final int HEIGHT = 700;
	private static final int NUM_OF_SUBMARINES = 8;
	private static final int NUM_OF_PLANES = 4;

	public SubmarineMain() {

		setSize(WIDTH, HEIGHT);
		// 載入背景圖片
		setContentPane(new JLabel(new ImageIcon(".\\image\\seabg.jpg")));

		addKeyListener(new KeyInput(this));
		// ctrl = new Controller(this, sub);
		ctrl = new Controller(this, 1);
		ctrlPlaneBomb = new Controller(this, 2);
	}

	// 鍵盤控制
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_RIGHT) {
			ship.setX(ship.get_X() + 5);// Right shift
		} else if (key == KeyEvent.VK_LEFT) {
			ship.setX(ship.get_X() - 5);// Left shift

		} else if (key == KeyEvent.VK_SPACE) {

			new Bomb(ship.get_X() + 80, ship.get_Y() + 80, this, ctrl);
		} else if (key == KeyEvent.VK_0) {
			remove(ctrl.b.get(1));
			remove(sub[1]);
			ctrl.b.remove(1);
			addSubmarine(1);
		}
	}


	public void addSubmarine(int i) {
		int[] arrRand = { 40, 210, 80, 320, 150, 100, 180, 250 };// for 不同深度
		sub[i] = new Submarine(-120 - i * 100, 300 + arrRand[i], Math.random() * 10);
		// constructor 初始位置
		this.add(sub[i]);
		// 以Thread同時跑潛艇
		Thread thread = new Thread(sub[i]);
		thread.start();
	}

	public static void main(String[] args) {
		JFrame frame = new SubmarineMain();
		frame.setLayout(null);

		// 主艦
		ship = new Ship(420, 170);// constructor 初始位置
		frame.add(ship);
		frame.setVisible(true);

		int[] arrRand = { 40, 210, 80, 320, 150, 100, 180, 250 };// for 不同深度

		// Thread thr=new Thread(new BombCrash((SubmarineMain)frame, ctrl,
		// sub));
		// thr.start();
		// 創 NUM_OF_SUBMARINES個潛艇
		sub = new Submarine[NUM_OF_SUBMARINES];
		for (int i = 0; i < NUM_OF_SUBMARINES; i++) {
			sub[i] = new Submarine(-120 - i * 100, 300 + arrRand[i], Math.random() * 10);
			// constructor 初始位置
			frame.add(sub[i]);
			// 以Thread同時跑潛艇
			Thread thread = new Thread(sub[i]);
			thread.start();

			// 固定間格時間 create thread
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		Plane[] plane = new Plane[NUM_OF_PLANES];
		for (int i = 0; i < NUM_OF_PLANES; i++) {
			plane[i] = new Plane(1200, 20, Math.random() * 10, (SubmarineMain) frame, ctrlPlaneBomb);
			frame.add(plane[i]);
			Thread thread = new Thread(plane[i]);
			thread.start();

			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
