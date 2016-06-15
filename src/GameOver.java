import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;

import java.awt.Graphics;
import java.awt.Graphics2D;

public class GameOver extends JFrame {
	// use in sub_health in main
	SubmarineMain game;

	public GameOver(int x, int y, SubmarineMain game) {
		this.game = game;
		this.setSize(x, y);
		this.setContentPane(new JLabel(new ImageIcon(".\\image\\scoreBoard.png")));
		// gameover圖片
		JLabel gameover = new JLabel();
		ImageIcon icon = new ImageIcon(".\\image\\game over.png");
		gameover.setIcon(icon);
		gameover.setSize(547, 245);
		gameover.setLocation(x / 2 - 250, y / 2 - 300);
		// 增加0血條
		JLabel health = new JLabel();
		ImageIcon icon4 = new ImageIcon(".\\image\\0.png");
		health.setIcon(icon4);
		health.setLocation(0, 0);
		health.setSize(400, 22);
		// 增加again文字
		JLabel word = new JLabel();
		ImageIcon icon5 = new ImageIcon(".\\image\\again.png");
		word.setIcon(icon5);
		word.setLocation(x / 2 - 270, y / 2 + 20);
		word.setSize(547, 80);
		// 增加重啟按鈕
		ImageIcon icon2 = new ImageIcon(".\\image\\YES.png");
		JButton restart = new JButton(icon2);
		Restart_listener mblistener = new Restart_listener(this);
		restart.addActionListener(mblistener);
		restart.setLocation(x / 2 - 300, y / 2 + 150);
		restart.setSize(234, 58);
		// 增加離開按鈕
		ImageIcon icon3 = new ImageIcon(".\\image\\NO.png");
		JButton exitbtn = new JButton(icon3);
		ExitListener exitlistener = new ExitListener(this);
		exitbtn.addActionListener(exitlistener);
		exitbtn.setLocation(x / 2 + 54, y / 2 + 150);
		exitbtn.setSize(234, 58);
		// 將按鈕新增至frame
		this.add(exitbtn);
		this.add(restart);
		this.add(gameover);
		this.add(health);
		this.add(word);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public void pressed() {
		this.setVisible(false);
		game.resetAll();
		try {
			Thread.sleep(200);

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		game.setEnabled(true);
		game.setVisible(true);
		this.dispose();
	}

	public void exit() {
		this.setVisible(false);
		this.dispose();
		System.exit(0);
	}
}
