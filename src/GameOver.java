import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;

import java.awt.Graphics;
import java.awt.Graphics2D;

public class GameOver extends JFrame{
	
		public GameOver(int x ,int y){
			this.setSize(x, y);
			this.setContentPane(new JLabel(new ImageIcon(".\\image\\seabg.jpg")));
			JLabel gameover = new JLabel();
			JLabel health = new JLabel();
			JLabel word = new JLabel();
			ImageIcon icon = new ImageIcon(".\\image\\game over.png");
			ImageIcon icon2 = new ImageIcon(".\\image\\YES.png");
			ImageIcon icon3 = new ImageIcon(".\\image\\NO.png");
			ImageIcon icon4 = new ImageIcon(".\\image\\0.png");
			ImageIcon icon5 = new ImageIcon(".\\image\\again.png");

			gameover.setIcon(icon);
			gameover.setSize(547,245);
			gameover.setLocation(x/2-250,y/2-300);
			health.setIcon(icon4);
			health.setLocation(0,0);
			health.setSize(400, 22);
			word.setIcon(icon5);
			word.setLocation(x/2-270,y/2+20);
			word.setSize(547, 80);
			JButton restart = new JButton(icon2);
			JButton exitbtn = new JButton(icon3);
			Restart_listener mblistener = new Restart_listener(this);
			restart.addActionListener(mblistener);
			restart.setLocation(x/2-300,y/2+150);
			restart.setSize(234,58);
			exit_listener exitlistener = new exit_listener(this);
			exitbtn.addActionListener(exitlistener);
			exitbtn.setLocation(x/2+54,y/2+150);
			exitbtn.setSize(234,58);
			this.add(exitbtn);
			this.add(restart);
			this.add(gameover);
			this.add(health);
			this.add(word);
			this.setVisible(true);
		}
		
		public void pressed(){
			this.setVisible(false);
			this.dispose();
			SubmarineMain game = new SubmarineMain();
			game.setLayout(null);
			game.setVisible(true);
		}
		public void exit(){
			this.setVisible(false);
			this.dispose();
		}
}
