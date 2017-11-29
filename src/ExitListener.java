
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExitListener implements ActionListener {

	GameOver gameover;

	public ExitListener(GameOver gameover) {
		this.gameover = gameover;
	}

	public void actionPerformed(ActionEvent e) {
		gameover.exit();
	}
}
