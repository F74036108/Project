import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class SubmarineMain extends JFrame{

	Ship ship = new Ship(420, 170, this);// 主艦
	SubmarineUser subUser; // User控制潛艦
	HealthBar healthBar = new HealthBar();// 血量條
	Submarine[] sub = new Submarine[NUM_OF_SUBMARINES];// Auto潛艦
	ToxicSeaBomb seaBomb;// 深海綠色炸藥
	PentaKill pentaKill;// 大絕招
	
	ScreenShot screenShot = new ScreenShot(this);// 截圖
	Plane[] plane = new Plane[NUM_OF_PLANES];// 飛機
	GameOver gameOver;// 結束畫面
	Score score;// 計分
	
	// 音樂建立
	private SoundBase backGroundMusic = new SoundBase("./audio/background.wav");
	private KeyInput keyInput;
	private String userName;// 使用者NAME
	
	private static final int WIDTH = 1000;
	private static final int HEIGHT = 700;
	private static final int NUM_OF_SUBMARINES = 6;
	private static final int NUM_OF_PLANES = 3;

	public static void main(String[] args) {

		SubmarineMain game = new SubmarineMain();
		game.setLayout(null);
		game.setVisible(true);

	}
	
	public SubmarineMain() {
		StartPage startPage = new StartPage(this, WIDTH, HEIGHT);

		while (true) {
			// Check START signal every 2 seconds
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// Action after click START
			if (startPage.get_state() == StartPage.State.Start) {
				
				try {
					Thread.sleep(15);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				startPage.startButtonPressed();

				initMainGameBoard();
				break;
			}
		}

	}
	
	private void initMainGameBoard() {
		this.setTitle("Submarine War");
		Image img = Toolkit.getDefaultToolkit().getImage("./image/submarine2.png");
		this.setIconImage(img);
		this.setSize(WIDTH, HEIGHT);

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

		// 截圖
		this.add(screenShot);
		// 計分
		score = new Score(this);
		this.add(score);
		// GAMEOVER
		gameOver = new GameOver(WIDTH, HEIGHT, this, score);
		gameOver.hideGameOverPage();
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public void showMainGameBoard() {
		this.setEnabled(true);
		this.setVisible(true);
	}
	
	public void hideMainGameBoard() {
		this.setEnabled(false);
		this.setVisible(false);
	}
	
	public void playBackgroundMusic() {
		backGroundMusic.play();
	}
	
	public void pauseBackgroundMusic() {
		backGroundMusic.pause();
	}
	
	public void resumeBackgroundMusic() {
		backGroundMusic.resume();
	}
	
	public void stopBackgroundMusic() {
		backGroundMusic.stop();
	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		ship.keyPressedAction(key);
		subUser.keyPressedAction(key);
		pentaKill.keyPressedAction(key);
	}

	public void setUserName(String name) {
		userName = name;
	}
	public void addScore(int i) {
		score.addScore(i);
	}

	public void addHealth() {
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

public void subHealth(int a) {
		healthBar.minusHealth(a);
		// GameOver Signal
		if (healthBar.getHealth() <= 0) {
			handleGameOver();
		}else if (healthBar.getHealth() <= 50) {
			ship.change_picture();
		}
	}

	private void handleGameOver() {
		this.hideMainGameBoard();
		// GameOver gameOver = new GameOver(WIDTH, HEIGHT, this);
		gameOver.showGameOverPage();
		if (score.checkIfScoreIsSaved() == false) {
			score.save_score(userName);
			gameOver.refreshTable();
			backGroundMusic.stop();
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
	
	public int getWidth() {
		return WIDTH;
	}

	public int getHeight() {
		return HEIGHT;
	}
}
