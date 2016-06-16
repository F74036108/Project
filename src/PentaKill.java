
public class PentaKill {
	SubmarineMain game;
	boolean Q;
	
	boolean E;

	public PentaKill(SubmarineMain game) {
		this.game = game;	
	}
	public void bombFromSky(){
		
		for(int i=0;i<=11;i++){
			game.bomb.addBomb(90*i, (int)(Math.random()*150));
		}
		reset();
		
	}
	public void reset(){
		Q = false;
		
		E = false;
	}
	
}
