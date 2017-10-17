import java.awt.Color;
import java.awt.Font;
import javax.swing.*;
import java.io.*;

public class Score extends JLabel {
	private int score = 0;
	private int health_sign = 0;
	public int[] x = new int[5];
	String[] userNameArr = new String[5];
	SubmarineMain game;
	boolean scoreSaved;
	JLabel lv = new JLabel();
	int LV=1;
	//Name&score labels for scoreboard
	JLabel a = new JLabel();
	JLabel b = new JLabel();
	JLabel c = new JLabel();
	JLabel d = new JLabel();
	JLabel e = new JLabel();
	JLabel f = new JLabel();
	JLabel g = new JLabel();
	JLabel h = new JLabel();
	JLabel i = new JLabel();
	JLabel j = new JLabel();
	JLabel k = new JLabel();
	public Score(SubmarineMain game) {
		this.game = game;
		//this.gameOver = gameOver;
		
		
		setLocation(800, -50);
		setSize(300, 150);
		setText("Score:00");
		setFont(new Font("Arial Black", Font.PLAIN, 30));
		setForeground(Color.YELLOW);
		lv.setLocation(1,1);
		lv.setSize(300,20);
		lv.setText("LV:1");
		lv.setFont(new Font("Arial Black", Font.PLAIN, 20));
		lv.setForeground(Color.RED);
		game.add(lv);
		try{
			FileReader fr = new FileReader("scorerecord.txt");
			BufferedReader br = new BufferedReader(fr);
			String line;
			int i=0;
			while((line=br.readLine())!=null){
				x[i]=Integer.parseInt(line.substring(line.indexOf("score:")+6));
				userNameArr[i]=line.substring(line.indexOf("name:")+5,line.indexOf("score:"));

//				System.out.print(x[i]);
//				System.out.print(userNameArr[i]+"\n");
				i++;
			}
		}catch(Exception e) {
			//e.printStackTrace();
		}
		a.setLocation(300, -38);
		a.setSize(300, 150);
		a.setText(Integer.toString(score));
		a.setFont(new Font("Broadway", Font.PLAIN, 50));
		a.setForeground(Color.YELLOW);

		b.setLocation(300, 160);
		b.setSize(300, 150);
		b.setText(Integer.toString(x[0]));
		b.setFont(new Font("Broadway", Font.PLAIN, 50));
		b.setForeground(Color.YELLOW);

		c.setLocation(300, 250);
		c.setSize(300, 150);
		c.setText(Integer.toString(x[1]));
		c.setFont(new Font("Broadway", Font.PLAIN, 50));
		c.setForeground(Color.YELLOW);

		d.setLocation(300, 345);
		d.setSize(300, 150);
		d.setText(Integer.toString(x[2]));
		d.setFont(new Font("Broadway", Font.PLAIN, 50));
		d.setForeground(Color.YELLOW);

		e.setLocation(300, 430);
		e.setSize(300, 150);
		e.setText(Integer.toString(x[3]));
		e.setFont(new Font("Broadway", Font.PLAIN, 50));
		e.setForeground(Color.YELLOW);

		f.setLocation(300, 520);
		f.setSize(300, 150);
		f.setText(Integer.toString(x[4]));
		f.setFont(new Font("Broadway", Font.PLAIN, 50));
		f.setForeground(Color.YELLOW);

		g.setLocation(100, 180);
		g.setSize(300, 150);
		g.setText(userNameArr[0]);
		g.setFont(new Font("Broadway", Font.PLAIN, 50));
		g.setForeground(Color.YELLOW);

		h.setLocation(100, 270);
		h.setSize(300, 150);
		h.setText(userNameArr[1]);
		h.setFont(new Font("Broadway", Font.PLAIN, 50));
		h.setForeground(Color.YELLOW);

		i.setLocation(100, 360);
		i.setSize(300, 150);
		i.setText(userNameArr[2]);
		i.setFont(new Font("Broadway", Font.PLAIN, 50));
		i.setForeground(Color.YELLOW);

		j.setLocation(100, 450);
		j.setSize(300, 150);
		j.setText(userNameArr[3]);
		j.setFont(new Font("Broadway", Font.PLAIN, 50));
		j.setForeground(Color.YELLOW);

		k.setLocation(100, 520);
		k.setSize(300, 150);
		k.setText(userNameArr[4]);
		k.setFont(new Font("Broadway", Font.PLAIN, 50));
		k.setForeground(Color.YELLOW);
		
		
		game.gameOver.add(a);
		game.gameOver.add(b);
		game.gameOver.add(c);
		game.gameOver.add(d);
		game.gameOver.add(e);
		game.gameOver.add(f);
		game.gameOver.add(g);
		game.gameOver.add(h);
		game.gameOver.add(i);
		game.gameOver.add(j);
		game.gameOver.add(k);
	}

	public void addScore(int i) {
		score = score + i;
		this.setText("Score:" + score);
		LV=score/100+1;
		lv.setText("LV:"+LV);
		// 加血量
		if ((score - health_sign) / 50 == 1) {
			health_sign = score;
			game.add_health();
		}
		
	}
	
