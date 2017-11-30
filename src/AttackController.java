
public final class AttackController {

	private static long bombLastShootingTime = System.currentTimeMillis();
	private static long laserLastShootingTime = System.currentTimeMillis();
	private final static long BOMB_FIRING_INTERVAL = 800;
	private final static long LASER_FIRING_INTERVAL = 1800;
	
	final static int MAX_PENTAKILL_TIMES = 3;
	private static int pentakillTimes = 0;
	
	public static void addBomb(SubmarineMain game, int x, int y) {
		long currentTime = System.currentTimeMillis();
		
		if (currentTime - bombLastShootingTime > BOMB_FIRING_INTERVAL) {

			Bomb bomb = new Bomb(x, y, game);
			game.add(bomb);
			bomb.playDroppingSound();

			Thread thread = new Thread(bomb);
			thread.start();
			
			bombLastShootingTime = currentTime;
		}
		
	}
	
	public static void addPlaneBomb(SubmarineMain game, int x, int y) {
		PlaneBomb bomb = new PlaneBomb(x, y, game);
		game.add(bomb);
		Thread thread = new Thread(bomb);
		thread.start();

	}
	
	public static void addLaser(SubmarineMain game, int x, int y) {
		long currentTime = System.currentTimeMillis();
		
		if (currentTime - laserLastShootingTime > LASER_FIRING_INTERVAL) {

			Laser laser = new Laser(x, y, game);
			game.add(laser);
			laser.playLaserSound();
			
			Thread thread = new Thread(laser);
			thread.start();
			
			laserLastShootingTime = currentTime;
		}
	}
	
	public static void pentaKill(SubmarineMain game) {
		if (pentakillTimes < MAX_PENTAKILL_TIMES) {

			game.pentaKill.showPentaAnimationOnShip();

			for (int i = 0; i <= 8; i++) {
//				game.bomb.addBomb(110 * i + 10, (int) (Math.random() * 150));
				Bomb bomb = new Bomb(110 * i + 10, (int) (Math.random() * 150), game);
				game.add(bomb);
				Thread thread = new Thread(bomb);
				thread.start();
			}
			pentakillTimes++;
			game.pentaKill.changeIcon(pentakillTimes);
			

		}

	}
	
	public static void resetPentakillCnt() {
		pentakillTimes = 0;
	}
	
	public static void showExplosion(SubmarineMain game, int x, int y) {
		
		try {
			Explosion explosion = new Explosion(x, y, "./image/Nuclear_explosion1.gif");
			explosion.playExplodeSound();
			game.add(explosion);
			Thread.sleep(500);
			
			game.remove(explosion);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
