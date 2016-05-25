import javax.swing.*;

public class Bomb extends Vehicle implements Runnable{
	Controller ctrl;
	Bomb tempBomb;
	public Bomb(double x, double y, Controller ctrl){
	    ImageIcon icon = new ImageIcon(".\\image\\bomb.png");//SET image
	    setIcon(icon);
		this.x = x;
		this.y = y;
		this.ctrl=ctrl;
		
		ctrl.add_Bomb(this);
		
		setLocation((int)x ,(int)y);
		setSize(59,83);
	}
	public void run(){
		while(true){
			setY(get_Y()+2);
			if(get_Y() > 700) {
				
			
				//setY(700);
				//this.interrupt();
				
				ctrl.remove();
				break;
				
			}
			//s.resetLocation();
			try {
				Thread.sleep(20);
		
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
