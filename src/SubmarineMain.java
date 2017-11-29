
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class SubmarineMain extends JFrame implements MouseMotionListener {


	
	
	Ship ship = new Ship(420, 170, this);// 主艦
	SubmarineUser subUser; // User控制潛艦
	HealthBar healthBar = new HealthBar();// 血量條
	
//	Laser laser = new Laser(0, 0, this);// User艦雷射
	Submarine[] sub = new Submarine[NUM_OF_SUBMARINES];// Auto潛艦
	ToxicSeaBomb seaBomb;// 深海綠色炸藥
	PentaKill pentaKill;// 大絕招
	KeyInput keyInput;
	ScreenShot screenShot = new ScreenShot(this);// 截圖
	Plane[] plane = new Plane[NUM_OF_PLANES];// 飛機
	// 音樂建立
	SoundBase startPageMusic = new SoundBase("./audio/startpage.wav");
	SoundBase backGroundMusic = new SoundBase("./audio/background.wav");
	SoundBase loadingMusic = new SoundBase("./audio/startbutton.wav");
//	SoundBase lazerMusic = new SoundBase("./audio/lazer.wav");
	SoundBase overMusic = new SoundBase("./audio/Game Over.wav");
	GameOver gameOver;// 結束畫面
	Score score;// 計分
	String userName;// 使用者NAME
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
		startPageMusic.play();
		Frontpage frame2 = new Frontpage(HEIGHT, WIDTH);
		frame2.setSize(WIDTH, HEIGHT);
		frame2.setContentPane(new JLabel(new ImageIcon("./image/seabg.jpg")));
		JLabel startButton = new JLabel();
		ImageIcon icon = new ImageIcon("./image/START.png");// LOAD image
		startButton.setIcon(icon);
		startButton.setLocation(WIDTH / 2 - 180, 350);
		startButton.setSize(350, 60);
		frame2.add(startButton);
		// 名字輸入
		JTextField input = new JTextField(30);
		input.setLocation(WIDTH / 2 - 180, 450);
		input.setSize(input.getPreferredSize());
		input.setText("<Input your name here>");
		frame2.add(input);
		frame2.setTitle("Submarine War");
		Image img = Toolkit.getDefaultToolkit().getImage("./image/submarine2.png");
		frame2.setIconImage(img);
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
				startPageMusic.pause();
				try {
					Thread.sleep(15);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				loadingMusic.play();
				userName = input.getText();
				ImageIcon icon2 = new ImageIcon("./image/START PRESS.png");// LOAD // image
				startButton.setIcon(icon2);
				frame2.setVisible(true);
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				ImageIcon icon3 = new ImageIcon("./image/LOAD1.png");// LOAD
																		// image

				startButton.setIcon(icon3);
				try {
					Thread.sleep(300);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				ImageIcon icon4 = new ImageIcon("./image/LOAD2.png");// LOAD
				// image
				startButton.setIcon(icon4);
				try {
					Thread.sleep(1150);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				ImageIcon icon5 = new ImageIcon("./image/LOAD3.png");// LOAD
				// image
				startButton.setIcon(icon5);
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				frame2.setVisible(false);

				/*--MAIN Game Window------------------------------------------------*/
				this.setTitle("Submarine War");
				this.setIconImage(img);
				setSize(WIDTH, HEIGHT);

				backGroundMusic.play();
				// 載入背景圖片
				setContentPane(new JLabel(new ImageIcon("./image/seabg.jpg")));
				// 主艦
				this.add(ship);

				// User潛艦
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

				// 綠色炸彈
				addToxicBomb();
				// 大絕招
				pentaKill = new PentaKill(850, 100, this);
				this.add(pentaKill);
				// KeyListener (for KeyInput class)
				addKeyListener(keyInput = new KeyInput(this));

				// 置入鼠標
				// add(dragOctopus);
				// dragOctopus.setBounds(mouseX, mouseY, 100,132);//166, 131
				// addMouseMotionListener(this);

				// 截圖
				this.add(screenShot);
				// GAMEOVER
				gameOver = new GameOver(WIDTH, HEIGHT, this);
				gameOver.setVisible(false);
				gameOver.setEnabled(false);
				// 計分
				score = new Score(this);
				this.add(score);
				setDefaultCloseOperation(EXIT_ON_CLOSE);
				/*------------------------------------------------------------*/
				break;
			}
		}

	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		ship.keyPressedAction(key);
		subUser.keyPressedAction(key);
		pentaKill.keyPressedAction(key);
	}

	public void addScore(int i) {
		score.addScore(i);
	}

	public void add_health() {
		healthBar.refillhealth();
		if (healthBar.get_health() > 50) {
			ship.resetIcon();
		}
		ImageIcon icon = new ImageIcon("./image/+10.png");
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
			// GameOver gameOver = new GameOver(WIDTH, HEIGHT, this);
			gameOver.setEnabled(true);
			gameOver.setVisible(true);
			if (score.scoreSaved == false) {
				score.save_score(userName);
				backGroundMusic.pause();
				overMusic.play();
				backGroundMusic.stop();
			}
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
		seaBomb = new ToxicSeaBomb(-200, (int) (400 + Math.random() * 200), Math.random() * 10 - 5.25, this);

		this.add(seaBomb);
		Thread thread = new Thread(seaBomb);
		thread.start();
	}

	public void addUserSub() {
		subUser = new SubmarineUser(-50, 550, this);
		this.add(subUser);
	}

	public void resetAll() {

		ship.setX(420);
		ship.setY(170);
		ship.resetIcon();
		backGroundMusic.play();

		PlaneBomb.resetPlaneBomb();
		Plane.resetPlane();

		for (int i = 0; i < NUM_OF_SUBMARINES; i++) {
			this.remove(sub[i]);
			sub[i].setCrash();
			addSubmarine(i);
		}
		for (int i = 0; i < NUM_OF_PLANES; i++) {
			this.remove(plane[i]);
			addPlane(i);
		}

		this.remove(subUser);
		subUser.setEnabled(false);
		addUserSub();

		healthBar.reset();

		score.reset_score();

		AttackController.resetPentakillCnt();

		keyInput.reset();

	}

	/*********************************************************************************/
	// Octopus moving with mouse
	private JLabel dragOctopus = new JLabel(new ImageIcon("./image/mouse.gif"));
	private int mouseX = 500;
	private int mouseY = 500;

	public void mouseDragged(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
		dragOctopus.setBounds(mouseX, mouseY, 100, 132);// 166, 131
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
