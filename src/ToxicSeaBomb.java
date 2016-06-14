
import javax.swing.ImageIcon;

public class ToxicSeaBomb extends Vehicle implements Runnable{

	SubmarineMain game;
	private boolean crash;
	public ToxicSeaBomb(double x, double y, double speed, SubmarineMain game){
		this.game = game;
		ImageIcon icon;
		if(speed>0){
			icon = new ImageIcon(".\\image\\Toxic_bill2.png");//SET image
		}else{
			icon = new ImageIcon(".\\image\\Toxic_bill3.png");//SET image
			setX(1300);
		}
	    
	    setIcon(icon);
	    this.speed = speed;
	    this.x = x;
	    this.y=y;
	    setLocation((int)x, (int)y);
		setSize(100,100);
	} 
	//自動移動
	public void run(){
		while(true){
			if(crash==true)
				break;
			setX(get_X()+1*speed);
			if(get_X()>=1500&&speed>=0) {
				game.addToxicBomb();
				game.remove(this);
				break;
			}
			if(get_X()<=-400&&speed<0) {
				game.addToxicBomb();
				game.remove(this);
				break;
			}
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	public void setCrash(){
		crash=true;
	}


}
