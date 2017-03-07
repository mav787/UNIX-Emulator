package cs131.pa1.filter.sequential;

import java.util.LinkedList;



class DefaultFilter extends SubCommand {
	 
	//private String command;

	 
	 DefaultFilter(String subcommand){
		super(subcommand);
		 this.input = new LinkedList<String>();
		 this.output = new LinkedList<String>();
		 this.in = true;
		 this.out = true;
	 }
	 
	 //this.argument = subcommand.substring(5);		// string index out of range
	// argument = argument.trim();
	 
	 public void process(){
		 this.output = this.input;
		 if (output == null || output.size() == 0 || output.peek() == null || output.peek().length() == 0){
			 
		 }
		 else{
		 for (String s:output){
			 System.out.println(s);
		 }
		}
	 }
	
	protected String processLine(String line){
		return null;
	}
}
