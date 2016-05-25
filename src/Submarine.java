import javax.swing.*;

public class Submarine extends Vehicle implements Runnable{
	//private double x;
	//private double y;
	public Submarine(double x, double y){
	    ImageIcon icon = new ImageIcon(".\\image\\submarine2.png");//SET image
	    setIcon(icon);
	    this.x = x;
	    this.y=y;
	    setLocation((int)x, (int)y);
		setSize(142,49);
	} 
	//自動移動
	public void run(){
		while(true){
			setX(get_X()+5);
			//setX(x);
			//this.x+=10;
			if(get_X()>=1100) setX(-100);
			//s.resetLocation();
			try {
				Thread.sleep(30);
		
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	/*
	public void resetLocation(){
		setLocation((int)x, (int)y);
	}
	public void setX(double xx){
		x = xx;
		resetLocation();
	}
	public void setY(double yy){
		y = yy;
		resetLocation();
	}
	public double get_X(){
		return x;
	}
	public double get_Y(){
		return y;
	}
	*/

}
