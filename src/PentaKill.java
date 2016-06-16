import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class PentaKill {
	SubmarineMain game;
	boolean Q;
	boolean E;
	private final int NUM_OF_PENTAKILL=3;
	private int killCount;

	public PentaKill(SubmarineMain game) {
		this.game = game;
		JLabel pentaKill = new JLabel();
		ImageIcon icon = new ImageIcon(".\\image\\submarine2.png");// SET image;
		pentaKill.setIcon(icon);
		pentaKill.setLocation(0, 600);
		pentaKill.setSize(142, 49);
		this.game.add(pentaKill);
	}
	public void bombFromSky(){
		if(killCount<NUM_OF_PENTAKILL){
			for(int i=0;i<=11;i++){
				game.bomb.addBomb(90*i, (int)(Math.random()*150));
			}
			killCount++;
			reset();
		}
		
	}
	public void reset(){
		Q = false;
		
		E = false;
	}
	public void resetKillCnt(){
		killCount=0;
	}
	
}
