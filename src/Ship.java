import javax.swing.*;
import java.awt.*;
public class Ship extends Vehicle {
	//protected double x;
	//protected double y;
	public Ship(double x, double y){
	    ImageIcon icon = new ImageIcon(".\\image\\Troop_Ship2.png");//LOAD image
	    setIcon(icon);
	    this.x = x;
	    this.y=y;
	    setLocation((int)x, (int)y);
		setSize(189,129);
	} 
	/*
	public void resetLocation(){
		setLocation((int)x, (int)y);
	}
	public void setX(double x){
		this.x = x;
		resetLocation();
	}
	public void setY(double y){
		this.y = y;
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
