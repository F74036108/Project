import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RestartListener implements ActionListener {
	GameOver gameover;

	public RestartListener(GameOver gameover) {
		this.gameover = gameover;
	}

	public void actionPerformed(ActionEvent e) {
		gameover.restartButtonPressed();
	}
}
