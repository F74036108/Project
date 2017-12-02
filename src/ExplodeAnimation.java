import javax.swing.ImageIcon;

public class ExplodeAnimation extends GameObject{

	private ImageIcon icon;
	private static SoundBase bombMusic = new SoundBase("./audio/bomb.wav");
	
	public ExplodeAnimation(int x, int y, String mediaPath) {
		super(x, y, 0);
		// TODO Auto-generated constructor stub
		setIcon(icon = new ImageIcon(mediaPath));
		setSize(icon.getIconWidth(), icon.getIconHeight());
	}
	
	public void playExplodeSound() {
		bombMusic.play();
	}


}
