import javax.swing.*;

public class Plane extends Vehicle implements Runnable{
	public Plane(double x, double y){
	    ImageIcon icon = new ImageIcon(".\\image\\plane.png");//SET image
	    setIcon(icon);
	    this.x = x;
	    this.y=y;
	    setLocation((int)x, (int)y);
		setSize(142,49);
	} 
	//自動移動
	public void run(){
		while(true){
			setX(get_X()+10);
			//setX(x);
			//this.x+=10;
			if(get_X()>=1100) setX(-100);
			//s.resetLocation();
			try {
				Thread.sleep(300);
		
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
