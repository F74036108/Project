import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;

import java.awt.Graphics;
import java.awt.Graphics2D;

public class GameOver extends JFrame{
	
	    SubmarineMain game;
		public GameOver(int x ,int y,SubmarineMain game ){
			this.game = game;
			this.setSize(x, y);
			this.setContentPane(new JLabel(new ImageIcon(".\\image\\seabg.jpg")));
			//game over 圖案
			JLabel gameover = new JLabel();
			ImageIcon icon = new ImageIcon(".\\image\\game over.png");
			gameover.setIcon(icon);
			gameover.setSize(547,245);
			gameover.setLocation(x/2-250,y/2-300);
			//歸零血條設置
			ImageIcon icon4 = new ImageIcon(".\\image\\0.png");
			JLabel health = new JLabel();
			health.setIcon(icon4);
			health.setLocation(0,0);
			health.setSize(400, 22);
			//try again文字
			JLabel word = new JLabel();
			ImageIcon icon5 = new ImageIcon(".\\image\\again.png");
			word.setIcon(icon5);
			word.setLocation(x/2-270,y/2+20);
			word.setSize(547, 80);
			//重開始按鈕
			ImageIcon icon2 = new ImageIcon(".\\image\\YES.png");
			JButton restart = new JButton(icon2);
			Restart_listener mblistener = new Restart_listener(this);
			restart.addActionListener(mblistener);
			restart.setLocation(x/2-300,y/2+150);
			restart.setSize(234,58);
			//離開按鈕
			ImageIcon icon3 = new ImageIcon(".\\image\\NO.png");
			JButton exitbtn = new JButton(icon3);
			ExitListener exitlistener = new ExitListener(this);
			exitbtn.addActionListener(exitlistener);
			exitbtn.setLocation(x/2+54,y/2+150);
			exitbtn.setSize(234,58);
			//加入所有按鈕及畫面
			this.add(exitbtn);
			this.add(restart);
			this.add(gameover);
			this.add(health);
			this.add(word);
			this.setVisible(true);
		}
		
		public void pressed(){
			this.setVisible(false);
			game.resetAll();
			try {
				Thread.sleep(200);

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			game.setVisible(true);
			this.dispose();
		}
		public void exit(){
			this.setVisible(false);
			this.dispose();
			System.exit(0);
		}
}
