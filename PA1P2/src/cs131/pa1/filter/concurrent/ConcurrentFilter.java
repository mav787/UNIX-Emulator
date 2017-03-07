package cs131.pa1.filter.concurrent;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import cs131.pa1.filter.Filter;


public abstract class ConcurrentFilter extends Filter implements Runnable{
	
	protected BlockingQueue<String> input;
	protected BlockingQueue<String> output;
	protected boolean finish = true;
	//private static final String POISON_PILL = "POISON_PILL";
	
	protected void setFinish(boolean bool){
		this.finish = bool;
	}
	protected boolean getFinish(){
		return this.finish;
	}
	
	@Override
	public void setPrevFilter(Filter prevFilter) {
		prevFilter.setNextFilter(this);
	}
	
	
	
	@Override
	public void setNextFilter(Filter nextFilter) {
		if (nextFilter instanceof ConcurrentFilter){
			ConcurrentFilter sequentialNext = (ConcurrentFilter) nextFilter;
			this.next = sequentialNext;
			sequentialNext.prev = this;
			if (this.output == null){
				//Use the linked blocking queue
				this.output = new LinkedBlockingQueue<String>();
			}
			sequentialNext.input = this.output;
		} else {
			throw new RuntimeException("Should not attempt to link dissimilar filter types.");
		}
	}
	
	protected ConcurrentFilter getPrevFilter(){
		if (this.prev != null){
		return (ConcurrentFilter) this.prev;
	}
		else{
			return null;
		}
	}
	
	public void doJob() throws InterruptedException{
		// do the job
		String line = input.poll();			// take() will wait here, causing deadlock
		String processedLine = processLine(line);
		if (processedLine != null){
			output.put(processedLine);
		}
	}
	
	
	public void process() throws InterruptedException{
		this.setFinish(false);
			while(this.getPrevFilter() != null && !this.getPrevFilter().isDone()){		
				// the previous filter has not done yet
				doJob();
				}
			
			while (!this.input.isEmpty()){
				// complete the remaining jobs when the previous filter has done
				doJob();
			}
		this.setFinish(true);
	}
	
	public void run(){
		try {
			process();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			
		}
	}
	

	@Override
	public boolean isDone() {
		//this definition has flaws...
//			if (this.getPrevFilter() == null){	// OR this.input == null,
//												// which means that it is the first filter
//					return false;				// only to be overridden........
//			}
//			else{
//				return (input.size() == 0) && (this.getPrevFilter().isDone());		
//			}		
		return this.getFinish();
	}
	
	protected abstract String processLine(String line);
	
}
