import java.awt.Color;
import java.awt.Font;

import javax.swing.*;
public class Score extends JLabel{
	private int score;
	public Score(){
		setLocation(800,0);
		setSize(150, 150);
		setText("Score:00");
		setFont(new Font("Arial Black",Font.PLAIN,30));
		setForeground(Color.YELLOW);
	}
	public void addScore(){
		score=score+10;
		
		this.setText("Score:"+score);
	}
	public void reset_score(){
		score = 0;
		setText("Score:00");
	}
}
