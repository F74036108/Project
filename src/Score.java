import java.awt.Color;
import java.awt.Font;
import javax.swing.*;
import java.io.*;

public class Score extends JLabel {
	private int score = 0;
	private int health_sign = 0;
	public int[] x = new int[5]; 
	SubmarineMain game;
	
	public Score(SubmarineMain game) {
		this.game = game;
		setLocation(800, 0);
		setSize(300, 150);
		setText("Score:00");
		setFont(new Font("Arial Black", Font.PLAIN, 30));
		setForeground(Color.YELLOW);
		try{
			FileReader fr = new FileReader("scorerecord.txt");
			BufferedReader br = new BufferedReader(fr);
			String line;
			int i=0;
			while((line=br.readLine())!=null){
				x[i]=Integer.parseInt(line.substring(line.indexOf(":")+1));
//				System.out.println(x[i]);
				i++;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void addScore() {
		score = score + 10;
		this.setText("Score:" + score);
		// 加血量
		if ((score - health_sign) / 50 == 1) {
			health_sign = score;
			game.add_health();
		}
	}

	public void reset_score() {
		score = 0;
		setText("Score:00");
	}
	public void save_score(){
		try{
			FileWriter fw = new FileWriter("scorerecord.txt");
			if(score>=x[0]){
				x[4]=x[3];x[3]=x[2];x[2]=x[1];x[1]=x[0];
				x[0]=score;}
			else if(score<x[0]&&score>=x[1]){
				x[4]=x[3];x[3]=x[2];x[2]=x[1];
				x[1]=score;}
			else if(score<x[1]&&score>=x[2]){
				x[4]=x[3];x[3]=x[2];
				x[2]=score;}
			else if(score<x[2]&&score>=x[3]){
				x[4]=x[3];
				x[3]=score;}
			else if(score<x[3]&&score>=x[4]){
				x[4]=score;}
			else{score=score;}
			fw.append("Top1:"+x[0]+"\r\n");
			fw.append("Top2:"+x[1]+"\r\n");
			fw.append("Top3:"+x[2]+"\r\n");
			fw.append("Top4:"+x[3]+"\r\n");
			fw.append("Top5:"+x[4]+"\r\n");
			fw.flush();
			fw.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
	}
}