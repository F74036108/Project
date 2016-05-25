import java.awt.event.*;

public class KeyInput extends KeyAdapter {
	SubmarineMain game;

	public KeyInput(SubmarineMain game) {
		this.game = game;
	}

	public void keyPressed(KeyEvent e) {
		game.keyPressed(e);
	}

}
