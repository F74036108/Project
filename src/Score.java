import java.awt.Color;
import java.awt.Font;
import javax.swing.*;

public class Score extends JLabel {
	private int score = 0;
	private int health_sign = 0;
	SubmarineMain game;

	public Score(SubmarineMain game) {
		this.game = game;
		setLocation(800, 0);
		setSize(300, 150);
		setText("Score:00");
		setFont(new Font("Arial Black", Font.PLAIN, 30));
		setForeground(Color.YELLOW);
	}

	public void addScore() {
		score = score + 10;
		this.setText("Score:" + score);
		//加血量
		if ((score - health_sign) / 50 == 1) {
			health_sign = score;
			game.add_health();
		}
	}

	public void reset_score() {
		score = 0;
		setText("Score:00");
	}
}