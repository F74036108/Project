import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class StartPage extends JFrame{
	
	private SubmarineMain game;
	
	private SoundBase startPageMusic = new SoundBase("./audio/startpage.wav");
	private SoundBase loadingMusic = new SoundBase("./audio/startbutton.wav");
	private JLabel startButton;
	private JTextField input_area;
	public static enum State {
		Start, Front
	};

	public static State state;
	
	public StartPage(SubmarineMain game, int width, int height) {
		this.game=game;
		setSize(width, height);
		this.addMouseListener(new MouseInput(this));
		state = State.Front;
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		setContentPane(new JLabel(new ImageIcon("./image/seabg.jpg")));
		startButton = new JLabel();
		startButton.setIcon(new ImageIcon("./image/START.png"));
		startButton.setLocation(width / 2 - 180, 350);
		startButton.setSize(350, 60);
		add(startButton);
		// 名字輸入
		input_area = new JTextField(30);
		input_area.setLocation(width / 2 - 180, 450);
		input_area.setSize(input_area.getPreferredSize());
		input_area.setText("<Input your name here>");
		add(input_area);
		setTitle("Submarine War");
	
		setIconImage(Toolkit.getDefaultToolkit().getImage("./image/submarine2.png"));
		setVisible(true);
	}
	
	public void playStartPageMusic() {
		startPageMusic.play();
	}
	
	public void stopStartPageMusic() {
		startPageMusic.stop();
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		Font fnt0 = new Font("arial", Font.BOLD, 80);
		g.setFont(fnt0);
		g.setColor(Color.white);
		g2d.drawString("Submarine WAR", game.getWidth() / 2 - 290, 200);

	}

	public void set_start_state() {
		state = State.Start;
	}

	public State get_state() {
		return state;
	}
	
	public void startButtonPressed() {
		
		try {
			stopStartPageMusic();
			
			loadingMusic.play();
			game.setUserName(input_area.getText());
		
			startButton.setIcon(new ImageIcon("./image/START PRESS.png"));
			this.setVisible(true);
			
			Thread.sleep(200);
			
			startButton.setIcon( new ImageIcon("./image/LOAD1.png"));
			
			Thread.sleep(300);
			
			startButton.setIcon(new ImageIcon("./image/LOAD2.png"));
			
			Thread.sleep(1150);
			
			startButton.setIcon(new ImageIcon("./image/LOAD3.png"));
			
			Thread.sleep(100);

			this.setVisible(false);
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
