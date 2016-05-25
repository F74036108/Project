import java.util.LinkedList;

public class Controller extends Vehicle implements Runnable{
	
	private LinkedList<Bomb> b = new LinkedList<Bomb>();
	
	Bomb bomb;
	SubmarineMain game;
	
	public Controller(SubmarineMain game){
		this.game = game;
	}
	
	public void run(){
		for(int i = 0;i < b.size();i++){
			bomb = b.get(i);
			
			bomb.run();
		}
	}
	
	public void add_Bomb(Bomb block){
		b.add(block);
	}
	
	public void remove(Bomb block){
		b.remove(block);
	}

}
