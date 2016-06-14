import javax.swing.*;

public class Submarine extends Vehicle implements Runnable{

	public Submarine(double x, double y, double speed){
		ImageIcon icon;
		if(speed>0){
			icon = new ImageIcon(".\\image\\submarine2.png");//SET image
		}else{
			icon = new ImageIcon(".\\image\\submarine3.png");//SET image
		}
	    
	    setIcon(icon);
	    this.speed = speed;
	    this.x = x;
	    this.y=y;
	    setLocation((int)x, (int)y);
		setSize(142,49);
	} 
	//自動移動
	public void run(){
		while(true){
			setX(get_X()+1*speed);
			if(get_X()>=1200&&speed>=0) setX(-200);
			if(get_X()<=-200&&speed<0) setX(1200);
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}


}
