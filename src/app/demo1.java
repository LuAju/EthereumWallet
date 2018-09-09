package app;

public class demo1 {
	private static demo1 d = new demo1();
	
	private demo1(){
		
	}
	
	public demo1 createDemo(){
		return d;
	}
	
}
