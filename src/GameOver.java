import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class GameOver extends JFrame{
	
		public GameOver(int x ,int y){
			this.setSize(x, y);
			this.setContentPane(new JLabel(new ImageIcon(".\\image\\seabg.jpg")));
			JLabel gameover = new JLabel();
			ImageIcon icon = new ImageIcon(".\\image\\game over.png");
			gameover.setIcon(icon);
			gameover.setSize(549,245);
			gameover.setLocation(x/2-280,y/2-200);
			this.add(gameover);
			this.setVisible(true);
		}
}
