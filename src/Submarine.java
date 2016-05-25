import javax.swing.*;

public class Submarine extends Vehicle implements Runnable{

	public Submarine(double x, double y, double speed){
	    ImageIcon icon = new ImageIcon(".\\image\\submarine2.png");//SET image
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
			if(get_X()>=1100) setX(-100);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}


}
