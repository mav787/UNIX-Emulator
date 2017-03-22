package cs131.pa1.filter.sequential;

import java.util.LinkedList;

import cs131.pa1.filter.Message;

 class NotACommand extends SubCommand{
	// private String command;
	 
	 
	 NotACommand(String subcommand){
		 super(subcommand);
		 this.input = new LinkedList<String>();
		 this.output = new LinkedList<String>();
		 this.in = false;
		 this.out = false;
	 }
	 
	 
	 
	 
	protected  String processLine(String line){
		String s = Message.COMMAND_NOT_FOUND.with_parameter(this.command);
		return s;
	}
}
