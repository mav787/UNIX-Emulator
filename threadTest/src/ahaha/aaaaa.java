package ahaha;
public class aaaaa {
	public static void main(String[] args) throws Exception{	
		int i = 1;
		
		Test test = new Test(i);
		Runnable runnableTesting = new RunnableTesting(test);
		Thread t = new Thread(runnableTesting);
		t.start();
		
		while(true)
		{
			System.out.println(((RunnableTesting) runnableTesting).getValue());
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
	
	class RunnableTesting implements Runnable {
		private Test b;
		public RunnableTesting(Test b){
			this.b = b;
		}
		 public void run() {  
		        while(true){
		        	this.b.setA(this.b.getA() + 1);
		        }
		    }  
		 public int getValue(){
		        return this.b.getA();
		    }
	}
