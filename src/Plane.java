import javax.swing.*;

public class Plane extends Vehicle implements Runnable{
	public Plane(double x, double y, double speed){
	    ImageIcon icon = new ImageIcon(".\\image\\plane.png");//SET image
	    setIcon(icon);
	    this.speed = speed;
	    this.x = x;
	    this.y=y;
	    setLocation((int)x, (int)y);
		setSize(200,200);
	} 
	//自動移動
	public void run(){
		while(true){
			System.out.println("!!");
			setX(get_X()-1*speed);
			setY(get_Y()+(Math.random()*2-1));
			//setX(x);
			//this.x+=10;
			if(get_X()<=-100) setX(1200);
			//s.resetLocation();
			try {
				Thread.sleep(30);
		
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}