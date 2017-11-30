
/******************************
 * Score Record along with ScoreBoard
 * 
 * @author 鞠之浩
 * Scoreboard(GameOver) bg image design by 許熙浩
 *****************************/
import java.awt.Color;
import java.awt.Font;
import java.awt.Label;

import javax.swing.*;
import java.io.*;
import java.util.Arrays;
import java.util.SortedMap;
import java.util.TreeMap;

public class Score extends JLabel {
	private int score = 0;
	private int health_sign = 0;
	public int[] scores = new int[5+1];
	String[] userNameArr = new String[5+1];
	private JLabel[] colLabels = new JLabel[12];
	SubmarineMain game;
	boolean scoreSaved;
	JLabel lb_level= new JLabel();
	int level = 1;

	public Score(SubmarineMain game) {
		this.game = game;
		// this.gameOver = gameOver;

		setLocation(800, -50);
		setSize(300, 150);
		setText("Score:00");
		setFont(new Font("Arial Black", Font.PLAIN, 30));
		setForeground(Color.YELLOW);
		lb_level.setLocation(1, 1);
		lb_level.setSize(300, 20);
		lb_level.setText("level:1");
		lb_level.setFont(new Font("Arial Black", Font.PLAIN, 20));
		lb_level.setForeground(Color.RED);
		game.add(lb_level);
		try {
			FileReader fr = new FileReader("scorerecord.txt");
			BufferedReader br = new BufferedReader(fr);
			String line;
			int i = 0;
			while ((line = br.readLine()) != null) {
				scores[i] = Integer.parseInt(line.substring(line.indexOf("score:") + 6));
				userNameArr[i] = line.substring(line.indexOf("name:") + 5, line.indexOf("score:"));

				// System.out.print(scores[i]);
				// System.out.print(userNameArr[i]+"\n");
				i++;
			}
			br.close();
		} catch (Exception e) {
			// e.printStackTrace();
		}
		
		for (int i=0;i<colLabels.length;i++) {
			colLabels[i] = new JLabel();
			game.gameOver.add(colLabels[i]);
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
	
	private void refreshTable() {
		setLabelText(0, Integer.toString(score), 300, -38, 300, 150);
		
		for (int i=scores.length-1;i>0;i--) {
			setLabelText(scores.length-i, Integer.toString(scores[i]), 300, 160 + (scores.length-1-i)*90, 300, 150);
		}
		
		for (int i=userNameArr.length-1;i>0;i--) {
			setLabelText(userNameArr.length-1-i+6, userNameArr[i], 100, 180 + (userNameArr.length-1-i)*90, 300, 150);
		}
	}
	
	public void addScore(int i) {
		score = score + i;
		this.setText("Score:" + score);
		level = score / 100 + 1;
		lb_level.setText("level:" + level);
		// 加血量
		if ((score - health_sign) / 50 == 1) {
			health_sign = score;
			game.add_health();
		}

	}

	public void reset_score() {
		score = 0;
		setText("Score:00");
		scoreSaved = false;
	}

	private void sortScoreRecord()
    {
        int n = scores.length;
        for (int i=1; i<n; ++i)
        {
            int key = scores[i];
            String name = userNameArr[i];
            int j = i-1;
 
            /* Move elements of scores[0..i-1], that are
               greater than key, to one position ahead
               of their current position */
            while (j>=0 && scores[j] > key)
            {
                scores[j+1] = scores[j];
                userNameArr[j+1] = userNameArr[j];
                j = j-1;
            }
            scores[j+1] = key;
            userNameArr[j+1] = name;
        }
    }
 
	public void save_score(String userName) {
		if (!scoreSaved) {
			scoreSaved = true;
			try {

				if (userName.equals("<Input your name here>")) {
					userName = "User";
				}
				FileWriter fw = new FileWriter("scorerecord.txt");
				
				scores[5] = score;
				userNameArr[5] = userName;
				sortScoreRecord();

				//Write out
				for(int i=scores.length-1;i>0;i--) {
					fw.append("Top"+ (scores.length-i)+" name:" + userNameArr[i] + "score:" + scores[i] + "\r\n");
				}
				fw.flush();
				fw.close();

				refreshTable();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
}