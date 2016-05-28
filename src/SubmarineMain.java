
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SubmarineMain extends JFrame implements MouseMotionListener {

	Image img;

	private Ship ship = new Ship(420, 170);// 主艦
	private Controller ctrl = new Controller(this, 1);;
	private Controller ctrlPlaneBomb = new Controller(this, 2);;
	Submarine[] sub = new Submarine[NUM_OF_SUBMARINES];;
	private Plane[] plane = new Plane[NUM_OF_PLANES];

	private static final int WIDTH = 1000;
	private static final int HEIGHT = 700;
	private static final int NUM_OF_SUBMARINES = 8;
	private static final int NUM_OF_PLANES = 4;

	public static int get_width() {
		return WIDTH;
	}

	public SubmarineMain() {

		/* 開始頁面-------------------------------------------------------------- */
		Frontpage frame2 = new Frontpage(HEIGHT, WIDTH);
		frame2.setSize(WIDTH, HEIGHT);
		frame2.setContentPane(new JLabel(new ImageIcon(".\\image\\seabg.jpg")));
		JLabel startButton = new JLabel();
		ImageIcon icon = new ImageIcon(".\\image\\START.png");// LOAD image									
		startButton.setIcon(icon);
		startButton.setLocation(WIDTH / 2 -180 , 350);
		startButton.setSize(350,60);
		frame2.add(startButton);
		frame2.setVisible(true);
		/*------------------------------------------------------------------------*/
		while (true) {
			// Check START signal every 2 seconds
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// Action after click START
			if (frame2.get_state() == Frontpage.State.Start) {
				ImageIcon icon2 = new ImageIcon(".\\image\\START PRESS.png");// LOAD image
				startButton.setIcon(icon2);
				frame2.setVisible(true);
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				ImageIcon icon3 = new ImageIcon(".\\image\\LOAD1.png");// LOAD image
				startButton.setIcon(icon3);
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				ImageIcon icon4 = new ImageIcon(".\\image\\LOAD2.png");// LOAD image
				startButton.setIcon(icon4);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				ImageIcon icon5 = new ImageIcon(".\\image\\LOAD3.png");// LOAD image
				startButton.setIcon(icon5);
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				frame2.setVisible(false);

				setSize(WIDTH, HEIGHT);
				// 載入背景圖片
				setContentPane(new JLabel(new ImageIcon(".\\image\\seabg.jpg")));

				// 主艦
				this.add(ship);

				// 創 NUM_OF_SUBMARINES個潛艇
				for (int i = 0; i < NUM_OF_SUBMARINES; i++) {
					addSubmarine(i);
				}
				// 創 NUM_OF_PLANES個飛機
				for (int i = 0; i < NUM_OF_PLANES; i++) {
					addPlane(i);
				}
				// KeyListener (class KeyInput)
				addKeyListener(new KeyInput(this));
				// 置入Octopus
				add(dragOctopus);
				dragOctopus.setBounds(mouseX, mouseY, 166, 131);
				addMouseMotionListener(this);
				break;

			}
		}

	}

	// 鍵盤控制
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_RIGHT) {
			ship.setX(ship.get_X() + 5);// Right shift
		} else if (key == KeyEvent.VK_LEFT) {
			ship.setX(ship.get_X() - 5);// Left shift
		} else if (key == KeyEvent.VK_SPACE) {
			// Create BOMB
			new Bomb(ship.get_X() + 80, ship.get_Y() + 80, this, ctrl);
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

	public void addPlane(int i) {
		plane[i] = new Plane(1200, 20, Math.random() * 10, this, ctrlPlaneBomb);
		this.add(plane[i]);
		Thread thread = new Thread(plane[i]);
		thread.start();
	}

	/*********************************************************************************/
	// Octopus moving with mouse
	private JLabel dragOctopus = new JLabel(new ImageIcon(".\\image\\oct.gif"));
	private int mouseX = 500;
	private int mouseY = 500;

	public void mouseDragged(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
		dragOctopus.setBounds(mouseX, mouseY, 166, 131);
	}

	public void mouseMoved(MouseEvent e) {

		mouseX = e.getX();
		mouseY = e.getY();
		dragOctopus.setLocation(mouseX, mouseY);
	}

	/*********************************************************************************/

	public static void main(String[] args) {

		SubmarineMain game = new SubmarineMain();
		game.setLayout(null);
		game.setVisible(true);

	}
}
