import java.awt.Label;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class PentaKill {
	SubmarineMain game;
	boolean Q;
	boolean E;
	private final int NUM_OF_PENTAKILL=3;
	private int killCount;
	JLabel pentaKill = new JLabel();
	ImageIcon icon1 = new ImageIcon(".\\image\\many bombs1.png");
	ImageIcon icon2 = new ImageIcon(".\\image\\many bombs2.png");
	ImageIcon icon3 = new ImageIcon(".\\image\\many bombs3.png");
	ImageIcon iconPenta = new ImageIcon(".\\image\\big kill2.gif");
	JLabel penta = new JLabel(iconPenta);
			
	public PentaKill(SubmarineMain game) {
		this.game = game;
		ImageIcon icon = new ImageIcon(".\\image\\submarine2.png");// SET image;
		pentaKill.setIcon(icon3);
		pentaKill.setLocation(850, 100);
		pentaKill.setSize(80, 110);
		this.game.add(pentaKill);
		penta.setSize(185, 190);
	}
	public void bombFromSky(){
		if(killCount<NUM_OF_PENTAKILL){
		//	game.ship.setIcon(iconShipPenta);
			penta.setLocation((int)game.ship.get_X(), (int)game.ship.get_Y());

			for(int i=0;i<=8;i++){
				game.bomb.addBomb(110*i+10, (int)(Math.random()*150));
			}
			killCount++;
			if(killCount==1)pentaKill.setIcon(icon2);
			if(killCount==2)pentaKill.setIcon(icon1);
			if(killCount==3)pentaKill.setVisible(false);
			//game.ship.resetIcon();
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
