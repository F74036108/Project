
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class SubmarineMain extends JFrame {
	Image img;
	private static Ship ship;
	private static final int WIDTH = 1000;
	private static final int HEIGHT = 700;
	private static final int NUM_OF_SUBMARINES = 8;
	public SubmarineMain(){
		setSize(WIDTH, HEIGHT);
		//載入背景圖片
		setContentPane(new JLabel(new ImageIcon(".\\image\\seabg.jpg")));
	
		addKeyListener(new KeyInput(this));
	}
	
	//鍵盤控制
	public void keyPressed(KeyEvent e){
		int key = e.getKeyCode();
		
		if (key == KeyEvent.VK_RIGHT){
			ship.setX(ship.get_X()+5);//Right shift
		}else if(key == KeyEvent.VK_LEFT){
			ship.setX(ship.get_X()-5);//Left shift
		}
	}
    public void keyReleased(KeyEvent e){
		
	}
   
	public static void main(String [] args){
		JFrame frame = new SubmarineMain();
		frame.setLayout(null);

		//主艦
		ship =new Ship(420, 170);//constructor 初始位置
		frame.add(ship);
		frame.setVisible(true);
		
		
		int[] arrRand={20,200,80,320,150 ,100,180,250};//for 不同深度
		//創 NUM_OF_SUBMARINES個潛艇
		Submarine[] sub = new Submarine[NUM_OF_SUBMARINES];
		for(int i=0;i<NUM_OF_SUBMARINES;i++){
			sub[i]=new Submarine(-120,300+arrRand[i]);//constructor 初始位置
			frame.add(sub[i]);
			//以Thread同時跑潛艇	
			Thread thread = new Thread(sub[i]);
			thread.start();
			//固定間格時間 create thread
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	
	}

}
