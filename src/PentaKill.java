
public class PentaKill {
	SubmarineMain game;
	private static Bomb bomb;
	
	public PentaKill(SubmarineMain game) {
		this.game = game;
		
	}
	public void bombFromSky(){
		
		for(int i=1;i<6;i++){
			bomb.addBomb(150*i, 100);
		}
	}
	
	
}
