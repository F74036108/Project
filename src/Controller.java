import java.util.LinkedList;

public class Controller {

	private LinkedList<Bomb> b = new LinkedList<Bomb>();

	Bomb bomb;
	Bomb tempBomb;
	SubmarineMain game;

	public Controller(SubmarineMain game) {
		this.game = game;
	}

	public void add_Bomb(Bomb block) {
		b.add(block);
		game.add(block);
		Thread thread = new Thread(block);
		thread.start();

	}

	public void remove() {

		for (int i = 0; i < b.size(); i++) {
			tempBomb = b.get(i);
			if (tempBomb.getY() > 700) {
				game.remove(tempBomb);
				b.remove(i);
			}
		}
	}
}