	public void reset_score() {
		score = 0;
		setText("Score:00");
		scoreSaved=false;
	}
	public void save_score(String userName){
		if(!scoreSaved){
			scoreSaved=true;
		try{
			
			if(userName.equals("<Input your name here>")){
				userName="User";
			}
			
		
			FileWriter fw = new FileWriter("scorerecord.txt");
			if(score>=x[0]){
				x[4]=x[3];x[3]=x[2];x[2]=x[1];x[1]=x[0];
				userNameArr[4]=userNameArr[3];userNameArr[3]=userNameArr[2];userNameArr[2]=userNameArr[1];userNameArr[1]=userNameArr[0];
				x[0]=score;userNameArr[0]=userName;
				}
			else if(score<x[0]&&score>=x[1]){
				x[4]=x[3];x[3]=x[2];x[2]=x[1];
				userNameArr[4]=userNameArr[3];userNameArr[3]=userNameArr[2];userNameArr[2]=userNameArr[1];
				x[1]=score;userNameArr[1]=userName;
				}
			else if(score<x[1]&&score>=x[2]){
				x[4]=x[3];x[3]=x[2];
				userNameArr[4]=userNameArr[3];userNameArr[3]=userNameArr[2];
				x[2]=score;userNameArr[2]=userName;
				}
			else if(score<x[2]&&score>=x[3]){
				x[4]=x[3];
				userNameArr[4]=userNameArr[3];
				x[3]=score;userNameArr[3]=userName;
				}
			else if(score<x[3]&&score>=x[4]){
				x[4]=score;userNameArr[4]=userName;
				}
			else{//score=score;
				}

			fw.append("Top1 name:"+userNameArr[0]+"score:"+x[0]+"\r\n");
			fw.append("Top2 name:"+userNameArr[1]+"score:"+x[1]+"\r\n");
			fw.append("Top3 name:"+userNameArr[2]+"score:"+x[2]+"\r\n");
			fw.append("Top4 name:"+userNameArr[3]+"score:"+x[3]+"\r\n");
			fw.append("Top5 name:"+userNameArr[4]+"score:"+x[4]+"\r\n");
			fw.flush();
			fw.close();
			
			a.setLocation(300, -38);
			a.setSize(300, 150);
			a.setText(Integer.toString(score));
			a.setFont(new Font("Broadway", Font.PLAIN, 50));
			a.setForeground(Color.YELLOW);

			b.setLocation(300, 160);
			b.setSize(300, 150);
			b.setText(Integer.toString(x[0]));
			b.setFont(new Font("Broadway", Font.PLAIN, 50));
			b.setForeground(Color.YELLOW);

			c.setLocation(300, 250);
			c.setSize(300, 150);
			c.setText(Integer.toString(x[1]));
			c.setFont(new Font("Broadway", Font.PLAIN, 50));
			c.setForeground(Color.YELLOW);

			d.setLocation(300, 345);
			d.setSize(300, 150);
			d.setText(Integer.toString(x[2]));
			d.setFont(new Font("Broadway", Font.PLAIN, 50));
			d.setForeground(Color.YELLOW);

			e.setLocation(300, 430);
			e.setSize(300, 150);
			e.setText(Integer.toString(x[3]));
			e.setFont(new Font("Broadway", Font.PLAIN, 50));
			e.setForeground(Color.YELLOW);

			f.setLocation(300, 520);
			f.setSize(300, 150);
			f.setText(Integer.toString(x[4]));
			f.setFont(new Font("Broadway", Font.PLAIN, 50));
			f.setForeground(Color.YELLOW);

			g.setLocation(100, 180);
			g.setSize(300, 150);
			g.setText(userNameArr[0]);
			g.setFont(new Font("Broadway", Font.PLAIN, 50));
			g.setForeground(Color.YELLOW);

			h.setLocation(100, 270);
			h.setSize(300, 150);
			h.setText(userNameArr[1]);
			h.setFont(new Font("Broadway", Font.PLAIN, 50));
			h.setForeground(Color.YELLOW);

			i.setLocation(100, 360);
			i.setSize(300, 150);
			i.setText(userNameArr[2]);
			i.setFont(new Font("Broadway", Font.PLAIN, 50));
			i.setForeground(Color.YELLOW);

			j.setLocation(100, 450);
			j.setSize(300, 150);
			j.setText(userNameArr[3]);
			j.setFont(new Font("Broadway", Font.PLAIN, 50));
			j.setForeground(Color.YELLOW);

			k.setLocation(100, 520);
			k.setSize(300, 150);
			k.setText(userNameArr[4]);
			k.setFont(new Font("Broadway", Font.PLAIN, 50));
			k.setForeground(Color.YELLOW);
			

			
			}catch (Exception e) {
				e.printStackTrace();
			}
		}

/*		System.out.print(x[0]);
		System.out.print(userNameArr[0]+"\n");

		System.out.print(x[1]);
		System.out.print(userNameArr[1]+"\n");
		System.out.print(x[2]);
		System.out.print(userNameArr[2]+"\n");
		System.out.print(x[3]);
		System.out.print(userNameArr[3]+"\n");
		System.out.print(x[4]);
		System.out.print(userNameArr[4]+"\n");
*/
	
	
	
	
	
	
	}
}