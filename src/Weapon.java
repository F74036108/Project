public abstract class Weapon extends GameObject{

	public Weapon(int x, int y, double speed) {
		super(x, y, speed);
		// TODO Auto-generated constructor stub
	}

	public abstract boolean handleCollision();
}
