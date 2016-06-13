import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class exit_listener implements ActionListener{
	
	GameOver gameover;
	public exit_listener(GameOver gameover){
		this.gameover = gameover;
	}
	public void actionPerformed(ActionEvent e){
		gameover.exit();
	}
}
