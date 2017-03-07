package cs131.pa1.filter.sequential;

import java.util.LinkedList;

import cs131.pa1.filter.Message;


class PWDCommand extends SubCommand{
	//private String command;

	 
	 PWDCommand(String subcommand){
		 super(subcommand);
		 this.input = new LinkedList<String>();
		 this.output = new LinkedList<String>();
		 this.in = false;
		 this.out = true;
	 }

	 
	 //this.argument = subcommand.substring(4);
	 //argument = argument.trim();
	 
	 
	 
	 public void process(){
		 
		 if (!input.isEmpty()){
			 System.out.print(Message.CANNOT_HAVE_INPUT.with_parameter(command));
		 }
		 else{
		 //System.out.println(SequentialREPL.currentWorkingDirectory);
		 output.offer(SequentialREPL.currentWorkingDirectory);
		 }
	 }
	 
	 
	 
protected String processLine(String line){
		
		 return null;
	}
}
