import javax.swing.*;

public class Bomb extends Vehicle implements Runnable{
	
	public Bomb(double x, double y){
	    ImageIcon icon = new ImageIcon(".\\image\\bomb.png");//SET image
	    setIcon(icon);
		this.x = x;
		this.y = y;
		
		setLocation((int)x ,(int)y);
		setSize(59,83);
	}
	public void run(){
		while(true){
			setY(get_Y()+10);
			
			if(get_Y() == 0) setY(-120);
			//s.resetLocation();
			try {
				Thread.sleep(300);
		
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
