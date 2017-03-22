package ahaha;
public class aaaaa {
	public static void main(String[] args) throws Exception{
		
		
		int i=1;
		Test test = new Test(i);
		Runnable hhh = new Testing(test);
		Thread t=new Thread(hhh);
		t.start();
		
		while(true)
		{
			System.out.println(test.getA());
			Thread.sleep(1000);
		}	
	}
}

class Test{
	private int a;
	Test(int a){
		this.a = a;
	}
	int getA(){
		return this.a;
		
	}
	
	void setA(int a){
		this.a = a;
	}
}

class Testing implements Runnable {
	private Test b;
	public Testing(Test b)
	{
		this.b=b;
	}
	 public void run() {  
	        while(true)
	        {
	        	this.b.setA(this.b.getA() + 1);;
	        }
	    }  
	 public int getValue(){
	        return this.b.getA();
	    }
}
