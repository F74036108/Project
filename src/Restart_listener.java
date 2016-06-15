import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Restart_listener implements ActionListener {
	GameOver gameover;

	public Restart_listener(GameOver gameover) {
		this.gameover = gameover;
	}
 
	public void actionPerformed(ActionEvent e) {
		gameover.pressed();
	}
}
