package cs131.pa1.filter.concurrent;




public class WcFilter extends ConcurrentFilter {
	private int linecount;
	private int wordcount;
	private int charcount;

	
	
	
	public WcFilter() {
		super();
	}
	
//	public void process() throws InterruptedException {
//		if(isDone()) {
//			output.add(processLine(null));
//		} else {
//			super.process();
//		}
		
		
//	}	
	
	public void doJob1() throws InterruptedException{
		String line = input.poll();			
		String processedLine = processLine1(line);		//processLine1
		if (processedLine != null){
			output.put(processedLine);
		}
	}
	
	public void doJob2() throws InterruptedException{
		String line = input.poll();			
		String processedLine = processLine2(line);		//processLine2
		if (processedLine != null){
			output.put(processedLine);
		}
	}
	
	public void process() throws InterruptedException{
		this.setFinish(false);
			while(this.getPrevFilter() != null && !this.getPrevFilter().isDone()){		
				// the previous filter has not done yet, doJob1
				doJob1();		// might get null 
				}
			
			// if input is not empty, doJob2
			// do while loop executes at least once
			do{								
				doJob2();		// getting a null means finishing the job
			}while (!input.isEmpty());
		this.setFinish(true);
	}
	
	
	public String processLine1(String line) {
		// passed a null, but job not done, do nothing
		if(line == null) {
			return null;   
		}
		else {
			linecount++;
			String[] wct = line.split(" ");
			wordcount += wct.length;
			String[] cct = line.split("|");
			charcount += cct.length;
			return null;
		}
	}
	
	public String processLine2(String line) {
		//prints current result if ever passed a null
		if(line == null) {
			return linecount + " " + wordcount + " " + charcount;
		}
		
		if (input.isEmpty()){
			// the last line
			String[] wct = line.split(" ");
			wordcount += wct.length;
			String[] cct = line.split("|");
			charcount += cct.length;
			return ++linecount + " " + wordcount + " " + charcount;
		} else {
			linecount++;
			String[] wct = line.split(" ");
			wordcount += wct.length;
			String[] cct = line.split("|");
			charcount += cct.length;
			return null;
		}
	}
	
	// not used
	public String processLine(String line) {
		return null;
	}
}
