import java.awt.event.*;
import java.util.*;

public class KeyInput extends KeyAdapter {
	SubmarineMain game;
	private final Set<KeyEvent> pressed = new HashSet<KeyEvent>();

	public KeyInput(SubmarineMain game) {
		this.game = game;

	}

	public synchronized void keyPressed(KeyEvent e) {
		int key1 = e.getKeyCode();
		boolean chk = true;
		for (KeyEvent E : pressed) {
			int key2 = E.getKeyCode();
			if (key1 == key2) {
				chk = false;
				break;
				// pressed.remove(E);
			}
		}
		if (chk)
			pressed.add(e);
		if (pressed.size() >= 1) {
			for (KeyEvent E : pressed) {
				game.keyPressed(E);
			}

		}
	}

	public synchronized void keyReleased(KeyEvent e) {
		int key1 = e.getKeyCode();
		// System.out.println(key1);
		for (KeyEvent A : pressed) {
			int key2 = A.getKeyCode();
			if (key1 == key2) {
				pressed.remove(A);
				break;
			}
		}

	}

	public void reset() {
		pressed.clear();
	}

}
