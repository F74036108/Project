import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

public class GameOver extends JFrame {
	// use in sub_health in main
	private SubmarineMain game;
	private JLabel[] colLabels = new JLabel[12];
	private Score scores;
	private SoundBase gameOverMusic = new SoundBase("./audio/Game Over.wav");

	public GameOver(int x, int y, SubmarineMain game, Score scores) {
		this.game = game;
		this.scores=scores;
		this.setSize(x, y);
		this.setContentPane(new JLabel(new ImageIcon("./image/scoreBoard.png")));
		this.setTitle("Submarine War");
		Image img = Toolkit.getDefaultToolkit().getImage("./image/submarine2.png");
		this.setIconImage(img);
		
		JLabel gameover = new JLabel();
		ImageIcon icon = new ImageIcon("./image/game over.png");
		gameover.setIcon(icon);
		gameover.setSize(547, 245);
		gameover.setLocation(x / 2 - 20, y / 2 - 300);
		// 增加0血條
		JLabel health = new JLabel();
		ImageIcon icon4 = new ImageIcon("./image/0.png");
		health.setIcon(icon4);
		health.setLocation(0, 0);
		health.setSize(400, 22);
		// 增加again文字
		JLabel word = new JLabel();
		ImageIcon icon5 = new ImageIcon("./image/again.png");
		word.setIcon(icon5);
		word.setLocation(x / 2 - 70, y / 2 + 20);
		word.setSize(547, 80);
		// 增加重啟按鈕
		ImageIcon icon2 = new ImageIcon("./image/YES.png");
		JButton restart = new JButton(icon2);
		RestartListener mblistener = new RestartListener(this);
		restart.addActionListener(mblistener);
		restart.setLocation(x / 2 - 50, y / 2 + 150);
		restart.setSize(234, 58);
		// 增加離開按鈕
		ImageIcon icon3 = new ImageIcon("./image/NO.png");
		JButton exitbtn = new JButton(icon3);
		ExitListener exitlistener = new ExitListener(this);
		exitbtn.addActionListener(exitlistener);
		exitbtn.setLocation(x / 2 + 234, y / 2 + 150);
		exitbtn.setSize(234, 58);
		// 將按鈕新增至frame
		this.add(exitbtn);
		this.add(restart);
		this.add(gameover);
		// this.add(health);
		this.add(word);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		for (int i=0;i<colLabels.length;i++) {
			colLabels[i] = new JLabel();
			this.add(colLabels[i]);
		}
		
		refreshTable();
		
	}

	private void setLabelText(int index, String text, int x, int y, int width, int height) {
		colLabels[index].setLocation(x, y);
		colLabels[index].setSize(width, height);
		colLabels[index].setText(text);
		colLabels[index].setFont(new Font("Broadway", Font.PLAIN, 50));
		colLabels[index].setForeground(Color.YELLOW);
	}
	
	public void refreshTable() {
		setLabelText(0, Integer.toString(scores.getScore()), 300, -38, 300, 150);
		int scoreRecord[] = scores.getScoreRecords();
		for (int i=scoreRecord.length-1;i>0;i--) {
			setLabelText(scoreRecord.length-i, Integer.toString(scoreRecord[i]), 300, 160 + (scoreRecord.length-1-i)*90, 300, 150);
		}
		
		String userNameArr[] = scores.getUsernameRecords();
		for (int i=userNameArr.length-1;i>0;i--) {
			setLabelText(userNameArr.length-1-i+6, userNameArr[i], 100, 180 + (userNameArr.length-1-i)*90, 300, 150);
		}
	}
	
	public void restartButtonPressed() {
		this.hideGameOverPage();
		gameOverMusic.stop();
		game.resetAll();
		try {
			Thread.sleep(200);

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		game.showMainGameBoard();
		// this.dispose();
	}
	
	public void showGameOverPage() {
		this.setVisible(true);
		this.setEnabled(true);
		gameOverMusic.play();
		
	}

	public void hideGameOverPage() {
		this.setVisible(false);
		this.setEnabled(false);
	}
	public void exit() {
		this.setVisible(false);
		this.dispose();
		System.exit(0);
	}
}
