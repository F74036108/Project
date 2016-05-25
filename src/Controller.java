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
		// if(block.getY()>=700){thread.interrupt();remove(block);game.remove(block);System.out.println("!!@@@");}

	}

	public void remove() {
		// b.remove(block);
		// thread.interrupt();
		// remove(block);

		for (int i = 0; i < b.size(); i++) {
			tempBomb = b.get(i);
			if (tempBomb.getY() > 700) {
				game.remove(tempBomb);
				b.remove(i);
			}
		}

		// game.remove(block);
		// System.out.println("!!@@@");
	}

}
