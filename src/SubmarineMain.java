
/********************
 * Main Game Window
 * 
 ********************/
import javax.swing.*;
import java.awt.event.*;

public class SubmarineMain extends JFrame implements MouseMotionListener {

	Ship ship = new Ship(420, 170);// 主艦
	SubmarineUser subUser = new SubmarineUser(0,500);//User控制潛艦
	private HealthBar healthBar = new HealthBar();
	Bomb bomb = new Bomb(0, 0, this);
	Submarine[] sub = new Submarine[NUM_OF_SUBMARINES];
	ToxicSeaBomb seaBomb;
	private ScreenShot screenShot = new ScreenShot(this);
	private Plane[] plane = new Plane[NUM_OF_PLANES];
	private Score score = new Score(this);
	private static final int WIDTH = 1000;
	private static final int HEIGHT = 700;
	private static final int NUM_OF_SUBMARINES = 6;
	private static final int NUM_OF_PLANES = 3;

	public static int get_width() {
		return WIDTH;
	}

	public static int get_height() {
		return HEIGHT;
	}

	public SubmarineMain() {

		/* 開始頁面-------------------------------------------------------------- */
		Frontpage frame2 = new Frontpage(HEIGHT, WIDTH);
		frame2.setSize(WIDTH, HEIGHT);
		frame2.setContentPane(new JLabel(new ImageIcon(".\\image\\seabg.jpg")));
		JLabel startButton = new JLabel();
		ImageIcon icon = new ImageIcon(".\\image\\START.png");// LOAD image
		startButton.setIcon(icon);
		startButton.setLocation(WIDTH / 2 - 180, 350);
		startButton.setSize(350, 60);
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
				ImageIcon icon2 = new ImageIcon(".\\image\\START PRESS.png");// LOAD
																				// image
				startButton.setIcon(icon2);
				frame2.setVisible(true);
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				ImageIcon icon3 = new ImageIcon(".\\image\\LOAD1.png");// LOAD
																		// image
				startButton.setIcon(icon3);
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				ImageIcon icon4 = new ImageIcon(".\\image\\LOAD2.png");// LOAD
																		// image
				startButton.setIcon(icon4);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				ImageIcon icon5 = new ImageIcon(".\\image\\LOAD3.png");// LOAD
																		// image
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
				
				
				//User潛艦
				addUserSub();
				
				// 血條
				this.add(healthBar);
				// 創 NUM_OF_SUBMARINES個潛艇
				for (int i = 0; i < NUM_OF_SUBMARINES; i++) {
					addSubmarine(i);
				}
				// 創 NUM_OF_PLANES個飛機
				for (int i = 0; i < NUM_OF_PLANES; i++) {
					addPlane(i);
				}

				addToxicBomb();

				// 計分
				this.add(score);
				// KeyListener (class KeyInput)
				addKeyListener(new KeyInput(this));
				//InputMap im = panel.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW);
				    
				// 置入Octopus
				add(dragOctopus);
				dragOctopus.setBounds(mouseX, mouseY, 250,250);//166, 131
				addMouseMotionListener(this);

				// 截圖
				this.add(screenShot);

				setDefaultCloseOperation(EXIT_ON_CLOSE);
				break;
			}
		}

	}

	// 鍵盤控制
	long lastShoot = System.currentTimeMillis();
	final long threshold = 800;

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_D) {// Right shift
			if(ship.get_X()<850)
			ship.setX(ship.get_X() + 5);
		} else if (key == KeyEvent.VK_A) {// Left shift
			if(ship.get_X()>-50)
			ship.setX(ship.get_X() - 5);
		} else if (key == KeyEvent.VK_SPACE) {
			// Create BOMB from SHIP
			// new Bomb(ship.get_X() + 80, ship.get_Y() + 80, this, ctrl);
			long now = System.currentTimeMillis();
			if (now - lastShoot > threshold) {
				bomb.addBomb((int) ship.get_X() + 80, (int) ship.get_Y() + 80);
				lastShoot = now;
			}
			
		}else if (key == KeyEvent.VK_UP) {
			if(subUser.get_Y()>=300)
				subUser.setY(subUser.get_Y() - 5);// Up shift
		}else if (key == KeyEvent.VK_DOWN) {
			if(subUser.get_Y()<=600)
				subUser.setY(subUser.get_Y() + 5);// Down shift
	    }else if (key == KeyEvent.VK_LEFT) {
	    	if(subUser.get_X()>-25){
				subUser.setX(subUser.get_X() - 10);// Left shift
				subUser.changeDirection("left");
	    	}
	    }else if (key == KeyEvent.VK_RIGHT) {
	    	if(subUser.get_X()<850){
				subUser.setX(subUser.get_X() + 10);// Right shift
				subUser.changeDirection("right");
	    	}
	    }else if (key == KeyEvent.VK_ENTER) {
	    	
	    }
	}

	public void addScore() {
		score.addScore();
	}

	public void add_health() {
		healthBar.refillhealth();
		if (healthBar.get_health() > 50) {
			ship.resetIcon();
		}
		ImageIcon icon = new ImageIcon(".\\image\\+10.png");
		JLabel picture = new JLabel();
		picture.setIcon(icon);
		picture.setLocation(WIDTH / 2 - 150, HEIGHT / 2 - 30);
		picture.setSize(278, 238);
		if (healthBar.getHealth() < 100) {
			this.add(picture);
			picture.setVisible(true);
			try {
				Thread.sleep(2000);

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			picture.setVisible(false);
		}
		this.remove(picture);
	}

	public void sub_health(int a) {
		healthBar.minusHealth(a);
		// GameOver Signal
		if (healthBar.getHealth() <= 0) {
			this.setVisible(false);
			this.setEnabled(false);
			GameOver gameOver = new GameOver(WIDTH, HEIGHT, this);
			gameOver.setVisible(true);
		}
		if (healthBar.getHealth() <= 50) {
			ship.change_picture();
		}
	}

	public void addSubmarine(int i) {
		int[] arrRand = { 140, 210, 80, 320, 175, 115, 180, 250 };// for 不同深度
		sub[i] = new Submarine(-120 - i * 100, 300 + arrRand[i], Math.random() * 10 - 5.05);
		// constructor 初始位置
		this.add(sub[i]);
		// 以Thread同時跑潛艇
		Thread thread = new Thread(sub[i]);
		thread.start();
	}

	public void addPlane(int i) {
		plane[i] = new Plane(1000 + i * 300, 10, 1, this);
		this.add(plane[i]);
		Thread thread = new Thread(plane[i]);
		thread.start();
	}

	public void addToxicBomb() {
		seaBomb = new ToxicSeaBomb(-200, 400 + Math.random() * 200, Math.random() * 10 - 5.25, this);
		this.add(seaBomb);
		Thread thread = new Thread(seaBomb);
		thread.start();
	}

	public void addUserSub(){
		subUser = new SubmarineUser(-50,530);
		this.add(subUser);
	}
	public void resetAll() {
		// FOR RESTART reset all components & datas
		ship.setX(420);
		ship.setY(170);
		for (int i = 0; i < NUM_OF_SUBMARINES; i++) {
			this.remove(sub[i]);
			sub[i].setCrash();
			addSubmarine(i);
		}
		for (int i = 0; i < NUM_OF_PLANES; i++) {
			this.remove(plane[i]);
			addPlane(i);
		}
		healthBar.reset();
		PlaneBomb.resetPlaneBomb();
		score.reset_score();
		ship.resetIcon();
	}

	/*********************************************************************************/
	// Octopus moving with mouse
	private JLabel dragOctopus = new JLabel(new ImageIcon(".\\image\\cat1.gif"));
	private int mouseX = 500;
	private int mouseY = 500;

	public void mouseDragged(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
		dragOctopus.setBounds(mouseX, mouseY, 250, 250);//166, 131
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
