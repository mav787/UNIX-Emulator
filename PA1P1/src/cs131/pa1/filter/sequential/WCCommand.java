package cs131.pa1.filter.sequential;

import java.util.LinkedList;

import cs131.pa1.filter.*;

class WCCommand extends SubCommand{
	 
	//private String command;
	//private String argument;
	 private int line = 0;
	 private int word = 0;
	 private int character = 0;
	 
	 WCCommand(String subcommand){
		 super(subcommand);
		 this.input = new LinkedList<String>();
		 this.output = new LinkedList<String>();
		 this.in = true;
		 this.out = true;
	 }
	 
	 //this.argument = subcommand.substring(3);
	 //argument = argument.trim();
	 
	 public void process(){
		 if (input == null){
			 return;
		 }
		 
		 else if (input.isEmpty()){
			 output.offer("0 0 0");
		 }
		 
		 	int size = input.size();
	
			while (!input.isEmpty()){
				String line = input.poll();
				String processedLine = processLine(line,size);
				if (processedLine != null){
					output.add(processedLine);
				}
			}	
		}

	 protected String processLine(String line, int size){
		 this.line++;
		 this.character += line.length();
		 String[] words = line.split(" ");
		 this.word += words.length;
		 
		 if (this.line == size){
			 return this.line + " " + this.word + " " + this.character;
		 }
		 else{
		return null;
		 }
	} 
	 
	 
	 protected String processLine(String line){
		 return null;
	 }
}
