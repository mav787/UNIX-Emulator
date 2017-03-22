package synchronization;

class Producer implements Runnable {
	private BoundedBuffer buffer;
	private int operationsTotal, operationsDone = 0;
	private int id;
	public Producer(BoundedBuffer buf, int ops, int id){
		this.buffer = buf;
		this.operationsTotal = ops;
		this.id = id;
	}
	public void run(){
		while(operationsDone < operationsTotal){
			try {
				String element = "Producer "+this.id+": element #" + operationsDone;
				if(ProducerConsumerTest.DEBUG)
					System.out.println("Producer puts: {"+element+"}");
				buffer.put(element);
				operationsDone++;
			}
			catch (InterruptedException ie){
				ie.printStackTrace();
				return;
			}
		}
	}
}

class Consumer implements Runnable {
	private BoundedBuffer buffer;
	private int operationsTotal, operationsDone = 0;
	private int id;
	public Consumer(BoundedBuffer buf, int ops, int id){
		this.buffer = buf;
		this.operationsTotal = ops;
		this.id = id;
	}
	public void run(){
		while(operationsDone < operationsTotal){
			try {
				String element = (String) buffer.take();
				if (ProducerConsumerTest.DEBUG)
					System.out.println("Consumer "+this.id+" got: {"+element+"}");
				operationsDone++;
			}
			catch (InterruptedException ie){
				ie.printStackTrace();
				return;
			}
		}
	}
}
	

public class ProducerConsumerTest {
	public static final boolean DEBUG = false;
	public static final int NUM_GETS = 5000000;
	public static final int NUM_PUTS = 10000000;
	//public static final int NUM_GETS = 200;
	//public static final int NUM_PUTS = 200;
	public static final int NUM_PRODUCERS = 1;
	public static final int NUM_CONSUMERS = 2;
	
	public static void main(String args[]){
		BoundedBuffer buffer = new BoundedBufferSynchronized();
		Thread[] producers = new Thread[NUM_PRODUCERS];
		Thread[] consumers = new Thread[NUM_CONSUMERS];
		// start producer/consumer threads!
		for (int i=0; i<NUM_PRODUCERS; i++){
			producers[i] = new Thread(new Producer(buffer, NUM_PUTS, i));
			producers[i].start();
		}
		for (int i=0; i<NUM_CONSUMERS; i++){
			consumers[i] = new Thread(new Consumer(buffer, NUM_GETS, i));
			consumers[i].start();
		}
		
		long start = System.currentTimeMillis();
		for (int i=0; i<NUM_PRODUCERS; i++){
			try {
				producers[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		for (int i=0; i<NUM_CONSUMERS; i++){
			try {
				consumers[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		long end = System.currentTimeMillis();
		System.out.println("Finished. Total time: "+(end-start)+"ms");
	}
	
}
